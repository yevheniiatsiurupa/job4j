package ru.job4j.threads;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @version 1.0.
 * @since 29/07/2019.
 * @author Evgeniya Tsiurupa
 */

public class PingPong extends Application {
    private static final String JOB4J = "Пинг-понг www.job4j.ru";

    /**
     * Метод для запуска программы.
     * Создаем прямоугольник с координатами начала Х = 50, У = 100, ширина = 10, высота = 10.
     * Создаем группу и добавляем в нее прямоугольник.
     * Создаем поток, который запускает метод run объекта RectangleMove.
     * Устанавливаем сцену, название Stage, определяем, что оно не изменяет размеров и выводим его для показа.
     * @param stage объект Stage.
     */
    @Override
    public void start(Stage stage) {
        int limitX = 300;
        int limitY = 300;
        Group group = new Group();
        Rectangle rect = new Rectangle(50, 100, 10, 10);
        group.getChildren().add(rect);
        new Thread(new RectangleMove(rect, limitX, limitY)).start();
        stage.setScene(new Scene(group, limitX, limitY));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
    }
}
