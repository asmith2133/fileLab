import javax.swing.*;
import java.awt.*;

public class detailsPanel {
    private JPanel panel;
    private static JLabel titleLabel;
    private static JLabel typeLabel;
    private static JLabel genreLabel;
    private static JLabel yearLabel;

    public detailsPanel() {
        // Adds panel labels to the details panel
        panel = new JPanel(new GridLayout(4, 1));
        titleLabel = new JLabel("Title: ");
        typeLabel = new JLabel("Type: ");
        genreLabel = new JLabel("Genre: ");
        yearLabel = new JLabel("Year: ");

        panel.add(titleLabel);
        panel.add(typeLabel);
        panel.add(genreLabel);
        panel.add(yearLabel);
    }

    public JPanel getPanel() {
        return panel;
    }

    // Displays selected movie data
    public static void updateDetails(movie movie) {
        if (movie != null) {
            titleLabel.setText("Title: " + movie.getTitle());
            typeLabel.setText("Type: " + movie.getTitleType());
            genreLabel.setText("Genre: " + movie.getGenre());
            yearLabel.setText("Year: " + movie.getYear());
        } else {
            titleLabel.setText("Title: ");
            typeLabel.setText("Type: ");
            genreLabel.setText("Genre: ");
            yearLabel.setText("Year: ");
        }
    }
}

