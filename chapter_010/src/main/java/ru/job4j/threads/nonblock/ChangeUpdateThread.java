package ru.job4j.threads.nonblock;

/**
 * @version 1.0.
 * @since 02/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class ChangeUpdateThread extends Thread {
    private NonBlockCache storage;
    private Base changingModel;

    public ChangeUpdateThread(NonBlockCache storage, Base changingModel) {
        this.storage = storage;
        this.changingModel = changingModel;
    }

    /**
     * Метод обновляет версию объекта changingModel
     * (изменение версии обычно сопровождается другими изменениями,
     * например, полей модели - имени, описания, тд; но мы сами изменения не
     * делаем, просто меняем номер версии).
     * После изменения версии объекта, пытаемся обновить его в хранилище.
     */
    @Override
    public void run() {
            storage.update(changingModel);
    }
}
