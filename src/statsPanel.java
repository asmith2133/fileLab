import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class statsPanel {
    private JPanel panel;
    private JTable statsTable;

    public statsPanel(List<movie> movies) {
        panel = new JPanel(new BorderLayout());

        // Calculate statistics
        int totalMovies = movies.size();
        double avgYear = movies.stream().mapToInt(movie::getYear).average().orElse(0);  // Get Average Year
        String mostCommonGenre = getMostCommonGenre(movies);

        // List data in the JTable
        String[][] data = {
                {"Total Movies", String.valueOf(totalMovies)},
                {"Average Year", String.format("%.2f", avgYear)},
                {"Most Common Genre", mostCommonGenre}
        };

        String[] columnNames = {"Statistic", "Value"};
        statsTable = new JTable(data, columnNames);  // JTable for statistics

        panel.add(new JScrollPane(statsTable), BorderLayout.CENTER);
    }

    private String getMostCommonGenre(List<movie> movies) {
        Map<String, Long> genreCount = movies.stream()
                .flatMap(m -> List.of(m.getGenre().split(",")).stream())  // Separate multiple genres
                .collect(Collectors.groupingBy(g -> g, Collectors.counting()));

        return genreCount.entrySet().stream()
                .max(Map.Entry.comparingByValue()) // Returns genre with the max count
                .map(Map.Entry::getKey)
                .orElse("N/A");
    }

    public JPanel getPanel() {
        return panel;
    }
}
