package base.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ExchangeRateDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExchangeRateDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<ExchangeRate> getAllExchangeRates(){
		String sql = "SELECT er.id AS exchangeRateId, er.rate, "
					+ "bc.id AS baseCurrencyId, bc.code AS baseCurrencyCode, bc.name AS baseCurrencyName, bc.symbol AS baseCurrencySymbol,"
					+ "tc.id AS targetCurrencyId, tc.code AS targetCurrencyCode, tc.name AS targetCurrencyName, tc.symbol AS targetCurrencySymbol"
					+ " FROM exchangerates er"
					+ " JOIN currencies bc ON er.basecurrency = bc.id "
					+ "JOIN currencies tc ON er.targetcurrency = tc.id";
		
		List<ExchangeRate> exchangeRates = jdbcTemplate.query(sql, (rs, rowNum) -> {		
			ExchangeRate eR = new ExchangeRate();
			eR.setId(rs.getInt("exchangeRateId"));
			eR.setRate(rs.getDouble("rate"));
            
			Currency bc = new Currency();
			bc.setId(rs.getInt("baseCurrencyId"));
			bc.setCode(rs.getString("baseCurrencyCode"));
			bc.setName(rs.getString("baseCurrencyName"));
			bc.setSymbol(rs.getString("baseCurrencySymbol").charAt(0));
			
			
			Currency tc = new Currency();
			tc.setId(rs.getInt("targetCurrencyId"));
			tc.setCode(rs.getString("targetCurrencyCode"));
			tc.setName(rs.getString("targetCurrencyName"));
			tc.setSymbol(rs.getString("targetCurrencySymbol").charAt(0));
			
			eR.setBaseCurrency(bc);
			eR.setTargetCurrency(tc);
			
			return eR;
        });
		
		return exchangeRates;
	}
    
    public ExchangeRate getExchangeRate(int id){
		String sql = "SELECT er.id AS exchangeRateId, er.rate, "
					+ "bc.id AS baseCurrencyId, bc.code AS baseCurrencyCode, bc.name AS baseCurrencyName, bc.symbol AS baseCurrencySymbol,"
					+ "tc.id AS targetCurrencyId, tc.code AS targetCurrencyCode, tc.name AS targetCurrencyName, tc.symbol AS targetCurrencySymbol"
					+ " FROM exchangerates er"
					+ " JOIN currencies bc ON er.basecurrency = bc.id "
					+ "JOIN currencies tc ON er.targetcurrency = tc.id"
					+ " WHERE er.id = ?";

    	
		List<ExchangeRate> exchangeRates = jdbcTemplate.query(sql, (rs, rowNum) -> {		
			ExchangeRate eR = new ExchangeRate();
			eR.setId(rs.getInt("exchangeRateId"));
			eR.setRate(rs.getDouble("rate"));
            
			Currency bc = new Currency();
			bc.setId(rs.getInt("baseCurrencyId"));
			bc.setCode(rs.getString("baseCurrencyCode"));
			bc.setName(rs.getString("baseCurrencyName"));
			bc.setSymbol(rs.getString("baseCurrencySymbol").charAt(0));
			
			
			Currency tc = new Currency();
			tc.setId(rs.getInt("targetCurrencyId"));
			tc.setCode(rs.getString("targetCurrencyCode"));
			tc.setName(rs.getString("targetCurrencyName"));
			tc.setSymbol(rs.getString("targetCurrencySymbol").charAt(0));
			
			eR.setBaseCurrency(bc);
			eR.setTargetCurrency(tc);
			
			return eR;
        }, id);
		
		return exchangeRates.get(0);
	}
    
	public int addExchangeRate(ExchangeRate exchangeRateToAdd) {
		String sql = "INSERT INTO exchangerates (basecurrency, targetcurrency, rate) VALUES (?, ?, ?)";
		
	    int rowsAffected = jdbcTemplate.update(sql, exchangeRateToAdd.getBaseCurrency().getId(), exchangeRateToAdd.getTargetCurrency().getId(), exchangeRateToAdd.getRate());
		
		return rowsAffected;
	}
	
	public int deleteExchangeRate(int id) {
		String sql = "DELETE FROM exchangerates WHERE id = ?";
		
		int rowsAffected = jdbcTemplate.update(sql, id);
	
		return rowsAffected;
	}
	
	public Exchange getExchangeDetails(String from, String to) {
		String sql = "SELECT er.rate, "
				+ "bc.id AS baseCurrencyId, bc.code AS baseCurrencyCode, bc.name AS baseCurrencyName, bc.symbol AS baseCurrencySymbol,"
				+ "tc.id AS targetCurrencyId, tc.code AS targetCurrencyCode, tc.name AS targetCurrencyName, tc.symbol AS targetCurrencySymbol"
				+ " FROM exchangerates er"
				+ " JOIN currencies bc ON er.basecurrency = bc.id "
				+ "JOIN currencies tc ON er.targetcurrency = tc.id"
				+ " WHERE bc.code = ? AND tc.code = ?";
	
		
		List<Exchange> exchange = jdbcTemplate.query(sql, (rs, rowNum) -> {		
			Exchange e = new Exchange();
			e.setRate(rs.getDouble("rate"));
            
			Currency bc = new Currency();
			bc.setId(rs.getInt("baseCurrencyId"));
			bc.setCode(rs.getString("baseCurrencyCode"));
			bc.setName(rs.getString("baseCurrencyName"));
			bc.setSymbol(rs.getString("baseCurrencySymbol").charAt(0));
			
			
			Currency tc = new Currency();
			tc.setId(rs.getInt("targetCurrencyId"));
			tc.setCode(rs.getString("targetCurrencyCode"));
			tc.setName(rs.getString("targetCurrencyName"));
			tc.setSymbol(rs.getString("targetCurrencySymbol").charAt(0));
			
			e.setBaseCurrency(bc);
			e.setTargetCurrency(tc);
			
			return e;
		}, from, to);
		return exchange.get(0);
	}
}
