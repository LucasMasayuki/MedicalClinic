package main.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class MenuController {
    private static Stage primaryStage;
    private static BorderPane rootLayout;
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MenuController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    private void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../../resources/view/RootLayout.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Medical Clinic");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        try {
            initRootLayout();

            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../../resources/view/Menu.fxml"));
            AnchorPane menu = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showRegisterPatientView(ActionEvent event) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../../resources/view/RegisterPatient.fxml"));
            AnchorPane registerPatient = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(registerPatient);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
