/**
 * Класс CareTaker, который управляет состояниями объектов (Memento)
 * с возможностью сохранения и восстановления состояния.
 */
package model;

import java.util.ArrayDeque;
import java.util.Queue;

public class CareTaker {

    private Queue<Memento> mementoList = new ArrayDeque<>();

    // Сохраняем состояние
    public void push(Memento state) {
        mementoList.add(state);
    }

    // Восстанавливаем последнее состояние
    public Memento poll() {
        return mementoList.poll();
    }

    // Проверяем, есть ли сохраненные состояния
    public boolean hasMementos() {
        return !mementoList.isEmpty();
    }
}