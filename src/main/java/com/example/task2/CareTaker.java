package com.example.task2;

import java.util.ArrayDeque;
import java.util.Queue;

public class CareTaker {
    // Очередь для хранения объектов Memento (состояний фигур)
    private final Queue<Memento> mementoList = new ArrayDeque<>();

    // Добавить состояние фигуры в очередь
    public void push(Memento state) {
        mementoList.add(state);
    }

    // Извлечь последнее сохраненное состояние фигуры из очереди
    public Memento poll() {
        return mementoList.poll();
    }
}
