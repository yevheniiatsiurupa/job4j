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
        int index = this.findIndex(id);
        if (index != -1) {
            result = this.container.set(index, userNew);
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
        int index = this.findIndex(id);
        if (index != -1) {
            result = this.container.remove(index);
        }
        return result;
    }

    /**
     * Метод находит элемент в контейнере по номеру.
     * @param id номер элемента, который удаляется.
     * @return возвращает результат выполнения операции.
     */
    public int findIndex(String id) {
        int index = -1;
        int count = 0;
        for (T tmp : container) {
            if (id.equals(tmp.getId())) {
               index = count;
                break;
            }
            count++;
        }
        return index;
    }

    public T findById(String id) {
        return this.container.get(this.findIndex(id));
    }
}
