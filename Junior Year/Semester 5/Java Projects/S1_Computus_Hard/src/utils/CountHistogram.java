package utils;

import easter.GregorianEaster;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.ui.ApplicationFrame;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class CountHistogram extends ApplicationFrame {
    private final int[] countArray;

    public CountHistogram(String title, int[] countArray) {
        super("mnkrueger - Computus Histograms");
        this.countArray = countArray;
        CategoryDataset dataset = createDataset();
        JFreeChart barChart = ChartFactory.createBarChart(
                title,
                "Day",
                "Count",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        BarRenderer renderer = (BarRenderer) barChart.getCategoryPlot().getRenderer();
        renderer.setToolTipGenerator(new StandardCategoryToolTipGenerator());

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String series = "Easter Dates";

        for (int i = 0; i < countArray.length; i++) {
            int day = i + 1;
            dataset.addValue(countArray[i], series, String.valueOf(day));
        }
        return dataset;
    }

    public void showHistogram() {
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
