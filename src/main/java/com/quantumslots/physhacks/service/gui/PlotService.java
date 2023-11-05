package com.quantumslots.physhacks.service.gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlotService extends ApplicationFrame {
    private XYSeries series;
    private XYSeriesCollection dataset;
    private JFreeChart chart;
    private double timer = 0;
    private final float animationInterval = 1000 / 20; // Adjust the animation speed as needed

    public PlotService(String title) {
        super(title);

        series = new XYSeries("Wavefunction");
        dataset = new XYSeriesCollection(series);
        chart = ChartFactory.createXYLineChart(
                "Animated Wavefunction Graph",
                "X",
                "Amplitude",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);

        // Create a Timer to update the graph at a specific interval
        Timer graphTimer = new Timer((int) animationInterval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer += animationInterval / 1000; // Convert to seconds
                updateGraph(timer);
            }
        });
        graphTimer.start();
    }

    public void updateGraph(double t) {
        series.clear();

        int numPoints = 200; // Number of points to plot

        for (int i = 0; i < numPoints; i++) {
            double x = -1.0 + (2.0 / (numPoints - 1)) * i;
            double amplitude = calculateWavefunction(x, t);

            series.add(x, amplitude);
        }
    }

    private double calculateWavefunction(double x, double t) {
        // Replace this with your wavefunction calculation
        return Math.sin(2.0 * Math.PI * x) * Math.cos(2 * Math.PI * t);
    }

    public static void main(String[] args) {
        PlotService plotService = new PlotService("Animated Graph Example");
        plotService.pack();
        UIUtils RefineryUtilities = null;
        RefineryUtilities.centerFrameOnScreen(plotService);
        plotService.setVisible(true);
    }
}
