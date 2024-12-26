package com.example.task2;

public class Director { //Индикатор
    public void constructIndicator(Builder builder, float start, float stop, float measure) {
        builder.lineBounds(start, stop);
        builder.linePaint(measure);
        builder.lineMark(String.format("%.1f", measure));
    }
}
