package woowacourse.shoppingcart.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import woowacourse.shoppingcart.entity.PrivacyEntity;

@Repository
public class JdbcPrivacyDao implements PrivacyDao {
    private static final RowMapper<PrivacyEntity> PRIVACY_ENTITY_ROW_MAPPER = (rs, rowNum) -> new PrivacyEntity(
            rs.getInt("customer_id"),
            rs.getString("name"),
            rs.getString("gender"),
            rs.getTimestamp("birth_day").toLocalDateTime().toLocalDate(),
            rs.getString("contact")
    );

    private final JdbcTemplate jdbcTemplate;

    public JdbcPrivacyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(int customerId, PrivacyEntity privacyEntity) {
        String sql = "INSERT INTO PRIVACY (customer_id, name, gender, birth_day, contact) VALUES(?, ?, ?, ? ,?)";
        jdbcTemplate.update(sql, customerId, privacyEntity.getName(), privacyEntity.getGender(),
                privacyEntity.getBirthday(), privacyEntity.getContact());
    }

    @Override
    public PrivacyEntity findById(int id) {
        String sql = "SELECT customer_id, name, gender, birth_day, contact FROM PRIVACY WHERE customer_id = ?";
        return jdbcTemplate.queryForObject(sql, PRIVACY_ENTITY_ROW_MAPPER, id);
    }

    @Override
    public void update(int customerId, PrivacyEntity privacyEntity) {
        String sql = "UPDATE PRIVACY SET name = ?, gender = ?, birth_day = ?, contact = ? WHERE customer_id = ?";
        jdbcTemplate.update(sql, privacyEntity.getName(), privacyEntity.getGender(), privacyEntity.getBirthday(),
                privacyEntity.getContact(), customerId);
    }

    @Override
    public void delete(int customerId) {
        String sql = "DELETE FROM PRIVACY WHERE customer_id = ?";
        jdbcTemplate.update(sql, customerId);
    }
}
