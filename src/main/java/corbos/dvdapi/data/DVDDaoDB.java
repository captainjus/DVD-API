package corbos.dvdapi.data;

import corbos.dvdapi.models.DVD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class DVDDaoDB implements DVDDao{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DVDDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public DVD add(DVD dvd) {
        final String sql = "INSERT INTO dvd(title, releaseYear, director, rating, notes) VALUES(?,?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, dvd.getTitle());
            statement.setInt(2, dvd.getReleaseYear());
            return statement;

        }, keyHolder);

        dvd.setDvdid(keyHolder.getKey().intValue());

        return dvd;
    }

    @Override
    public List<DVD> getAll() {
        return null;
    }

    @Override
    public DVD findById(int id) {
        return null;
    }

    @Override
    public DVD findByTitle(String title) {
        return null;
    }

    @Override
    public DVD findByReleaseYear(int year) {
        return null;
    }

    @Override
    public DVD findByDirector(String director) {
        return null;
    }

    @Override
    public DVD findByRating(String rating) {
        return null;
    }

    @Override
    public boolean update(DVD dvd) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
