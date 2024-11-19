package base.controller;

import base.model.Currency;
import base.service.CurrencyService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@GetMapping("/currencies/{code}")
	public ResponseEntity<Currency> getCurrency(@PathVariable("code") String code) {
		Currency currencyToReturn = currencyService.getCurrency(code);
		
	    if (currencyToReturn == null) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    
	    return new ResponseEntity<>(currencyToReturn, HttpStatus.OK);
	}
	
	@PostMapping("/currencies")
	public ResponseEntity<Currency> addCurrency(@RequestBody Currency currencyToAdd) {
		int rowsAffected = currencyService.addCurrency(currencyToAdd);
	    var status = rowsAffected > 0 ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR;
		
		return new ResponseEntity<>(currencyToAdd, status);
	}
	
	@DeleteMapping("/currencies/{code}")
	public ResponseEntity<Currency> deleteCurrency(@PathVariable("code") String code) {
		int rowsAffected = currencyService.deleteCurrency(code);
		var status = rowsAffected > 0 ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;

	    return new ResponseEntity<>(status);
	}
}
