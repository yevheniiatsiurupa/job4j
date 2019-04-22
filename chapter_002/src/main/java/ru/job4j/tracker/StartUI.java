package ru.job4j.tracker;

/**
 * @version 1.0.
 * @since 22/04/2019.
 * @author Evgeniya Tsiurupa
 */
public class StartUI {

    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";
    /**
     * Константа меню для показа всех заявок.
     */
    private static final String SHOW_ALL = "1";
    /**
     * Константа меню для редактирования заявок.
     */
    private static final String EDIT = "2";
    /**
     * Константа меню для удаления заявок.
     */
    private static final String DELETE = "3";
    /**
     * Константа меню для поиска заявки по id.
     */
    private static final String FIND_BY_ID = "4";
    /**
     * Константа меню для поиска заявки по имени.
     */
    private static final String FIND_BY_NAME = "5";
    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";
    /**
     * Константа для ответа ДА.
     */
    private static final String YES = "ДА";
    /**
     * Константа для ответа НЕТ.
     */
    private static final String NO = "НЕТ";
    /**
     * Получение данных от пользователя.
     */
    public final Input input;
    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструктор, инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основной цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню: ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW_ALL.equals(answer)) {
                this.showAllItems();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FIND_BY_ID.equals(answer)) {
                this.findItemById();
            } else if (FIND_BY_NAME.equals(answer)) {
                this.findItemByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Метод реализует добавление новой заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки: ");
        String desc = this.input.ask("Введите описание заявки: ");
        long time = System.currentTimeMillis();
        Item item = new Item(name, desc, time);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
    }

    /**
     * Метод реализует показ всех заявок.
     */
    public void showAllItems() {
        System.out.println("------------ Показ существующих заявок --------------");
        Item[] show = this.tracker.findAll();
        for (int i = 0; i < show.length; i++) {
            System.out.println("Заявка" + (i + 1) + " - " + show[i].getName());
        }
    }

    /**
     * Метод реализует редактирование заявки.
     */
    public void editItem() {
        System.out.println("------------ Редактирование заявок --------------");
        String id = this.input.ask("Введите id заявки, которую необходимо отредактировать: ");
        String answerOne = this.input.ask("Редактируем имя заявки? ДА / НЕТ");
        String name = null;
        if (YES.equals(answerOne)) {
            name = this.input.ask("Введите новое имя для заявки: ");
        } else if (NO.equals(answerOne)) {
            name = this.tracker.findById(id).getName();
        }
        String answerTwo = this.input.ask("Редактируем описание заявки? ДА / НЕТ");
        String desc = null;
        if (YES.equals(answerTwo)) {
            desc = this.input.ask("Введите новое описание заявки: ");
        } else if (NO.equals(answerTwo)) {
            desc = this.tracker.findById(id).getDesc();
        }
        long time = System.currentTimeMillis();
        Item item = new Item(name, desc, time);
        boolean result = this.tracker.replace(id, item);
        if (result) {
            System.out.println("Редактирование прошло успешно.");
        } else {
            System.out.println("Редактирование не выполнено.");
        }
    }

    /**
     * Метод реализует удаление заявки.
     */
    public void deleteItem() {
        System.out.println("------------ Удаление заявок --------------");
        String id = this.input.ask("Введите id заявки, которую необходимо удалить: ");
        boolean result = this.tracker.delete(id);
        if (result) {
            System.out.println("Удаление заявки прошло успешно.");
        } else {
            System.out.println("Удаление заявки не выполнено.");
        }
    }

    /**
     * Метод реализует поиск заявки по id.
     */
    public void findItemById() {
        System.out.println("------------ Поиск заявок по id --------------");
        String id = this.input.ask("Введите id заявки, которую необходимо найти: ");
        Item result = this.tracker.findById(id);
        System.out.println("Имя заявки - " + result.getName());
        System.out.println("Описание заявки - " + result.getDesc());
    }

    /**
     * Метод реализует поиск заявок по имени.
     */
    public void findItemByName() {
        System.out.println("------------ Поиск заявок по имени --------------");
        String id = this.input.ask("Введите имя заявки, которую необходимо найти: ");
        Item[] result = this.tracker.findByName(id);
        for (int i = 0; i < result.length; i++) {
            System.out.println("Заявка с номером id - " + result[i].getId());
        }
    }

    /**
     * Метод выводит на консоль главное меню.
     */
    private void showMenu() {
        System.out.println("");
        System.out.println("Menu.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all Items");
        System.out.println("2. Edit Item");
        System.out.println("3. Delete Item");
        System.out.println("4. Find Item by Id");
        System.out.println("5. Find Items by name");
        System.out.println("6. Exit Program");
    }

    /**
     * Запуск программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
