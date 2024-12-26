package com.example.task2;

//Интерфейс для строителя, который создает объект индикатора
public interface Builder {
    void setView(int N, char norm, char select);
    void lineBounds(float start, float stop);
    void linePaint(float measure);
    void lineMark(String measure);
    void addTitle(String name);
    Indicator build();
}

