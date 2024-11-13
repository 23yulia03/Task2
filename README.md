## Task2
Фабричный метод.

# Описание

Данное JavaFX-приложение предоставляет графический интерфейс для визуализации геометрических фигур на основании количества сторон, вводимого пользователем (от 0 до 5). Приложение позволяет рисовать следующие фигуры: круг, линия, угол, треугольник, квадрат и пятиугольник, соответствующие числу сторон.

### Основные компоненты:

- **HelloApplication** — основной класс, инициализирующий и запускающий приложение с пользовательским интерфейсом.
- **HelloController** — контроллер, управляющий вводом данных пользователем и рисованием фигур на холсте.
- **ShapeFactory** — фабричный класс для создания объектов фигур в зависимости от числа сторон.
- **Shape** — интерфейс, реализуемый всеми фигурами, содержащий методы:
    - `draw(GraphicsContext gr)` — рисует фигуру.
    - `descriptor()` — возвращает текстовое описание фигуры.

### Реализованные фигуры:

- **Circle (0 сторон)** — рисует круг.
- **Line (1 сторона)** — рисует отрезок.
- **Angle (2 стороны)** — рисует угол.
- **Triangle (3 стороны)** — рисует треугольник.
- **Square (4 стороны)** — рисует квадрат.
- **Pentagon (5 сторон)** — рисует пятиугольник.

## Функциональность

1. Пользователь вводит целое число от 0 до 5 в текстовое поле.
2. Программа рисует соответствующую фигуру на холсте.
3. Если ввод некорректен или фигура с указанным числом сторон не существует, отображается предупреждающее сообщение.
4. Холст очищается перед рисованием новой фигуры.

## Технологии

- **JavaFX** для создания графического интерфейса и работы с графикой.
- **FXML** для декларативного описания пользовательского интерфейса.

## Демонстрация работы программы
![Рабочее окно программы](https://github.com/23yulia03/Task2/blob/main/img/screenshot.png)

## Архитектура
![Вывод на экран Диаграммы Классов](https://github.com/23yulia03/Task2/blob/main/ClassDiagram-task2.png)

## Структура проекта

```bash
com/example/task2
├── HelloApplication.java     # Основной класс приложения
├── HelloController.java      # Контроллер
├── ShapeFactory.java         # Фабрика фигур
├── Shape.java                # Интерфейс фигур
├── Angle.java                # Реализация угла
├── Circle.java               # Реализация круга
├── Line.java                 # Реализация линии
├── Pentagon.java             # Реализация пятиугольника
├── Square.java               # Реализация квадрата
└── Triangle.java             # Реализация треугольника

