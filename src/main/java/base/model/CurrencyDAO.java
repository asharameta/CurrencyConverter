package base.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
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
	
	public int addCurrency(Currency currencyToAdd) {
		String sql = "INSERT INTO currencies (code, name, symbol) VALUES (?, ?, ?)";
		
	    int rowsAffected = jdbcTemplate.update(sql, currencyToAdd.getCode(), currencyToAdd.getName(), currencyToAdd.getSymbol());
		
		return rowsAffected;
	}
	
	public Currency getCurrency(String code) {
		String sql = "SELECT * FROM currencies WHERE code = ?";
		
		List<Currency> currencyToReturn = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Currency c = new Currency();
            c.setId(Integer.parseInt(rs.getString("id")));
            c.setCode(rs.getString("code"));
            c.setName(rs.getString("name"));
            c.setSymbol(rs.getString("symbol").charAt(0));
            return c;
        });
	    
	    return currencyToReturn.get(0);
	}
	
	public int deleteCurrency(String code) {
		String sql = "DELETE FROM currencies WHERE code = ?";
		
		int rowsAffected = jdbcTemplate.update(sql, code);
	
		return rowsAffected;
	}
}
