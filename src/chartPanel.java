import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class chartPanel {
    private JPanel panel;

    public chartPanel(List<movie> movies) {
        panel = new JPanel(new BorderLayout());
        JFreeChart barChart = ChartFactory.createBarChart(
                "Movies Produced Each Year", // Chart Title
                "Year",                     // X-Axis Label
                "Number of Movies",         // Y-Axis Label
                createDataset(movies)       // Data
        );

        ChartPanel chartPanel = new ChartPanel(barChart);
        panel.add(chartPanel, BorderLayout.CENTER);
    }

    private CategoryDataset createDataset(List<movie> movies) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Sort movies by year and count them
        Map<Integer, Long> moviesPerYear = movies.stream()
                .collect(Collectors.groupingBy(movie::getYear, Collectors.counting()));

        for (Map.Entry<Integer, Long> entry : moviesPerYear.entrySet()) {
            dataset.addValue(entry.getValue(), "Movies", String.valueOf(entry.getKey()));
        }

        return dataset;
    }

    public JPanel getPanel() {
        return panel;
    }
}
