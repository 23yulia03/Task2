package com.example.task2;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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
    private ColorPicker strokeColorPicker;
    @FXML
    private Slider strokeWidthSlider;
    @FXML
    private ComboBox<String> fillStyleComboBox;
    @FXML
    private ComboBox<String> animationComboBox;

    private ShapeFactory shapeFactory = new ShapeFactory();
    private ArrayList<Shape> shapes = new ArrayList<>();
    private Stack<Shape> undoStack = new Stack<>();
    private PriorityQueue<String> shapeQueue = new PriorityQueue<>();
    private Map<String, Integer> shapeCountMap = new HashMap<>();

    private boolean isDrawing = false;
    private Shape currentShape = null;

    // Инициализация ListView
    public void initialize() {
        shapeListView.setItems(FXCollections.observableArrayList(
                "Линия", "Квадрат", "Треугольник", "Круг", "Угол", "Пятиугольник"
        ));
        fillStyleComboBox.setItems(FXCollections.observableArrayList(
                "Сплошной", "Пунктир", "Штриховка"
        ));
        animationComboBox.setItems(FXCollections.observableArrayList(
                "Нет", "Мерцание", "Постепенное появление"
        ));
    }

    // Метод для очистки холста
    public void onClear() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        shapes.clear();
        undoStack.clear();
        shapeQueue.clear();
        shapeCountMap.clear();
    }

    // Создаёт фигуру на основе выбранного названия
    private Shape createShapeByName(String shapeName, Color fillColor, Color strokeColor, double strokeWidth, String fillStyle, String animationType, double size) {
        switch (shapeName) {
            case "Линия":
                return shapeFactory.createShape("Line", fillColor, strokeColor, strokeWidth, fillStyle, animationType, size);
            case "Квадрат":
                return shapeFactory.createShape("Square", fillColor, strokeColor, strokeWidth, fillStyle, animationType, size);
            case "Треугольник":
                return shapeFactory.createShape("Triangle", fillColor, strokeColor, strokeWidth, fillStyle, animationType, size, size);
            case "Круг":
                return shapeFactory.createShape("Circle", fillColor, strokeColor, strokeWidth, fillStyle, animationType, size);
            case "Угол":
                return shapeFactory.createShape("Angle", fillColor, strokeColor, strokeWidth, fillStyle, animationType, size);
            case "Пятиугольник":
                return shapeFactory.createShape("Pentagon", fillColor, strokeColor, strokeWidth, fillStyle, animationType, size);
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
        isDrawing = true;
        onMouseDragged(event);
    }

    // Обработчик для отпускания мыши
    @FXML
    private void onMouseReleased(MouseEvent event) {
        isDrawing = false;
        currentShape = null;
    }

    // Обработчик для движения мыши при зажатой клавише
    @FXML
    private void onMouseDragged(MouseEvent event) {
        if (isDrawing) {
            String shapeName = shapeListView.getSelectionModel().getSelectedItem(); // Получаем выбранное название фигуры
            Color fillColor = colorPicker.getValue(); // Получаем цвет заливки
            Color strokeColor = strokeColorPicker.getValue(); // Получаем цвет контура
            double strokeWidth = strokeWidthSlider.getValue(); // Получаем толщину контура
            String fillStyle = fillStyleComboBox.getSelectionModel().getSelectedItem(); // Получаем стиль заливки
            String animationType = animationComboBox.getSelectionModel().getSelectedItem(); // Получаем тип анимации
            double size = Double.parseDouble(sizeInput.getText()); // Получаем размер фигуры
            GraphicsContext gc = canvas.getGraphicsContext2D();

            if (currentShape == null) {
                currentShape = createShapeByName(shapeName, fillColor, strokeColor, strokeWidth, fillStyle, animationType, size);
            }

            if (currentShape != null) {
                // Устанавливаем позицию фигуры на место курсора
                currentShape.setPosition(event.getX(), event.getY());
                currentShape.draw(gc);

                // Добавляем фигуру в список и стек для отмены
                shapes.add(currentShape);
                undoStack.push(currentShape);

                // Обновляем статистику
                shapeQueue.add(shapeName);
                shapeCountMap.put(shapeName, shapeCountMap.getOrDefault(shapeName, 0) + 1);

                // Создаем новую фигуру для следующего рисования
                currentShape = createShapeByName(shapeName, fillColor, strokeColor, strokeWidth, fillStyle, animationType, size);
            } else {
                showAlert("Ошибка", "Неверное название фигуры.");
            }
        }
    }

    // Метод для отмены последнего действия
    public void onUndo() {
        if (!undoStack.isEmpty()) {
            Shape lastShape = undoStack.pop();
            if (lastShape instanceof Line) {
                ((Line) lastShape).stopAnimation();
            } else if (lastShape instanceof Square) {
                ((Square) lastShape).stopAnimation();
            } else if (lastShape instanceof Triangle) {
                ((Triangle) lastShape).stopAnimation();
            } else if (lastShape instanceof Circle) {
                ((Circle) lastShape).stopAnimation();
            } else if (lastShape instanceof Angle) {
                ((Angle) lastShape).stopAnimation();
            } else if (lastShape instanceof Pentagon) {
                ((Pentagon) lastShape).stopAnimation();
            }
            shapes.remove(lastShape);
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
}