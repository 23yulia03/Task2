package com.example.task2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.ToggleGroup;

public class HelloController {

    @FXML
    private TextField summaField;

    @FXML
    private Label bigTipResult;

    @FXML
    private Label mediumTipResult2;

    @FXML
    private Label smallTipResult;

    @FXML
    private RadioButton radio9;

    @FXML
    private RadioButton radio10;

    private ToggleGroup tipGroup;

    private Procent procent = new Procent();

    @FXML
    public void initialize() {
        // Инициализация группы для радиокнопок
        tipGroup = new ToggleGroup();
        radio9.setToggleGroup(tipGroup);
        radio10.setToggleGroup(tipGroup);

        radio9.setSelected(true);
    }

    // Метод для расчета малых чаевых (3%)
    @FXML
    void m_mal_ch(ActionEvent event) {
        double sum = Double.parseDouble(summaField.getText());
        double result = procent.countSumTrunc(sum, 3);
        smallTipResult.setText(String.format("Сумма: %.0f р.", result));
    }

    // Метод для расчета чаевых (9% или 10%)
    @FXML
    void m_sred_ch(ActionEvent event) {
        double sum = Double.parseDouble(summaField.getText());
        double result;

        // Проверяем, какая радиокнопка выбрана
        if (radio9.isSelected()) {
            result = procent.countSumTrunc(sum, 9);
        } else {
            result = procent.countSumTrunc(sum, 10);
        }

        mediumTipResult2.setText(String.format("Сумма: %.0f р.", result));
    }

    // Метод для расчета больших чаевых (15%)
    @FXML
    void m_big_ch(ActionEvent event) {
        double sum = Double.parseDouble(summaField.getText());
        double result = procent.countSumTrunc(sum, 15);
        bigTipResult.setText(String.format("Сумма: %.0f р.", result));
    }
}
