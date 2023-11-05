package com.quantumslots.physhacks.service.gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class PlotService extends ApplicationFrame {
    private XYSeries series;
    private XYSeriesCollection dataset;
    private JFreeChart chart;

    public PlotService(String title) {
        super(title);

        series = new XYSeries("Wavefunction"); // Create a series for the wavefunction data
        dataset = new XYSeriesCollection(series);
        chart = ChartFactory.createXYLineChart(
                "Live Wavefunction Graph",    // Chart title
                "Time",                       // X-axis label
                "Amplitude",                  // Y-axis label
                dataset,                      // Dataset
                PlotOrientation.VERTICAL,
                true, true, false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }

    public void updateGraph() {
        // Replace this with your wavefunction calculation logic
        double time = System.currentTimeMillis();
        double amplitude = Math.sin(time / 1000.0); // Example: a simple sine wave

        series.add(time, amplitude);
    }

    public static void main(String[] args) {
        PlotService plotService = new PlotService("Live Graph Example");
        plotService.pack();
        UIUtils RefineryUtilities = null;
        RefineryUtilities.centerFrameOnScreen(plotService);
        plotService.setVisible(true);

        // Create a thread to update the graph at a specific interval
        Thread updateThread = new Thread(() -> {
            while (true) {
                plotService.updateGraph();
                try {
                    Thread.sleep(100); // Delay in milliseconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        updateThread.setDaemon(true); // Set the thread as a daemon thread (optional)
        updateThread.start();
    }
}
