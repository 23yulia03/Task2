package com.example.task2;

//Интерфейс для итератора
public interface Iterator {
    boolean hasNext();
    Object next();
    Object preview();
    void reset();  // Сброс итератора
}

