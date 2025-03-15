import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class movieTable {
    private JTable table;
    private DefaultTableModel tableModel;
    private Vector<Vector<Object>> tableData;
    private Vector<String> columnNames;
    private JPanel tablePanel;
    private List<movie> movies;
    private detailsPanel detailsPanel;

    public movieTable(List<movie> movies, detailsPanel detailsPanel) {
        // Constructor
        this.movies = movies;
        this.detailsPanel = detailsPanel;

        // Column Headers for table data
        columnNames = new Vector<>();
        columnNames.add("ID");
        columnNames.add("Title");
        columnNames.add("Type");
        columnNames.add("Genre");
        columnNames.add("Year");

        // Initialize data
        tableData = new Vector<>();
        tableModel = new DefaultTableModel(tableData, columnNames);
        table = new JTable(tableModel);   // Create JTable

        // Allow single row selection
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Shows details of item selected
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                showDetails();
            }
        });

        // Create scrollpanel for table
        tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);

        updateTableData(movies);
    }

    private void showDetails() {
        // Show details of selected row
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            movie selectedMovie = movies.get(selectedRow);
            detailsPanel.updateDetails(selectedMovie);
        }
    }

    public JPanel getTablePanel() {
        return tablePanel;
    }

    public List<movie> getMovies() {
        return movies;
    }

    public void updateTableData(List<movie> filteredMovies) {
        tableData.clear();

        // Place movies in table
        for (movie m : filteredMovies) {
            Vector<Object> row = new Vector<>();
            row.add(m.getTconst());
            row.add(m.getTitle());
            row.add(m.getTitleType());
            row.add(m.getGenre());
            row.add(m.getYear());
            tableData.add(row);
        }

        tableModel.fireTableDataChanged();
    }
}
