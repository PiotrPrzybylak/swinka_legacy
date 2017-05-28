package it.morfoza;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@Profile("!demo")
public class DatabasePiggyRepository implements PiggyRepository {

    public DatabasePiggyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private JdbcTemplate jdbcTemplate;

    private RowMapper<PiggyBank> mapper = new RowMapper<PiggyBank>() {
        @Override
        public PiggyBank mapRow(ResultSet rs, int i) throws SQLException {
            String name = rs.getString("name");
            String city = rs.getString("city");
            BigDecimal price = rs.getBigDecimal("price");
            String date = rs.getString("date");
            String description = rs.getString("description");
            String long_description=rs.getString("long_description");
            String url_image=rs.getString("url_image");
            return new PiggyBank(name, city, date, new Money(price),description,long_description,url_image);
        }
    };

    @Override
    public List<PiggyBank> getAllEvents() {
        return jdbcTemplate.query("SELECT name, city, dance, id, price, date FROM events",
                mapper);
    }

    @Override
    public List<PiggyBank> getByCity(String danceName) {
        return jdbcTemplate.query("SELECT name, city, dance, id, price, date FROM events WHERE dance LIKE ?",
                mapper, "%" + danceName + "%");
    }

    @Override
    public PiggyBank getById(long id) {
        return null;
    }

    @Override
    public long add(PiggyBank piggyBank) {
        return 0;
    }

    @Override
    public void delete(long id) {

    }

}
