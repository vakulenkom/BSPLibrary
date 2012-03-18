package ua.mabp.kiev.chart;

/**
 *
 */

import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.*;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StackedXYBarRenderer;
import org.jfree.data.time.TimeTableXYDataset;
import org.jfree.data.time.Year;
import org.jfree.data.xy.TableXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.VerticalAlignment;


public class StackedXYBarChartFrame extends ApplicationFrame {

    /**
     * Создаёт новый фрейм с графиком 
     * @param title Заголовок окна 
     */
    public StackedXYBarChartFrame(String title) {
        super(title);
        // Создаём новый график 
        JFreeChart chart = createChart(createDataset());
        // На панеле 
        ChartPanel chartPanel = new ChartPanel(chart);
        // С размерами 450*450 
        chartPanel.setPreferredSize(new Dimension(450, 450));
        // И ползунками если необходимо 
        JScrollPane sp = new JScrollPane(chartPanel);
        sp.setPreferredSize(new Dimension(500, 500));
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setContentPane(sp);
    }

    /**
     * Наполняет Set данными для построения графика 
     * @return Данные для построения 
     */
    private static TableXYDataset createDataset() {
        // Типа данные 
        TimeTableXYDataset dataset = new TimeTableXYDataset();
        dataset.add(new Year(2002), 1000, "Blue");
        dataset.add(new Year(2003), 1100, "Blue");
        dataset.add(new Year(2002), 0, "Red");
        dataset.add(new Year(2003), 50, "Red");
        return dataset;
    }

    /**
     * Создаёт новый график по данным 
     * @param dataset данные для построения 
     * @return график
     */
    private static JFreeChart createChart(TableXYDataset dataset) {

        // OX - ось абсцисс 
        // задаем название оси 
        DateAxis domainAxis = new DateAxis("Year");
        // Показываем стрелочку вправо 
        domainAxis.setPositiveArrowVisible(true);
        // Задаем отступ от графика 
        domainAxis.setUpperMargin(0.2);

        // OY - ось ординат 
        // Задаём название оси 
        NumberAxis rangeAxis = new NumberAxis("Color");
        // Задаём величину деления 
        rangeAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
        rangeAxis.setTickUnit(new NumberTickUnit(200));
        // Показываем стрелочку вверх 
        rangeAxis.setPositiveArrowVisible(true);


        // Render 
        // Создаем стопковый (не знаю как лучше перевести) график 
        // 0.02 - расстояние между столбиками 
        StackedXYBarRenderer renderer = new StackedXYBarRenderer(0.02);
        // без рамки 
        renderer.setDrawBarOutline(false);
        // цвета для каждого элемента стопки 
        renderer.setSeriesPaint(0, Color.blue);
        renderer.setSeriesPaint(1, Color.red);
        // Задаём формат и текст подсказки 
        renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator("{0} : {1} = {2} tonnes", new SimpleDateFormat("yyyy"), new DecimalFormat("#,##0")));
        renderer.setSeriesItemLabelGenerator(0, new StandardXYItemLabelGenerator());
        renderer.setSeriesItemLabelGenerator(1, new StandardXYItemLabelGenerator());
        // Делаем её видимой 
        renderer.setSeriesItemLabelsVisible(0, true);
        renderer.setSeriesItemLabelsVisible(1, true);
        // И описываем её шрифт 
        renderer.setSeriesItemLabelFont(0, new Font("Serif", Font.BOLD, 10));
        renderer.setSeriesItemLabelFont(1, new Font("Serif", Font.BOLD, 10));

        // Plot 
        // Создаем область рисования 
        XYPlot plot = new XYPlot(dataset, domainAxis, rangeAxis, renderer);
        // Закрашиваем 
        plot.setBackgroundPaint(Color.white);
        // Закрашиваем сетку 
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        // Отступ от осей 
        plot.setAxisOffset(new RectangleInsets(0D, 0D, 10D, 10D));
        plot.setOutlinePaint(null);

        // Chart 
        // Создаем новый график 
        JFreeChart chart = new JFreeChart(plot);
        // Закрашиваем 
        chart.setBackgroundPaint(Color.white);
        // Перемещаем легенду в верхний правый угол 
        chart.getLegend().setPosition(RectangleEdge.RIGHT);
        chart.getLegend().setVerticalAlignment(VerticalAlignment.TOP);

        return chart;
    }

    public static void main(String[] args) {
        // Создаем новый фрейм 
        StackedXYBarChartFrame demo = new StackedXYBarChartFrame("JFreeChart: StackedXYBarChart");
        demo.pack();
        // И показываем 
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}