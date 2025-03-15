public class movie {
    private String tconst;  // Unique movie ID
    private String title;
    private String titleType;  // Movie or short
    private String genre;
    private int year;

    public movie(String tconst, String title, String titleType, String genre, int year) {
        this.tconst = tconst;
        this.title = title;
        this.titleType = titleType;
        this.genre = genre;
        this.year = year;
    }

    public String getTconst() { return tconst; }
    public String getTitle() { return title; }
    public String getTitleType() { return titleType; }
    public String getGenre() { return genre; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return String.format(" %s (%d) | Type: %s | Genre: %s | ID: %s",
                title, year, titleType, genre, tconst);
    }
}
