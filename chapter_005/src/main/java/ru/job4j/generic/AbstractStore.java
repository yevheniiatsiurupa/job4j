package ru.job4j.generic;

public abstract class AbstractStore<T extends Base> implements Store<T> {
    /**
     * Поле содержит хранилище T.
     */
    private SimpleArray<T> container = new SimpleArray<>(10);

    /**
     * Метод добавляет объект T в хранилище
     * @param user объект, который добавляется в контейнер.
     */
    @Override
    public void add(T user) {
        if (user != null) {
            container.add(user);
        }

    }

    /**
     * Метод заменяет объект в контейнере по номеру id.
     * @param id номер элемента, который заменяется.
     * @param userNew объект, на который заменяется элемент в контейнере.
     * @return возвращает результат выполнения операции.
     */
    public boolean replace(String id, T userNew) {
        boolean result = false;
        T[] allUsers = container.findAll();
        for (int i = 0; i < allUsers.length; i++) {
            if (id.equals(allUsers[i].getId())) {
                container.set(i, userNew);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метож удаляет объект из контейнера по id.
     * @param id номер элемента, который удаляется.
     * @return возвращает результат выполнения операции.
     */
    public boolean delete(String id) {
        boolean result = false;
        T[] allUsers = container.findAll();
        for (int i = 0; i < allUsers.length; i++) {
            if (id.equals(allUsers[i].getId())) {
                container.remove(i);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод находит элемент в контейнере по номеру.
     * @param id номер элемента, который удаляется.
     * @return возвращает результат выполнения операции.
     */
    public T findById(String id) {
        T result = null;
        for (T tmp : container) {
            if (id.equals(tmp.getId())) {
                result = tmp;
                break;
            }
        }
        return result;
    }

    /**
     * Метод показывает все элементы в контейнере.
     * @return возвращает массив элементов в контейнере.
     */
    public T[] findAll() {
        return container.findAll();
    }
}
