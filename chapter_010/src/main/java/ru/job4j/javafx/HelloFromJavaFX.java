package ru.job4j.javafx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloFromJavaFX extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Text text = new Text("Hello from JavaFX.");
        text.setLayoutY(80);
        text.setLayoutX(100);

        Group group = new Group(text);
        Scene scene = new Scene(group);
        stage.setScene(scene);

        stage.setTitle("First App");
        stage.setWidth(300);
        stage.setHeight(250);
        stage.show();
    }
}
