package main.java.application;

import main.java.controller.MenuController;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        new MenuController(primaryStage).init();
    }
}
