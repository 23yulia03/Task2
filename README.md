## Task2
Фигуры + Карандаши.

# Описание

Данное JavaFX-приложение предоставляет графический интерфейс для рисования различных геометрических фигур на холсте. Пользователь может ввести название фигуры, выбрать её размер и цвет, после чего фигура отобразится на холсте в месте нахождения курсора. Приложение реализует паттерн *Фабричный метод* для создания фигур, обеспечивая возможность расширения с добавлением новых типов фигур, и реализует паттерн *Компоновщик* для для группировки фигур и унификации работы с одиночными и составными объектами. 

### Основные компоненты:

1. **HelloApplication**  
   Основной класс приложения. Отвечает за запуск интерфейса и загрузку FXML-файла.

2. **HelloController**  
   Контроллер, который обрабатывает пользовательский ввод, взаимодействует с фабрикой фигур, а также управляет рисованием и очисткой холста. Реализует:
  - Взаимодействие с `ShapeFactory` для создания объектов фигур.
  - Обработку событий, таких как нажатие, перемещение, отпускание мыши.
  - Реализацию кнопок "Очистить" и "Отменить".

3. **ShapeFactory**  
   Класс-фабрика, который создаёт объекты фигур в зависимости от переданного названия фигуры, её цвета и размеров. Поддерживает следующие фигуры:
  - Линия
  - Квадрат
  - Треугольник
  - Круг
  - Угол
  - Пятиугольник

4. **Shape**  
   Абстрактный класс, который является суперклассом для всех фигур. Содержит методы:
  - `draw(GraphicsContext gr)` — абстрактный метод для отрисовки фигуры.
  - `area()` — абстрактный метод, возвращающий площадь фигуры.
  - `clone()` — метод для создания копии объекта.
  - Общие свойства:
    - Цвет (`Color color`)
    - Позиция (`x`, `y`)
    - Ширина и высота (для удобного размещения).

5. **ShapeGroup**
    Класс `ShapeGroup` расширяет абстрактный класс `Shape` и реализует возможность группировки фигур.
   Основной функционал:
   - Хранение коллекции объектов `Shape`
   - Метод `addShape(Shape shape)` — добавляет фигуру в группу
   - Метод `removeShape(Shape shape)` — удаляет фигуру из группы
   - Метод `clear()` — очищает всю группу
   - Метод `draw(GraphicsContext gc)` — отрисовывает все фигуры группы
   - Метод `area()` — возвращает суммарную площадь всех фигур
   - Метод `clone()` — глубокое копирование всей группы
   - Метод `contains(double x, double y)` — проверяет попадание точки в любую фигуру группы
   - Метод `descriptor()` — возвращает строковое описание содержимого группы

6. **Классы фигур**  
   Реализация конкретных фигур:
  - **Line** — рисует линию определённой длины.
  - **Angle** — рисует угол (как две пересекающиеся линии).
  - **Square** — рисует квадрат заданного размера.
  - **Triangle** — рисует равнобедренный треугольник с заданными основанием и высотой.
  - **Circle** — рисует круг с заданным радиусом.
  - **Pentagon** — рисует правильный пятиугольник.

---

### Реализованная функциональность:

1. **Рисование фигур**  
   Пользователь выбирает тип фигуры из списка, задаёт её цвет и размер через интерфейс. Фигура рисуется в позиции курсора мыши.

2. **Очистка холста**  
   При нажатии на кнопку "Очистить" все фигуры удаляются, а холст становится пустым.

3. **Отмена последнего действия**  
   При нажатии на кнопку "Отменить" удаляется последняя добавленная фигура.

4. **Группировка фигур**
   Реализована возможность объединять несколько фигур в одну группу (ShapeGroup). Это позволяет выполнять действия над всей группой, как над одной фигурой.

5. **Обработка ошибок**  
   Если пользователь пытается нарисовать фигуру с неверными параметрами, отображается сообщение об ошибке.

---

### Технологии

- **JavaFX** — для построения графического интерфейса и обработки пользовательского ввода.
- **FXML** — для декларативного описания интерфейса.
- **Паттерн "Фабричный метод"** — для создания фигур.
- **Паттерн "Компоновщик"** — для группировки фигур и унификации работы с одиночными и составными объектами.

---

### Демонстрация работы программы

Приложение предоставляет простой и интуитивный интерфейс:

1. **Список доступных фигур**:  
   Линия, квадрат, треугольник, круг, угол, пятиугольник.
2. **Цвет**: Выбирается через `ColorPicker`.
3. **Размер**: Указывается в текстовом поле.

---

### Архитектура

- Программа следует принципам ООП.
- Использует паттерн *Фабричный метод* для создания объектов.
- Использует паттерн **Компоновщик** для создания групп фигур и их совместного управления.
- Легко расширяется:
  1. Добавление новых типов фигур.
  2. Расширение логики группировки.
  3. Возможность интеграции дополнительных операций над фигурами (например, масштабирование, поворот, экспорт в SVG и др.).
  4. Все фигуры реализуют общий интерфейс через абстрактный класс **Shape**, что позволяет единообразно обращаться как к одиночной фигуре, так и к группе.

## Демонстрация работы программы
![Рабочее окно программы](https://github.com/23yulia03/Task2/blob/develop/img/screen1.png)

![Рабочее окно программы](https://github.com/23yulia03/Task2/blob/develop/img/screen2.png)

## Архитектура
![Вывод на экран Диаграммы Классов](https://github.com/23yulia03/Task2/blob/develop/img/ClassDiagram-task2+Componovshik.png)

## Структура проекта

```bash

com/example/task2
├── HelloApplication.java     # Основной класс приложения
└── HelloController.java      # Контроллер

model
├── fabrica
│   └── ShapeFactory.java     # Фабрика фигур
├── memento
│   ├── CareTaker.java        # Класс-хранитель для отмены действий
│   └── Memento.java          # Снимок состояния (списка фигур)
└── shapes
    ├── Angle.java            # Реализация угла
    ├── Circle.java           # Реализация круга
    ├── Line.java             # Реализация линии
    ├── Pentagon.java         # Реализация пятиугольника
    ├── Shape.java            # Абстрактный суперкласс для всех фигур
    ├── ShapeGroup.java       # Класс-компоновщик для группировки фигур (паттерн Компоновщик)
    ├── Square.java           # Реализация квадрата
    └── Triangle.java         # Реализация треугольника
