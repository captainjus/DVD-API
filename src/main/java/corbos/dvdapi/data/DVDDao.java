package corbos.dvdapi.data;

import corbos.dvdapi.models.DVD;

import java.util.List;

public interface DVDDao {
    DVD add(DVD dvd);

    List<DVD> getAll();

    DVD findById(int id);
    DVD findByTitle(String title);
    DVD findByReleaseYear(int year);
    DVD findByDirector(String director);
    DVD findByRating(String rating);

    // true if item exists and is updated
    boolean update(DVD dvd);

    // true if item exists and is deleted
    boolean deleteById(int id);
}

