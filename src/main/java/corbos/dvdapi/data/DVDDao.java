package corbos.dvdapi.data;

import corbos.dvdapi.models.DVD;

import java.util.List;

public interface DVDDao {
    DVD add(DVD dvd);

    List<DVD> getAll();

    DVD findById(int id);
    List<DVD> findByTitle(String title);
    List<DVD> findByReleaseYear(int year);
    List<DVD> findByDirector(String director);
    List<DVD> findByRating(String rating);

    // true if item exists and is updated
    boolean update(DVD dvd);

    // true if item exists and is deleted
    boolean deleteById(int id);
}
