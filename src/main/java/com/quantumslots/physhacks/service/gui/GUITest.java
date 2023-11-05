package com.quantumslots.physhacks.service.gui;

import com.quantumslots.physhacks.service.gui.PlotService;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;

import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.RED;

public class GUITest extends Application {
    @Override
    public void start(Stage stage) {

        // Create a SwingNode
        SwingNode swingNode = new SwingNode();

        // Create a new JFreeChart using PlotService
        PlotService plotService = new PlotService();
        SwingUtilities.invokeLater(() -> {
            //plotService.pack();
            plotService.setVisible(true);
        });

        // Set the content of the SwingNode to the ApplicationFrame
        swingNode.setContent(plotService);

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 800, 600, RED);

        plotService.setSize(1000, 100);
        ChartPanel chartPanel = new ChartPanel(plotService.getChart());
        //StackPane root = new StackPane();
        root.getChildren().add(swingNode);
        Rectangle rectangle = new Rectangle(100, 100, BLUE);
        root.getChildren().add(rectangle);





        //JFrame
        //JFrame myJFrame = new JFrame("Normal JFrame");



        //Graph into JFrame

        // Put Graph into Node
        //SwingNode graphNode = new SwingNode();
        //graphNode.setContent(chartPanel);

        //Constraining Node into StackPane
        /* StackPane wrappedNode = new StackPane(graphNode);

        wrappedNode.setPrefWidth(150); // Set the desired width
        wrappedNode.setPrefHeight(75); // Set the desired height */

// Create the JavaFX scene and stage
        //StackPane root = new StackPane(graphNode);

        // Create a scene and set it to the root node


        // Create a rectangle (your content)
        //Rectangle rectangle = new Rectangle(100, 100, BLUE);
        //root.getChildren().add(graphNode);
        // Add the rectangle to the root


        //SwingNode swingNode = new SwingNode();

        // Create a JPanel to host your Swing UI
        JPanel panel = new JPanel();
        panel.add(new JButton("Spin!"));

        // Set the content of the SwingNode to the JPanel
        swingNode.setContent(panel);

/////////////////////////////////////////////////////////////

        // Set the scene to the stage
        stage.setScene(scene);

        // Set the stage title
        stage.setTitle("SwingNode Example");

        // Show the stage
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
