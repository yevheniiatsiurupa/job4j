package ru.job4j.tracker;

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
     * Метод заполняет массив.
     */
    public void fillActions() {
        this.actions.add(new AddItem());
        this.actions.add(new ShowAllItems());
        this.actions.add(new EditItem());
        this.actions.add(new DeleteItem());
        this.actions.add(new FindItemByID());
        this.actions.add(new FindItemByName());
        this.actions.add(new ExitProgram());
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

    public class AddItem implements UserAction {
        @Override
        public int key() {
            return ADD;
        }

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

        @Override
        public String info() {
            return String.format("%d. %s", this.key(), "Add new Item");
        }
    }

    public class ShowAllItems implements UserAction {
        @Override
        public int key() {
            return SHOW_ALL;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Показ существующих заявок --------------");
            Item[] show = tracker.findAll();
            for (int i = 0; i < show.length; i++) {
                System.out.println(String.format("Заявка %d - %s", (i + 1), show[i].getName()));
            }
        }

        @Override
        public String info() {
            return String.format("%d. %s", this.key(), "Show all Items");
        }
    }

    public class EditItem implements UserAction {
        @Override
        public int key() {
            return EDIT;
        }

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

        @Override
        public String info() {
            return String.format("%d. %s", this.key(), "Edit Item");
        }
    }

    public class DeleteItem implements UserAction {
        @Override
        public int key() {
            return DELETE;
        }

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

        @Override
        public String info() {
            return String.format("%d. %s", this.key(), "Delete Item");
        }
    }

    public class FindItemByID implements UserAction {
        @Override
        public int key() {
            return FIND_BY_ID;
        }

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

        @Override
        public String info() {
            return String.format("%d. %s", this.key(), "Find Item by Id");
        }
    }

    public class FindItemByName implements UserAction {
        @Override
        public int key() {
            return FIND_BY_NAME;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявок по имени --------------");
            String id = input.ask("Введите имя заявки, которую необходимо найти: ");
            Item[] result = tracker.findByName(id);
            for (int i = 0; i < result.length; i++) {
                System.out.println("Заявка с номером id - " + result[i].getId());
            }
        }

        @Override
        public String info() {
            return String.format("%d. %s", this.key(), "Find Items by name");
        }
    }

    public class ExitProgram implements UserAction {
        @Override
        public int key() {
            return EXIT;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
        }

        @Override
        public String info() {
            return String.format("%d. %s", this.key(), "Exit Program");
        }
    }
}
