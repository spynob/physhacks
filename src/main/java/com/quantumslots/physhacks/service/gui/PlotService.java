package com.quantumslots.physhacks.service.gui;

import com.quantumslots.physhacks.model.potentials.PotentialFunction;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class PlotService extends ApplicationFrame {
    private XYSeries series1;
    private XYSeries series2;
    private static XYSeriesCollection dataset;
    private static JFreeChart chart;

    private final float animationInterval = 1000 / 20; // Adjust the animation speed as needed
    private double currentTime = 0.0;

    private PotentialFunction potential;

    public PlotService(String title, PotentialFunction potential) {
        super(title);
        this.potential = potential;

        series1 = new XYSeries("Function 1");
        series2 = new XYSeries("Function 2");
        dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        chart = ChartFactory.createXYLineChart(
                "",
                "",
                "",
                dataset,
                PlotOrientation.VERTICAL,
                false, false, false
        );

        chart.setBackgroundPaint(Color.BLACK); // Set the background color to black

        // Customize the plot's gridlines (teal color)
        XYPlot plot = chart.getXYPlot();
        plot.setDomainGridlinePaint(Color.CYAN);
        plot.setRangeGridlinePaint(Color.CYAN);

        // Remove numbers on the axes
        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setTickLabelsVisible(false);

        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setTickLabelsVisible(false);

        // Set the plot area's background to black
        plot.setBackgroundPaint(Color.BLACK);

        // Set fixed axis bounds to prevent zooming
        chart.getXYPlot().getDomainAxis().setRange(-1.0, 1.0);
        chart.getXYPlot().getRangeAxis().setRange(-2.0, 2.0);

        // Create a Timer to update the graph at a specific interval
        Timer graphTimer = new Timer((int) animationInterval, e -> {
            updateGraph();
        });
        graphTimer.start();
    }

    // Method to retrieve the JFreeChart instance
    public JFreeChart getChart() {
        return chart;
    }

    public void updateGraph(double position) {
        series1.clear();
        series2.clear();

        int numPoints = 200; // Number of points to plot

        for (int i = 0; i < numPoints; i++) {
            double x = -1.0 + (2.0 / (numPoints - 1)) * i;
            double amplitude1 = collapse_wavefunction(x, position);

            series1.add(x, amplitude1);

        }
    }
    public void updateGraph() {
        series1.clear();
        series2.clear();

        int numPoints = 200; // Number of points to plot


            for (int i = 0; i < numPoints; i++) {
                double x = -1.0 + (2.0 / (numPoints - 1)) * i;
                double amplitude1 = calculateFunction1(x, currentTime);
                double amplitude2 = calculateFunction2(x, currentTime);

                series1.add(x, amplitude1);
                series2.add(x, amplitude2);
            }

            currentTime += 1.0 / 20.0; // Increment the time for the next frame
    }

    private double calculateFunction1(double x, double t) {
        // Replace this with your first function calculation
        return Math.sin(2.0 * Math.PI * x) * Math.cos(2 * Math.PI * t);
    }

    private double calculateFunction2(double x, double t) {
        // Replace this with your second function calculation
        return Math.cos(2.0 * Math.PI * x) * Math.sin(2 * Math.PI * t);
    }

    private double collapse_wavefunction(double x, double position) {
        return Math.exp(-10000 * Math.pow((x - position), 2));
    }


}
