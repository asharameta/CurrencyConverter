package base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import base.model.Currency;
import base.model.ExchangeRate;

@Controller
public class ExchangeRatesController {
	
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExchangeRatesController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
	
	@GetMapping("/exchangeRates")
	public ResponseEntity<List<ExchangeRate>> getExchangeRates() {
        String sql = "SELECT e.id, e.basecurrency, e.targetcurrency, e.rate, " +
                "bc.name as base_name, bc.code as base_code, bc.symbol as base_symbol, " +
                "tc.name as target_name, tc.code as target_code, tc.symbol as target_symbol " +
                "FROM exchangerates e " +
                "JOIN currencies bc ON e.basecurrency = bc.id " +
                "JOIN currencies tc ON e.targetcurrency = tc.id";

        List<ExchangeRate> exchangeRates = jdbcTemplate.query(sql, new RowMapper<ExchangeRate>() {
	       @Override
	       public ExchangeRate mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
	           ExchangeRate exchangeRate = new ExchangeRate();
	           exchangeRate.setId(rs.getInt("id"));
	           exchangeRate.setRate(rs.getFloat("rate"));
	           
	           Currency baseCurrency = new Currency();
	           baseCurrency.setId(rs.getInt("basecurrency"));
	           baseCurrency.setName(rs.getString("base_name"));
	           baseCurrency.setCode(rs.getString("base_code"));
	           baseCurrency.setSymbol(rs.getString("base_symbol").charAt(0));
	           exchangeRate.setBaseCurrency(baseCurrency);
	
	           Currency targetCurrency = new Currency();
	           targetCurrency.setId(rs.getInt("targetcurrency"));
	           targetCurrency.setName(rs.getString("target_name"));
	           targetCurrency.setCode(rs.getString("target_code"));
	           targetCurrency.setSymbol(rs.getString("target_symbol").charAt(0));
	           exchangeRate.setTargetCurrency(targetCurrency);
	
	           return exchangeRate;
	       }
	   });
        
		if(exchangeRates.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	    return new ResponseEntity<>(exchangeRates, HttpStatus.OK);
	}
	
//	@GetMapping("/exchangeRates/{id}")
//	public ResponseEntity<ExchangeRate> getExchangeRates(@PathVariable("id") int id) {
//		String sql = "SELECT * FROM exchangerates WHERE id = ?";
//		
//		List<ExchangeRate> exchangeRates = jdbcTemplate.query(sql, (rs, rowNum) -> {
//			ExchangeRate er = new ExchangeRate();
//			Currency c = new Currency();
//			er.setId(rs.getInt("id"));
//            er.setBaseCurrencyId((rs.getInt("basecurrency")));
//            er.setTargetCurrencyId(rs.getInt("targetcurrency"));
//            er.setRate(rs.getFloat("rate"));
//            return er;
//        });
//		
//	    if (exchangeRates == null) {
//	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
//	    
//	    return new ResponseEntity<>(exchangeRates.get(0), HttpStatus.OK);
//	}
	
//	@PostMapping("/exchangeRates")
//	public ResponseEntity<ExchangeRate> addExchangeRates(@RequestBody ExchangeRate ratesToAdd) {
//		 String sql = "INSERT INTO exchangerates (basecurrency, targetcurrency, rate) VALUES (?, ?, ?)";
//	        int rowsAffected = jdbcTemplate.update(sql, ratesToAdd.getBaseCurrency().getId(),
//	                ratesToAdd.getTargetCurrency().getId(), ratesToAdd.getRate());
//
//	        if (rowsAffected > 0) {
//	            // Get the inserted exchange rate ID (optional, assuming you have an ID in your table)
//	            String selectSql = "SELECT id FROM exchangerates WHERE basecurrency = ? AND targetcurrency = ? AND rate = ?";
//	            int exchangeRateId = jdbcTemplate.queryForObject(selectSql, Integer.class, 
//	                ratesToAdd.getBaseCurrency().getId(), ratesToAdd.getTargetCurrency().getId(), ratesToAdd.getRate());
//
//	            // Set the ID on the ExchangeRate object
//	            ratesToAdd.setId(exchangeRateId);
//
//	            // Fetch the full Currency objects based on the IDs
//	            Currency baseCurrency = getCurrencyById(ratesToAdd.getBaseCurrency().getId());
//	            Currency targetCurrency = getCurrencyById(ratesToAdd.getTargetCurrency().getId());
//
//	            // Set the full Currency objects
//	            ratesToAdd.setBaseCurrency(baseCurrency);
//	            ratesToAdd.setTargetCurrency(targetCurrency);
//	        }
//
//	        return ratesToAdd;
//	}
//	
//	@DeleteMapping("/exchangeRates/{id}")
//	public ResponseEntity<ExchangeRate> deleteExchangeRates(@PathVariable("id") int id) {
//		String sql = "DELETE FROM exchangerates WHERE id = ?";
//		int rowsAffected = jdbcTemplate.update(sql, id);
//
//	    if (rowsAffected == 0) {
//	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
//
//	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	}
}
