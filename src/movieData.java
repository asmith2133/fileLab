import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import java.util.stream.Stream;

public class movieData {
    public static void main(String[] args) {
        // Read file
        File file = new File("title.basics.tsv");
        if (!file.exists()) {
            System.out.println(" Error: File not found at " + file.getAbsolutePath());
            return;
        }

        List<movie> movies = readMovies(file);


        // Print first data item in file
        System.out.println("\n First Movie Entry:");
        if (movies.size() > 0) {
            System.out.println(movies.get(0));
        } else {
            System.out.println("No valid entries found.");
        }

        // Print 10th data item in file
        System.out.println("\n 10th Movie Entry:");
        if (movies.size() >= 10) {
            System.out.println(movies.get(9));  // Index 9 = 10th item
        } else {
            System.out.println("Less than 10 entries in the file.");
        }

        // Print total number of entries
        System.out.println("\n Total Number of Entries: " + movies.size());

    }

    // Read file
    public static List<movie> readMovies(File file) {
        List<movie> movies = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Path.of(file.getAbsolutePath()))) {
            movies = lines.skip(1) // Skip first row
                    .map(line -> line.split("\t", -1))
                    .filter(fields -> fields.length >= 9) // Create Columns
                    .filter(fields -> !fields[1].equals("tvEpisode")) // Skip TV episodes
                    .map(fields -> {
                        try {
                            String tconst = fields[0];    // ID
                            String titleType = fields[1];    // Type
                            String title = fields[2];      // Title
                            String genre = fields[fields.length - 1];     // Genre
                            int year = fields[5].equals("\\N") ? 0 : Integer.parseInt(fields[5].trim());   // Year

                            return new movie(tconst, title, titleType, genre, year);

                        } catch (NumberFormatException e) {
                            return null; // Ignore invalid rows
                        }
                    })
                    .collect(Collectors.toList()); // Store movies in list

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }


        return movies;
    }

}
