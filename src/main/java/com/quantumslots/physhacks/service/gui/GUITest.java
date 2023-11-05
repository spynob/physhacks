package com.quantumslots.physhacks.service.gui;

import com.quantumslots.physhacks.service.gui.PlotService;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.jfree.chart.ChartPanel;

public class GUITest extends Application {
    @Override
    public void start(Stage stage) {
        // Create a new JFreeChart using PlotService
        PlotService plotService = new PlotService("");
        ChartPanel chartPanel = new ChartPanel(plotService.getChart());

        // Create a SwingNode to embed the JFreeChart
        SwingNode chartNode = new SwingNode();
        chartNode.setContent(chartPanel);

        // Create a StackPane layout to center the chart
        StackPane root = new StackPane(chartNode);

        // Create the scene
        Scene scene = new Scene(root, 800, 600);

        // Set the title
        stage.setTitle("Schrödinger's Slot Machine");

        // Set the scene to the stage
        stage.setScene(scene);

        // Show the stage
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
