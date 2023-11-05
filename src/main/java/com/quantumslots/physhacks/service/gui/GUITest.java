package com.quantumslots.physhacks.service.gui;

import com.quantumslots.physhacks.controllers.HomeController;
import com.quantumslots.physhacks.model.Player;
import com.quantumslots.physhacks.model.potentials.InfiniteSquareWell;
import com.quantumslots.physhacks.model.potentials.PotentialFunction;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.jfree.chart.ChartPanel;
import javafx.scene.control.TextField;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.embed.swing.SwingNode;
import org.jfree.chart.ChartPanel;
import javafx.scene.control.TextField;

public class GUITest extends Application {
    private HomeController homeController;
    private PlotService plotService;
    private Label cashLabel;
    private TextField betField;
    private TextField inputField2;
    private TextField inputField3;

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

        cashLabel = new Label("Cash: $" + homeController.getCash());
        cashLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14)); // Adjust font style
        cashLabel.setTextFill(Color.GREEN); // Adjust font color

        Label betLabel = new Label("Enter an amount to bet:");
        betField = new TextField();
        betField.setPromptText("Enter Bet:");

        Label boundLabel = new Label("Select a range for the bet:");
        Label leftBoundLabel = new Label("Left Bound:");
        inputField2 = new TextField();
        inputField2.setPromptText("Enter left bound:");
        Label rightBoundLabel = new Label("Right Bound:");
        inputField3 = new TextField();
        inputField3.setPromptText("Enter right bound:");

        Button placeBetButton = new Button("Place Bet");
        placeBetButton.setOnAction(e -> placeBet(betField.getText(), inputField2.getText(), inputField3.getText()));

        VBox textFieldsPane = new VBox(
                betLabel,
                betField,
                boundLabel,
                new HBox(leftBoundLabel, inputField2),
                new HBox(rightBoundLabel, inputField3),
                placeBetButton
        );
        textFieldsPane.setSpacing(10);

        VBox topRightPane = new VBox(cashLabel);
        topRightPane.setSpacing(10);
        topRightPane.setAlignment(Pos.TOP_RIGHT);

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

    private void placeBet(String bet, String leftBound, String rightBound) {
        homeController.placeBet(Integer.parseInt(bet), Float.parseFloat(leftBound),Float.parseFloat(rightBound));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
