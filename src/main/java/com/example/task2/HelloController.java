/**
 * Класс для управления рисованием фигур на холсте, включая создание, изменение цвета, выделение,
 * перетаскивание, отмену действий и переключение режимов рисования и выделения.
 */
package com.example.task2;

import model.fabrica.ShapeFactory;
import model.shapes.Shape;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.*;

public class HelloController {
    @FXML
    private Canvas canvas;
    @FXML
    private ListView<String> shapeListView;
    @FXML
    private TextField sizeInput;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Label modeLabel; // Ссылка на метку режима

    private ShapeFactory shapeFactory = new ShapeFactory();
    private ArrayList<Shape> shapes = new ArrayList<>();
    private Stack<UndoAction> undoStack = new Stack<>(); // Стек для Undo действий

    private boolean isDrawing = false;
    private boolean isSelecting = false; // флаг для выделения
    private boolean isDragging = false; // флаг для перетаскивания
    private Shape currentShape = null;
    private Rectangle selectionRect = null; // прямоугольник выделения
    private Set<Shape> selectedShapes = new HashSet<>(); // выделенные фигуры
    private double dragStartX, dragStartY; // Начальные координаты перетаскивания

    // Инициализация ListView
    public void initialize() {
        shapeListView.setItems(FXCollections.observableArrayList(
                "Линия", "Квадрат", "Треугольник", "Круг", "Угол", "Пятиугольник"
        ));
        modeLabel.setText("Режим: Рисование"); // Устанавливаем начальный режим
    }

