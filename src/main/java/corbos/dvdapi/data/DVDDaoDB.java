package corbos.dvdapi.data;

import corbos.dvdapi.models.DVD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository //makes the class an injectable dependency
@Profile("database")
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
            statement.setString(3, dvd.getDirector());
            statement.setString(4, dvd.getRating());
            statement.setString(5, dvd.getNotes());
            return statement;

        }, keyHolder);

        dvd.setDvdid(keyHolder.getKey().intValue());

        return dvd;
    }

    @Override
    public List<DVD> getAll() {
        final String sql = "SELECT DVDid, title, releaseYear, director, rating, notes FROM dvd;";
        return jdbcTemplate.query(sql, new DVDMapper());
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
    private static final class DVDMapper implements RowMapper<DVD> {

        @Override
        public DVD mapRow(ResultSet rs, int index) throws SQLException {
            DVD dvd = new DVD();
           dvd.setDvdid(rs.getInt("DVDid"));
           dvd.setTitle(rs.getString("title"));
           dvd.setDirector(rs.getString("director"));
           dvd.setReleaseYear(rs.getInt("releaseYear"));
           dvd.setRating(rs.getString("rating"));
           dvd.setNotes(rs.getString("notes"));
            return dvd;
        }
    }
}
