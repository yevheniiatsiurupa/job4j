package ru.job4j.tracker;

import javax.swing.tree.DefaultTreeCellEditor;
import java.util.ArrayList;
import java.util.List;

public class MenuTracker {
    /**
     * @param input хранит ссылку на объект.
     */
    private Input input;

    /**
     * @param tracker хранит ссылку на объект.
     */
    private Tracker tracker;

    /**
     * @param actions хранит ссылку на массив типа UserAction.
     */
    private List<UserAction> actions = new ArrayList<>();

    /**
     * Константа меню для добавления новой заявки.
     */
    private static final int ADD = 0;
    /**
     * Константа меню для показа всех заявок.
     */
    private static final int SHOW_ALL = 1;
    /**
     * Константа меню для редактирования заявок.
     */
    private static final int EDIT = 2;
    /**
     * Константа меню для удаления заявок.
     */
    private static final int DELETE = 3;
    /**
     * Константа меню для поиска заявки по id.
     */
    private static final int FIND_BY_ID = 4;
    /**
     * Константа меню для поиска заявки по имени.
     */
    private static final int FIND_BY_NAME = 5;
    /**
     * Константа для выхода из цикла.
     */
    private static final int EXIT = 6;

    /**
     * Конструктор.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод для получения массива меню.
     *
     * @return длину массива
     */
    public int getActionsLength() {
        return this.actions.size();
    }

    /**
     * Метод заполняет массив actions вариантами возможных действий.
     */
    public void fillActions() {
        this.actions.add(new AddItem(ADD, "Add new Item"));
        this.actions.add(new ShowAllItems(SHOW_ALL, "Show all Items"));
        this.actions.add(new EditItem(EDIT, "Edit Item"));
        this.actions.add(new DeleteItem(DELETE, "Delete Item"));
        this.actions.add(new FindItemByID(FIND_BY_ID, "Find Item by Id"));
        this.actions.add(new FindItemByName(FIND_BY_NAME, "Find Items by name"));
        this.actions.add(new ExitProgram(EXIT, "Exit Program"));
    }

    /**
     * Метод в зависимости от ключа, выполняет определенные действия.
     * @param key ключ операции.
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        System.out.println("");
        System.out.println("Menu.");
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }


    public class AddItem extends BaseAction {
        /**
         * Конструктор.
         * Ссылается на конструктор родительского класса.
         * @param key ключ операции.
         * @param name название операции.
         */
        public AddItem(int key, String name) {
            super(key, name);
        }

        /**
         * Метод добавляет новую заявку в трекер.
         * @param input объект типа Input.
         * @param tracker объект типа Tracker.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки: ");
            String desc = input.ask("Введите описание заявки: ");
            long time = System.currentTimeMillis();
            Item item = new Item(name, desc, time);
            tracker.add(item);
            System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");

        }
    }

    public class ShowAllItems extends BaseAction {
        /**
         * Конструктор.
         * Ссылается на конструктор родительского класса.
         * @param key ключ операции.
         * @param name название операции.
         */
        public ShowAllItems(int key, String name) {
            super(key, name);
        }

        /**
         * Метод добавляет показывает все заявки, внесенные в трекер.
         * @param input объект типа Input.
         * @param tracker объект типа Tracker.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Показ существующих заявок --------------");
            Item[] show = tracker.findAll();
            for (int i = 0; i < show.length; i++) {
                System.out.println(String.format("Заявка %d - %s", (i + 1), show[i].getName()));
            }
        }
    }

    public class EditItem extends BaseAction {
        /**
         * Конструктор.
         * Ссылается на конструктор родительского класса.
         * @param key ключ операции.
         * @param name название операции.
         */
        public EditItem(int key, String name) {
            super(key, name);
        }

        /**
         * Метод редактирует заявку по номеру ID.
         * @param input объект типа Input.
         * @param tracker объект типа Tracker.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Редактирование заявок --------------");
            String id = input.ask("Введите id заявки, которую необходимо отредактировать: ");
            String name = input.ask("Введите имя заявки: ");
            String desc = input.ask("Введите описание заявки: ");
            long time = System.currentTimeMillis();
            Item item = new Item(name, desc, time);
            boolean result = tracker.replace(id, item);
            if (result) {
                System.out.println("Редактирование прошло успешно.");
            } else {
                System.out.println("Редактирование не выполнено.");
            }
        }
    }

    public class DeleteItem extends BaseAction {
        /**
         * Конструктор.
         * Ссылается на конструктор родительского класса.
         * @param key ключ операции.
         * @param name название операции.
         */
        public DeleteItem(int key, String name) {
            super(key, name);
        }

        /**
         * Метод удаляет заявку по номеру ID.
         * @param input объект типа Input.
         * @param tracker объект типа Tracker.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Удаление заявок --------------");
            String id = input.ask("Введите id заявки, которую необходимо удалить: ");
            boolean result = tracker.delete(id);
            if (result) {
                System.out.println("Удаление заявки прошло успешно.");
            } else {
                System.out.println("Удаление заявки не выполнено.");
            }
        }
    }

    public class FindItemByID extends BaseAction {
        /**
         * Конструктор.
         * Ссылается на конструктор родительского класса.
         * @param key ключ операции.
         * @param name название операции.
         */
        public FindItemByID(int key, String name) {
            super(key, name);
        }

        /**
         * Метод находит заявку по номеру ID.
         * @param input объект типа Input.
         * @param tracker объект типа Tracker.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявок по id --------------");
            String id = input.ask("Введите id заявки, которую необходимо найти: ");
            Item result = tracker.findById(id);
            if (result != null) {
                System.out.println("Имя заявки - " + result.getName());
                System.out.println("Описание заявки - " + result.getDesc());
            }
        }
    }

    public class FindItemByName extends BaseAction {
        /**
         * Конструктор.
         * Ссылается на конструктор родительского класса.
         * @param key ключ операции.
         * @param name название операции.
         */
        public FindItemByName(int key, String name) {
            super(key, name);
        }

        /**
         * Метод находит заявку по названию.
         * @param input объект типа Input.
         * @param tracker объект типа Tracker.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявок по имени --------------");
            String id = input.ask("Введите имя заявки, которую необходимо найти: ");
            Item[] result = tracker.findByName(id);
            for (int i = 0; i < result.length; i++) {
                System.out.println("Заявка с номером id - " + result[i].getId());
            }
        }
    }

    public class ExitProgram extends BaseAction {
        /**
         * Конструктор.
         * Ссылается на конструктор родительского класса.
         * @param key ключ операции.
         * @param name название операции.
         */
        public ExitProgram(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
        }
    }
}
