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
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            BigDecimal target = rs.getBigDecimal("target");
            BigDecimal current = rs.getBigDecimal("current");
            //String date = rs.getString("date");
            String description = rs.getString("short_description");
            String long_description=rs.getString("long_description");
            PiggyBank piggyBank = new PiggyBank(name, "", new Money(target), new Money(current), description, long_description);
            piggyBank.setId(id);
            return piggyBank;
        }
    };

    @Override
    public List<PiggyBank> getAll() {
        return jdbcTemplate.query("SELECT id, name, target, current, short_description, long_description FROM piggybanks",
                mapper);
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
