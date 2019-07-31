package ru.job4j.threads;

import ru.job4j.list.ContainerInterface;

import java.util.Iterator;

public class ListThread<E> extends Thread {
    private ContainerInterface<E> list;

    public ListThread(ContainerInterface<E> list) {
        this.list = list;
    }

    @Override
    public void run(){
        Iterator<E> it = this.list.iterator();
        while (it.hasNext()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(it.next());
        }
    }
}
