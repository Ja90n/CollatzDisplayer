package com.jadonvb;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Voer een geheel getal in: ");
        int startingNumber = scanner.nextInt();

        List<Integer> xData = new ArrayList<>();
        List<Integer> yData = new ArrayList<>();

        int n = startingNumber;
        int step = 0;
        while (n != 1) {
            xData.add(step);
            yData.add(n);
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = 3 * n + 1;
            }
            step++;
        }
        xData.add(step);
        yData.add(1);

        // Tooltip-tekst genereren voor hele getallen
        List<String> toolTips = new ArrayList<>();
        for (int i = 0; i < xData.size(); i++) {
            if (xData.get(i) % 1 == 0) {
                toolTips.add("Step: " + xData.get(i) + ", Number: " + yData.get(i));
            } else {
                toolTips.add(""); // Geen tooltip voor niet-gehele getallen
            }
        }

        // Grafiek maken
        XYChart chart = new XYChartBuilder().width(800).height(600).title("Collatz Conjecture").xAxisTitle("Tijd").yAxisTitle("Getal").build();

        // Data toevoegen
        XYSeries series = chart.addSeries("Collatz", xData, yData);

        // Tooltips instellen met aangepaste tooltip generator
        chart.getStyler().setToolTipType(Styler.ToolTipType.yLabels);
        chart.getStyler().setCursorEnabled(true);
        chart.getStyler().setToolTipsEnabled(true);
        chart.getStyler().setToolTipFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12));
        chart.getStyler().setToolTipBackgroundColor(java.awt.Color.WHITE);
        chart.getStyler().setToolTipBorderColor(java.awt.Color.BLACK);

        // Laten zien
        new SwingWrapper<>(chart).displayChart();
    }
}