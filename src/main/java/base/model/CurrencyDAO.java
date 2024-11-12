package base.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class CurrencyDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CurrencyDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
	public List<Currency> getAllCurrencies(){
		String sql = "SELECT * FROM currencies";
		
		List<Currency> currencies = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Currency c = new Currency();
            c.setId(rs.getInt("id"));
            c.setCode(rs.getString("code"));
            c.setName(rs.getString("name"));
            c.setSymbol(rs.getString("symbol").charAt(0));
            return c;
        });
		
		return currencies;
	}
}
