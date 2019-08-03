package ru.job4j.threads.nonblock;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0.
 * @since 02/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class NonBlockCache {
    /**
     * Хранилище объектов Base в виде ConcurrentHashMap.
     * Объекты хранятся по ключу id.
     */
    private ConcurrentHashMap<Integer, Base> storage;

    public NonBlockCache(ConcurrentHashMap<Integer, Base> storage) {
        this.storage = storage;
    }

    /**
     * Метод для добавления объекта в хранилище.
     */
    public Base add(Base model) {
        return storage.put(model.getId(), model);
    }

    /**
     * Метод для обновления объекта в хранилище.
     * Объект обновляется, если был найден элемент с таким же ключом в хранилище.
     * Если версия найденного объекта и добавляемого отличаются на 1
     * (то есть если добавляемый объект является следующей версией найденного, если
     * его не успел видоизменить другой поток), то он обновляется в хранилище
     * (значение по ключу становится model).
     * Если версии не являются последовательными (объект был видоизменен), то выбрасывается
     * исключение.
     * @param model модель на которую нужно обновить соответствующую модель из хранилища (по ключу).
     */
    public boolean update(Base model) {
        Integer key = model.getId();
        storage.computeIfPresent(key, (k, v) -> {
            int currVers = model.getVersion();
            int prevVers = storage.get(key).getVersion();
            if (currVers == prevVers) {
                model.setVersion(model.getVersion() + 1);
                return model;
            } else {
                throw new OptimisticException("Object was modified.");
            }
        });
        return true;
    }

    /**
     * Метод для удаления элемента из хранилища.
     */
    public Base delete(Base model) {
        return storage.remove(model.getId());
    }

    /**
     * Метод для получения элемента из хранилища по ключу.
     * @param key ключ.
     */
    public Base get(Integer key) {
        return storage.get(key);
    }
}
