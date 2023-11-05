package com.quantumslots.physhacks.service.gui;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.*;

public class Slots extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Slot Machine");

        // Create a group to hold the slot machine components
        Group wheel = new Group();

        // Create three cylinders for the slots
        Cylinder slot1 = createSlot();
        Cylinder slot2 = createSlot();
        Cylinder slot3 = createSlot();


        //Spin!!
        Button spinButton = new Button("Spin");
        spinButton.setOnAction(event -> spinSlots(slot1, slot2, slot3));

        // Arrange the cylinders and buttons in a horizontal layout
        HBox hbox = new HBox(15);
        hbox.getChildren().addAll(slot1, slot2, slot3);
        hbox.setLayoutX(50);
        hbox.setLayoutY(100);

        // Add components to the root group
        wheel.getChildren().addAll(hbox, spinButton);

        // Create the scene
        Scene scene = new Scene(wheel, 400, 200);

        // Set the scene and show the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Cylinder createSlot() {
        Cylinder cylinder = new Cylinder(40, 120);
        cylinder.setTranslateY(-60);
        cylinder.setTranslateZ(-60);

        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.BLUE);
        cylinder.setMaterial(material);

                /*new ImagePattern(
                        new Image(".png"), 0, 0, 1, 1, true
                )*/
        //);

        return cylinder;//
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

