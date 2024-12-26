## Task3
Паттерн "Хранитель" для сохранения состояния фигур и перемещения.

# Описание

Приложение на JavaFX представляет собой графический редактор для управления геометрическими фигурами. В данном проекте реализован паттерн *Хранитель* для сохранения и восстановления состояния фигур. Пользователь может перемещать фигуры на холсте, а их состояния сохраняются для возможности отмены изменений.

---

### Основные компоненты:

1. **HelloApplication**  
   Главный класс приложения, который отвечает за запуск и загрузку интерфейса.

2. **HelloController**  
   Контроллер приложения, который управляет логикой взаимодействия пользователя с интерфейсом:
    - Обработка событий мыши для захвата, перетаскивания и перемещения фигур.
    - Использование паттерна "Хранитель" для сохранения состояния фигур до начала их изменения.

3. **CareTaker**  
   Класс, управляющий хранилищем состояний (объектов `Memento`) с использованием очереди.
    - Добавление (`push`) нового состояния в очередь.
    - Извлечение (`poll`) последнего сохранённого состояния.

4. **Memento**  
   Класс, реализующий паттерн "Хранитель" для сохранения состояния фигуры:
    - Сохраняет свойства фигуры, такие как ширина и цвет обводки.
    - Позволяет восстановить сохранённое состояние фигуры.

5. **FXML-файл**  
   Описывает интерфейс с использованием элемента `Pane` для отрисовки фигур и возможности их взаимодействия.

---

### Реализованная функциональность:

1. **Перемещение фигур**  
   Пользователь может перемещать фигуры, кликая по ним и перетаскивая мышкой. Фигура, захваченная для перемещения, выделяется красной обводкой.

2. **Сохранение состояния фигур**  
   Перед началом перемещения фигуры её состояние сохраняется с использованием объекта `Memento`.

3. **Восстановление состояния**  
   После завершения перемещения пользователь может восстановить первоначальное состояние фигуры.

4. **Инициализация фигур**  
   При запуске приложения на холсте отображаются несколько предустановленных фигур:
    - Прямоугольники, круги и треугольник с различными свойствами (размер, цвет, радиус закругления).

5. **Визуальное выделение**  
   Захваченная фигура выделяется, изменяя цвет и толщину её обводки.

---

### Технологии

- **JavaFX** — для создания графического интерфейса.
- **FXML** — для декларативного описания интерфейса.
- **Паттерн "Хранитель"** — для сохранения и восстановления состояний объектов.

---

### Архитектура

- **HelloApplication**: Запуск приложения.
- **HelloController**: Логика взаимодействия с фигурами.
- **CareTaker**: Хранилище состояний фигур.
- **Memento**: Сохранение состояния фигур.

---

## Демонстрация работы программы
![Рабочее окно программы](https://github.com/23yulia03/Task2/blob/task3-hranitel-memento/src/screenshots/img.png)

## Архитектура
![Вывод на экран Диаграммы Классов](https://github.com/23yulia03/Task2/blob/task3-hranitel-memento/src/screenshots/ClassDiagram-task3.png) 

---

### Структура проекта

```plaintext
com/example/task3
├── CareTaker.java            # Хранилище состояний фигур
├── HelloApplication.java     # Основной класс приложения
├── HelloController.java      # Контроллер
├── Memento.java              # Снимок состояния фигуры
├── hello-view.fxml           # FXML-файл интерфейса
