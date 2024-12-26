package com.example.task2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.util.Duration;

import java.io.File;

public class HelloController {

    @FXML
    private Pane indicatorPane;
    @FXML
    private Label startField, stopField, measureField;
    @FXML
    private Button startStopButton, applyDelayButton;
    @FXML
    private ImageView screen;
    @FXML
    private TextField delayField;
    @FXML
    private Button chooseFolderButton;

    private Aggregate aggregate;
    private Iterator iter;
    private Timeline time = new Timeline();
    private boolean isPlaying = false;
    private int currentImageIndex = 0;
    private Director director = new Director();

    public void initialize() {
        aggregate = new ConcreteAggregate("src/main/resources/img");
        iter = aggregate.getIterator();
        time.setCycleCount(Timeline.INDEFINITE);
        updateTimeline(1000);
        screen.setPreserveRatio(false);

        startField.setText("1");
        updateStopField();
        updateMeasureField();

    }


    // Обработка применения задержки, введенной пользователем
    @FXML
    public void applyDelay() {
        if (delayField != null && !delayField.getText().isEmpty()) {
            try {
                int newDelay = Integer.parseInt(delayField.getText());
                updateTimeline(newDelay);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Пожалуйста, введите действительный номер для подтверждения задержки.");
                alert.showAndWait();
            }
        }
    }

    // Обновление временной шкалы с новой задержкой
    private void updateTimeline(int delayMillis) {
        time.stop();
        time.getKeyFrames().clear();
        time.getKeyFrames().add(new KeyFrame(Duration.millis(delayMillis), new EvHandler()));
        if (isPlaying) {
            time.play();
        }
    }

    // Обработчик событий для обработки переходов между слайд-шоу
    private class EvHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Image image = (Image) iter.next();
            if (image != null) {
                screen.setImage(image);
                currentImageIndex++;
                updateMeasureField();
            } else {
                iter.reset();
                currentImageIndex = 0;
                updateMeasureField();
                image = (Image) iter.next();
                if (image != null) {
                    screen.setImage(image);
                }
            }
        }
    }

    // Метод переключения анимации между воспроизведением и паузой
    @FXML
    public void toggleAnimation() {
        float start = Float.parseFloat(startField.getText());
        float stop = Float.parseFloat(stopField.getText());
        float measure = Float.parseFloat(measureField.getText());

        indicatorPane.getChildren().clear();

        Builder builder = new ConcreteBuilder();
        director.constructIndicator(builder, start, stop, measure);

        Indicator indicator = builder.build();
        indicator.show(indicatorPane);

        if (isPlaying) {
            time.pause();
            startStopButton.setText("⏹");
            indicatorPane.setVisible(false);
        } else {
            startStopButton.setText("▶");
            time.play();
            indicatorPane.setVisible(true);
        }
        isPlaying = !isPlaying;
    }

    // Метод отображения следующего изображения в слайд-шоу
    @FXML
    public void next() {
        Image image = (Image) iter.next();
        if (image != null) {
            screen.setImage(image);
            currentImageIndex++;
            updateMeasureField();
        } else {
            iter.reset();
            currentImageIndex = 0;
            updateMeasureField();
            image = (Image) iter.next();
            if (image != null) {
                screen.setImage(image);
            }
        }
    }

    //Метод отображения предыдущего изображения в слайд-шоу
    @FXML
    public void preview() {
        Image image = (Image) iter.preview();
        if (image != null) {
            screen.setImage(image);
            currentImageIndex--;
            updateMeasureField();
        }
    }

    // Метод выбора папки с изображениями
    @FXML
    public void chooseFolder() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(chooseFolderButton.getScene().getWindow());

        if (selectedDirectory != null) {
            aggregate = new ConcreteAggregate(selectedDirectory.getAbsolutePath());
            iter = aggregate.getIterator();
            currentImageIndex = 0;
            updateStopField();
            updateMeasureField();
            Image image = (Image) iter.next();
            if (image != null) {
                screen.setImage(image);
            }
        }
    }

    // Метод обновления поля "стоп" с указанием общего количества изображений
    private void updateStopField() {
        int imageCount = aggregate.getImageCount();
        stopField.setText(String.valueOf(imageCount));
    }

    // Метод обновления поля измерения и положения индикатора
    private void updateMeasureField() {
        measureField.setText(String.valueOf(currentImageIndex + 1));
        float measure = Float.parseFloat(measureField.getText());
        float start = Float.parseFloat(startField.getText());
        float stop = Float.parseFloat(stopField.getText());

        indicatorPane.getChildren().clear();

        Builder builder = new ConcreteBuilder();
        director.constructIndicator(builder, start, stop, measure);

        Indicator indicator = builder.build();
        indicator.show(indicatorPane);
    }
}
