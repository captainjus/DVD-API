package corbos.dvdapi.models;

public class DVD {
    private int dvdid;
    private String title;
    private int releaseYear;
    private String director;
    private String rating;
    private  String notes;

    public int getDvdid() {
        return dvdid;
    }

    public void setDvdid(int dvdid) {
        this.dvdid = dvdid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }



}
