package it.morfoza;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Component
//@Profile("!demo")
public class DatabasePaymentRepository implements PaymentRepository {

    public DatabasePaymentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private JdbcTemplate jdbcTemplate;

    private RowMapper<Payment> mapper = new RowMapper<Payment>() {
        @Override
        public Payment mapRow(ResultSet rs, int i) throws SQLException {

            Payment payment = new Payment();
            payment.setId(rs.getLong("id"));
            payment.setAmount(new Money(rs.getBigDecimal("amount")));
            payment.setPiggyBankId(rs.getLong("piggybank_id"));
            payment.setName(rs.getString("name"));
            payment.setEmail(rs.getString("email"));
            payment.setTimestamp(rs.getDate("timestamp"));
            return payment;        }
    };

    @Override
    public List<Payment> getAll() {
        return jdbcTemplate.query("SELECT id, name, target, current, short_description, long_description, picture_url FROM piggybanks", mapper);
    }

    @Override
    public Payment getById(long id) {
        return jdbcTemplate.queryForObject("SELECT id, name, target, current, short_description, long_description, picture_url FROM piggybanks WHERE id = ?", mapper, id);
    }

    @Override
    public long add(Payment payment) {
        Long id = jdbcTemplate.queryForObject ("INSERT INTO payments(name, amount, piggybank_id, email) VALUES(?,?,?,?) RETURNING id", Long.class,
                payment.getName(),
                payment.getAmount().getBigDecimalValue(),
                payment.getPiggyBankId(),
                payment.getEmail()
                );
        return id;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Payment> getByPiggyBank(long piggyBankId) {
        return jdbcTemplate.query("SELECT id, name, amount, email, piggybank_id, timestamp FROM payments WHERE piggybank_id = ?", mapper, piggyBankId);
    }

}
