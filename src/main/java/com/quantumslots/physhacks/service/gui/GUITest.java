package com.quantumslots.physhacks.service.gui;

import com.quantumslots.physhacks.controllers.HomeController;
import com.quantumslots.physhacks.model.potentials.InfiniteSquareWell;
import com.quantumslots.physhacks.model.potentials.PotentialFunction;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jfree.chart.ChartPanel;
import javafx.scene.control.TextField;

public class GUITest extends Application {
    private HomeController homeController;
    private PlotService plotService;
    private TextField inputField1;
    private TextField inputField2;

    public GUITest() {
        PotentialFunction potential = new InfiniteSquareWell();
        plotService = new PlotService("", potential);
        homeController = new HomeController(potential, plotService);
    }

    @Override
    public void start(Stage stage) {
        ChartPanel chartPanel = new ChartPanel(plotService.getChart());

        Image icon = new Image("file:src/main/resources/images/icon.png");

        stage.getIcons().add(icon);

        chartPanel.setPreferredSize(new java.awt.Dimension(550, 400));

        SwingNode chartNode = new SwingNode();
        chartNode.setContent(chartPanel);

        StackPane leftPane = new StackPane(chartNode);

        Label instructionLabel1 = new Label("Enter a number between -1 and 1");
        Label instructionLabel2 = new Label("to select the range where you think");
        Label instructionLabel3 = new Label("the particle will be and place your bet!");

        inputField1 = new TextField();
        inputField1.setPromptText("Enter left bound: ");

        inputField2 = new TextField();
        inputField2.setPromptText("Enter right bound: ");

        Button placeBetButton = new Button("Place Bet");
        placeBetButton.setOnAction(e -> placeBet(inputField1.getText(), inputField2.getText()));

        VBox textFieldsPane = new VBox(
                new VBox(instructionLabel1, instructionLabel2, instructionLabel3, inputField1),
                new VBox(inputField2, placeBetButton)
        );
        textFieldsPane.setSpacing(10);

        Button measureButton = new Button("Make Measurement");
        measureButton.setOnAction(e -> triggerMeasure());

        HBox chartAndTextFields = new HBox(leftPane, textFieldsPane);
        chartAndTextFields.setPadding(new Insets(10));
        chartAndTextFields.setSpacing(10);

        HBox buttonPane = new HBox(measureButton);
        buttonPane.setPadding(new Insets(10));

        VBox root = new VBox(chartAndTextFields, buttonPane);

        Scene scene = new Scene(root, 800, 600);

        stage.setTitle("Schrödinger's Slot Machine");

        stage.setScene(scene);

        stage.show();
    }

    private void triggerMeasure() {
        double position = homeController.makeAMeasurement();
    }

    private void placeBet(String leftBound, String rightBound) {
        // Handle the bet placement using leftBound and rightBound
    }

    public static void main(String[] args) {
        launch(args);
    }
}
