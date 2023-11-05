package com.quantumslots.physhacks.service.gui;
import com.quantumslots.physhacks.controllers.HomeController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application {

    //HomeController controller = new HomeController();
    @Override
    public void start(Stage stage) {
        // Create a Text node with "Hello, World" text
        Text text = new Text("Hello, World");

        // Create a StackPane layout to center the text
        StackPane root = new StackPane(text);

        // Create the scene
        Scene scene = new Scene(root, 300, 200);

        // Set the title
        stage.setTitle("Hello World Applet");

        // Set the scene to the stage
        stage.setScene(scene);

        // Show the stage
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

