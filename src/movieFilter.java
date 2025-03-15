import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class movieFilter {
    private JComboBox<String> typeFilter, genreFilter;
    private JTextField yearFilter;
    private JButton applyFilter;
    private JButton resetFilter;
    private JPanel filterPanel;
    private movieTable movieTable;
    private statsPanel statsPanel;
    private chartPanel chartPanel;

    public movieFilter(movieTable movieTable, statsPanel statsPanel, chartPanel chartPanel) {
        this.movieTable = movieTable;
        this.statsPanel = statsPanel;
        this.chartPanel = chartPanel;
        filterPanel = new JPanel(new FlowLayout());

        // Create filters for data
        typeFilter = new JComboBox<>(new String[]{"All", "movie", "short", "tvMovie", "documentary"});
        genreFilter = new JComboBox<>(new String[]{"All", "Action", "Comedy", "Drama", "Horror", "Sci-Fi"});
        yearFilter = new JTextField(5);
        applyFilter = new JButton("Apply Filters");
        resetFilter = new JButton("Reset");

        // Add filter panels
        filterPanel.add(new JLabel("Type:"));
        filterPanel.add(typeFilter);
        filterPanel.add(new JLabel("Genre:"));
        filterPanel.add(genreFilter);
        filterPanel.add(new JLabel("Year:"));
        filterPanel.add(yearFilter);
        filterPanel.add(applyFilter);
        filterPanel.add(resetFilter);

        resetFilter.addActionListener(e -> resetFilters());
        applyFilter.addActionListener(e -> applyFilters());
    }

    public JPanel getFilterPanel() {
        return filterPanel;
    }

    public void applyFilters() {
        // Filter films based on criteria

            String selectedType = (String) typeFilter.getSelectedItem();
            String selectedGenre = (String) genreFilter.getSelectedItem();
            String yearText = yearFilter.getText().trim();

        List<movie> filteredMovies = new ArrayList<>();
        for (movie m : movieTable.getMovies()) {
            if (selectedType.equals("All") || m.getTitleType().equals(selectedType)) {
                if (selectedGenre.equals("All") || m.getGenre().contains(selectedGenre)) {
                    if (yearText.isEmpty() || String.valueOf(m.getYear()).equals(yearText)) {
                        filteredMovies.add(m);
                    }
                }
            }
        }
        movieTable.updateTableData(filteredMovies);
    }

    // Reset table
    private void resetFilters() {
        typeFilter.setSelectedIndex(0);
        genreFilter.setSelectedIndex(0);
        yearFilter.setText("");
        applyFilters();
    }

}
