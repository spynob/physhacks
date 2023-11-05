package com.quantumslots.physhacks.service.gui;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;

import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.RED;

public class GUITESTT extends Application {
    @Override



    public void start(Stage stage) {
        SwingNode swingNode = new SwingNode();

        // Create a custom ApplicationFrame for JFreeChart
        JComponent frame = new PlotService();

        // Set the content of the SwingNode to the ApplicationFrame
        swingNode.setContent(frame);

        // Create the JavaFX scene and stage
        StackPane root = new StackPane(swingNode);
        Scene scene = new Scene(root, 800, 600);

        // Set the scene to the stage
        stage.setScene(scene);

        // Set the stage title
        stage.setTitle("JFreeChart in SwingNode Example");

        // Show the stage
        stage.show();

        // Initialize and display the ApplicationFrame on the Swing thread
        /*
        SwingUtilities.invokeLater(() -> {
            frame.pack();
            RefineryUtilities.centerFrameOnScreen(frame);
            frame.setVisible(true);*/
    }



    public static void main(String[] args) {
        launch(args);
    }
}