    // Метод для очистки холста
    public void onClear() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        shapes.clear();
        undoStack.clear();
        selectedShapes.clear();
    }

    // Создаёт фигуру на основе выбранного названия
    private Shape createShapeByName(String shapeName, Color color, double size) {
        switch (shapeName) {
            case "Линия":
                return shapeFactory.createShape("Line", color, size);
            case "Квадрат":
                return shapeFactory.createShape("Square", color, size);
            case "Треугольник":
                return shapeFactory.createShape("Triangle", color, size, size);
            case "Круг":
                return shapeFactory.createShape("Circle", color, size);
            case "Угол":
                return shapeFactory.createShape("Angle", color, size);
            case "Пятиугольник":
                return shapeFactory.createShape("Pentagon", color, size);
            default:
                return null;
        }
    }

    // Показывает уведомление об ошибке
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Обработчик для нажатия мыши
    @FXML
    private void onMousePressed(MouseEvent event) {
        if (isSelecting) {
            // Проверяем, нажали ли на выделенную фигуру
            for (Shape shape : selectedShapes) {
                if (shape.contains(event.getX(), event.getY())) {
                    isDragging = true;
                    dragStartX = event.getX();
                    dragStartY = event.getY();
                    return;
                }
            }
            // Если не нажали на выделенную фигуру, начинаем выделение
            selectionRect = new Rectangle(event.getX(), event.getY(), 0, 0);
        } else {
            isDrawing = true;
            onMouseDragged(event);
        }
    }

    // Обработчик для отпускания мыши
    @FXML
    private void onMouseReleased(MouseEvent event) {
        if (isDragging) {
            isDragging = false; // Завершаем перетаскивание
        } else if (isSelecting) {
            // Завершаем выделение
            double x = selectionRect.getX();
            double y = selectionRect.getY();
            double width = selectionRect.getWidth();
            double height = selectionRect.getHeight();

            // Отмечаем фигуры, которые попадают в выделенную область
            selectedShapes.clear();
            for (Shape shape : shapes) {
                if (shape.getX() >= x && shape.getX() <= x + width && shape.getY() >= y && shape.getY() <= y + height) {
                    selectedShapes.add(shape);
                }
            }
        } else {
            isDrawing = false;
            currentShape = null;
        }
    }

    // Обработчик для движения мыши при зажатой клавише
    @FXML
    private void onMouseDragged(MouseEvent event) {
        if (isDragging) {
            // Вычисляем смещение курсора
            double offsetX = event.getX() - dragStartX;
            double offsetY = event.getY() - dragStartY;

            // Перемещаем все выделенные фигуры
            for (Shape shape : selectedShapes) {
                shape.setPosition(shape.getX() + offsetX, shape.getY() + offsetY);
            }

            // Обновляем начальные координаты для следующего шага
            dragStartX = event.getX();
            dragStartY = event.getY();

            // Перерисовываем холст
            redrawCanvas();
        } else if (isDrawing) {
            String shapeName = shapeListView.getSelectionModel().getSelectedItem(); // Получаем выбранное название фигуры
            Color color = colorPicker.getValue(); // Получаем цвет
            double size = Double.parseDouble(sizeInput.getText()); // Получаем размер фигуры
            GraphicsContext gc = canvas.getGraphicsContext2D();

            if (currentShape == null) {
                currentShape = createShapeByName(shapeName, color, size);
            }

            if (currentShape != null) {
                // Устанавливаем позицию фигуры на место курсора
                currentShape.setPosition(event.getX(), event.getY());
                currentShape.draw(gc);

                // Добавляем фигуру в список и стек для отмены
                shapes.add(currentShape);
                undoStack.push(new UndoAction(currentShape, UndoAction.Type.ADD)); // Сохраняем действие добавления фигуры

                // Создаем новую фигуру для следующего рисования
                currentShape = createShapeByName(shapeName, color, size);
            } else {
                showAlert("Ошибка", "Неверное название фигуры.");
            }
        } else if (isSelecting && selectionRect != null) {
            // Рисуем прямоугольник выделения
            double x = Math.min(event.getX(), selectionRect.getX());
            double y = Math.min(event.getY(), selectionRect.getY());
            double width = Math.abs(event.getX() - selectionRect.getX());
            double height = Math.abs(event.getY() - selectionRect.getY());

            selectionRect.setX(x);
            selectionRect.setY(y);
            selectionRect.setWidth(width);
            selectionRect.setHeight(height);

            // Перерисовываем холст с выделением
            redrawCanvas();
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setStroke(Color.BLACK);
            gc.strokeRect(x, y, width, height);
        }
    }

    // Метод для отмены последнего действия
    public void onUndo() {
        if (!undoStack.isEmpty()) {
            UndoAction lastAction = undoStack.pop();

            if (lastAction.getType() == UndoAction.Type.ADD) {
                // Если действие было добавлением фигуры, удаляем её
                shapes.remove(lastAction.getShape());
            } else if (lastAction.getType() == UndoAction.Type.COLOR_CHANGE) {
                // Если действие было изменением цвета, восстанавливаем старый цвет для всех фигур
                Map<Shape, Color> oldColors = lastAction.getOldColors();
                for (Map.Entry<Shape, Color> entry : oldColors.entrySet()) {
                    entry.getKey().setColor(entry.getValue());
                }
            }

            redrawCanvas();
        }
    }

    // Перерисовываем холст с учетом удаленных фигур
    private void redrawCanvas() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Shape shape : shapes) {
            shape.draw(gc);
        }
    }

    // Включение режима выделения
    @FXML
    private void onSelectMode() {
        isSelecting = true;
        modeLabel.setText("Режим: Выделение"); // Обновляем текст метки
    }

    // Включение режима рисования
    @FXML
    private void onDrawMode() {
        isSelecting = false;
        modeLabel.setText("Режим: Рисование"); // Обновляем текст метки
    }

    // Изменение цвета выбранных фигур
    @FXML
    private void onChangeColor() {
        Color newColor = colorPicker.getValue();

        // Создаем список для хранения старых цветов и фигур
        Map<Shape, Color> oldColors = new HashMap<>();

        // Для каждой выделенной фигуры сохраняем старый цвет и изменяем на новый
        for (Shape shape : selectedShapes) {
            oldColors.put(shape, shape.getColor()); // Сохраняем старый цвет
            shape.setColor(newColor); // Устанавливаем новый цвет
        }

        // Добавляем действие изменения цвета в стек отмены
        if (!oldColors.isEmpty()) {
            undoStack.push(new UndoAction(oldColors, UndoAction.Type.COLOR_CHANGE));
        }

        redrawCanvas(); // Обновляем холст с новым цветом
    }

    // Вспомогательный класс для действия Undo
    private static class UndoAction {
        enum Type {ADD, COLOR_CHANGE}

        private Shape shape; // Для действий с одной фигурой (например, добавление)
        private Map<Shape, Color> oldColors; // Для действий с несколькими фигурами (например, изменение цвета)
        private Type type;

        public UndoAction(Shape shape, Type type) {
            this.shape = shape;
            this.type = type;
            this.oldColors = null;
        }

        public UndoAction(Map<Shape, Color> oldColors, Type type) {
            this.shape = null;
            this.type = type;
            this.oldColors = oldColors;
        }

        public Shape getShape() {
            return shape;
        }

        public Map<Shape, Color> getOldColors() {
            return oldColors;
        }

        public Type getType() {
            return type;
        }
    }
}