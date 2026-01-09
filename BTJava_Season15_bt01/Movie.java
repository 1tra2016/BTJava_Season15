public class Movie {
    private int id;
    private String title;
    private String director;
    private String releaseDate;
    private double rating;

    public Movie(int id, String title, String director, String releaseDate, double rating) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{ID= " + id + " | Tên= " + title + " | Đạo diễn= " + director + " | Ngày phát hành= " + releaseDate + " | Rating= " + rating+"}";
    }
}
