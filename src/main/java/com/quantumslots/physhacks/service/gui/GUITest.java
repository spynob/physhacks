package com.quantumslots.physhacks.service.gui;

import com.quantumslots.physhacks.controllers.HomeController;
import com.quantumslots.physhacks.model.Potentials;
import com.quantumslots.physhacks.service.TimeService;
import com.quantumslots.physhacks.service.gui.PlotService;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.jfree.chart.ChartPanel;

public class GUITest extends Application {
    private HomeController homeController;
    private PlotService plotService;
    public GUITest() {
        plotService = new PlotService("");
        homeController = new HomeController(Potentials.InfiniteSquareWell, plotService);
    }


    @Override
    public void start(Stage stage) {
        // Create a new JFreeChart using PlotService
        ChartPanel chartPanel = new ChartPanel(plotService.getChart());

        // Set the fixed dimensions for the chart
        chartPanel.setPreferredSize(new java.awt.Dimension(550, 400)); // You can adjust the dimensions as needed

        // Create a SwingNode to embed the JFreeChart
        SwingNode chartNode = new SwingNode();
        chartNode.setContent(chartPanel);

        // Create a StackPane layout to center the chart on the left
        StackPane leftPane = new StackPane(chartNode);

        // Create a button and add an action to call the measure() method in HomeController
        Button measureButton = new Button("Make Measurement");
        measureButton.setOnAction(e -> triggerMeasure());


        // Create a VBox to organize the button and text
        VBox rightPane = new VBox(measureButton);
        rightPane.setSpacing(10); // Add spacing between button and text

        // Create an HBox to hold both the chart and the right components
        HBox root = new HBox(leftPane, rightPane);

        // Add padding to separate the chart from other components
        root.setPadding(new Insets(10));

        // Create the scene
        Scene scene = new Scene(root, 800, 600);

        // Set the title
        stage.setTitle("Schrödinger's Slot Machine");

        // Set the scene to the stage
        stage.setScene(scene);

        // Show the stage
        stage.show();
    }

    private void triggerMeasure() {
        double position = homeController.makeAMeasurement();
    }

    public static void main(String[] args) {
        // Proceed with launching the JavaFX application
        launch(args);
    }

}
