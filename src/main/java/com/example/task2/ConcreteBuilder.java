package com.example.task2;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;

public class ConcreteBuilder implements Builder {

    Indicator indicator = new Indicator();
    private float start, stop;

    @Override
    public void setView(int N, char norm, char select) {
        indicator.setLenght(N);
        indicator.setPaint(norm);
        indicator.setMetka(select);
    }

    @Override
    public void lineBounds(float start, float stop) {
        this.start = start;
        this.stop = stop;
        FlowPane pane = new FlowPane();
        Text text = new Text("" + start);
        Line line = new Line(5, 5, 200, 5);
        Text text1 = new Text("" + stop);
        pane.getChildren().addAll(text, line, text1);
        indicator.add(pane);
    }

    @Override
    public void linePaint(float measure) {
        AnchorPane pane = new AnchorPane();
        double fixedWidth = 200; // Фиксированная ширина
        double offset = fixedWidth * start / (stop - start);
        double x = fixedWidth * measure / (stop - start) - offset;
        SVGPath svgPath = new SVGPath();
        svgPath.setContent("M0 6L8 14L16 6V4H0V6Z");
        svgPath.setFill(Color.RED);
        svgPath.setStroke(Color.RED);
        svgPath.setScaleX(2.0);
        svgPath.setScaleY(2.0);
        svgPath.setTranslateX(x - 7);
        svgPath.setTranslateY(-13);
        pane.getChildren().add(svgPath);
        indicator.add(pane);
    }

    @Override
    public void lineMark(String measure) {
        AnchorPane pane = new AnchorPane();
        Text markText = new Text(measure);
        markText.setX(50);
        markText.setY(50);
        pane.getChildren().add(markText);
        indicator.add(pane);
    }

    @Override
    public void addTitle(String name) {
        AnchorPane pane = new AnchorPane();
        Text titleText = new Text(name);
        titleText.setX(50);
        titleText.setY(70);
        pane.getChildren().add(titleText);
        indicator.add(pane);
    }

    @Override
    public Indicator build() {
        return indicator;
    }
}
