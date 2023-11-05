package com.quantumslots.physhacks.service.gui;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Slots extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Slot Machine");

        // Create a group to hold the slot machine components
        Group root = new Group();

        // Create three cylinders for the slots
        Cylinder slot1 = createSlot();
        Cylinder slot2 = createSlot();
        Cylinder slot3 = createSlot();

        // Create buttons for spinning the slots
        Button spinButton = new Button("Spin");
        spinButton.setOnAction(event -> spinSlots(slot1, slot2, slot3));

        // Arrange the cylinders and buttons in a horizontal layout
        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(slot1, slot2, slot3);
        hbox.setLayoutX(50);
        hbox.setLayoutY(50);

        // Add components to the root group
        root.getChildren().addAll(hbox, spinButton);

        // Create the scene
        Scene scene = new Scene(root, 400, 200);

        // Set the scene and show the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Cylinder createSlot() {
        Cylinder cylinder = new Cylinder(40, 120);
        cylinder.setTranslateY(-60);
        cylinder.setTranslateZ(-60);

        return cylinder;
    }

    private void spinSlots(Cylinder slot1, Cylinder slot2, Cylinder slot3) {
        // Define a rotation transition for each slot
        RotateTransition rotate1 = createRotateTransition(slot1);
        RotateTransition rotate2 = createRotateTransition(slot2);
        RotateTransition rotate3 = createRotateTransition(slot3);

        // Start the rotations
        rotate1.play();
        rotate2.play();
        rotate3.play();
    }

    private RotateTransition createRotateTransition(Cylinder slot) {
        RotateTransition rotate = new RotateTransition(Duration.seconds(3), slot);
        rotate.setByAngle(360); // Rotate 360 degrees
        rotate.setCycleCount(100); // Rotate once
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setAxis(Rotate.X_AXIS);
        return rotate;
    }
}

