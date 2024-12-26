package com.example.task2;

import javafx.scene.image.Image;

import java.util.List;

public class ConcreteIterator implements Iterator {
    private List<Image> images;
    private int currentIndex = 0;

    //Конструктор, принимающий список изображений
    public ConcreteIterator(List<Image> images) {
        this.images = images;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < images.size();  // Проверяем, есть ли следующее изображение
    }

    @Override
    public Object next() {
        if (hasNext()) {
            Image image = images.get(currentIndex);  // Берем следующее изображение
            currentIndex++;  // Увеличиваем индекс
            return image;
        }
        return null;
    }

    @Override
    public Object preview() {
        if (currentIndex > 0) {
            currentIndex--;  // Уменьшаем индекс для предыдущего изображения
            return images.get(currentIndex);
        }
        return null;
    }

    @Override
    public void reset() {
        currentIndex = 0;  // Сброс индекса
    }
}
