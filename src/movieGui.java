import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class movieGui {
    private JFrame frame;
    private movieTable movieTable;
    private detailsPanel detailsPanel;
    private statsPanel statsPanel;
    private chartPanel chartPanel;
    private movieFilter filterPanel;

    public movieGui() {
        List<movie> movies = movieData.readMovies(new File("title.basics.tsv"));

        detailsPanel = new detailsPanel();
        movieTable = new movieTable(movies, detailsPanel);
        statsPanel = new statsPanel(movies);
        chartPanel = new chartPanel(movies);
        filterPanel = new movieFilter(movieTable, statsPanel, chartPanel); // Add Filters

        // Create main frame
        frame = new JFrame("Movie Data Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLayout(new BorderLayout());

        // Panel for filter selections
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(filterPanel.getFilterPanel(), BorderLayout.NORTH);

        // Panel for table and details
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(movieTable.getTablePanel(), BorderLayout.CENTER);
        centerPanel.add(detailsPanel.getPanel(), BorderLayout.SOUTH);

        // Panel for statistics and chart
        JPanel rightPanel = new JPanel(new GridLayout(2, 1));
        rightPanel.add(statsPanel.getPanel());
        rightPanel.add(chartPanel.getPanel());

        // Adds the panels to the frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(movieGui::new);
    }
}
