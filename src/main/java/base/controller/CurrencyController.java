package base.controller;

import base.model.Currency;
import base.service.CurrencyService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class CurrencyController {
	private final CurrencyService currencyService;
	
	public CurrencyController(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	
	@GetMapping("/currencies")
	public ResponseEntity<List<Currency>> getCurrencies() {
		List<Currency> currencies = currencyService.getAllItems();
		
		if(currencies.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	    return new ResponseEntity<>(currencies, HttpStatus.OK);
	}
	
//	@GetMapping("/currencies/{code}")
//	public ResponseEntity<Currency> getCurrency(@PathVariable("code") String code) {
//		String sql = "SELECT * FROM currencies WHERE code = ?";
//		
//		List<Currency> currencyToReturn = jdbcTemplate.query(sql, (rs, rowNum) -> {
//            Currency c = new Currency();
//            c.setId(Integer.parseInt(rs.getString("id")));
//            c.setCode(rs.getString("code"));
//            c.setName(rs.getString("name"));
//            c.setSymbol(rs.getString("symbol").charAt(0));
//            return c;
//        });
//		
//	    if (currencyToReturn == null) {
//	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
//	    
//	    return new ResponseEntity<>(currencyToReturn.get(0), HttpStatus.OK);
//	}
//	
//	
//	@PostMapping("/currencies")
//	public ResponseEntity<Currency> addCurrency(@RequestBody Currency currencyToAdd) {
//		String sql = "INSERT INTO currencies (code, name, symbol) VALUES (?, ?, ?)";
//	    int rowsAffected = jdbcTemplate.update(sql, currencyToAdd.getCode(), currencyToAdd.getName(), currencyToAdd.getSymbol());
//
//	    var status = rowsAffected > 0 ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR;
//		
//		return new ResponseEntity<>(currencyToAdd, status);
//	}
//	
//	@DeleteMapping("/currencies/{code}")
//	public ResponseEntity<Currency> deleteCurrency(@PathVariable("code") String code) {
//		String sql = "DELETE FROM currencies WHERE code = ?";
//		int rowsAffected = jdbcTemplate.update(sql, code);
//
//	    if (rowsAffected == 0) {
//	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
//
//	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	}
}
