package com.example.task2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private ListView<Shape> listView;
    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;

    private ObservableList<Shape> content;
    private GraphicsContext gc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Circle circle = new Circle();
        Rectangle rectangle = new Rectangle();
        Triangle triangle = new Triangle();

        content = FXCollections.observableArrayList(circle, rectangle, triangle);
        listView.setItems(content);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        gc = canvas.getGraphicsContext2D();
    }

    @FXML
    public void drawShape(MouseEvent mouseEvent) {
        int index = listView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            Shape shape = (Shape) content.get(index).clone();
            shape.setColor(colorPicker.getValue());
            shape.draw(gc, mouseEvent.getX(), mouseEvent.getY());
        }
    }
}