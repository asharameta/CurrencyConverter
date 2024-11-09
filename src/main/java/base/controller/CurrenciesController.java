package base.controller;

import base.model.Currency;
import java.util.ArrayList;
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
public class CurrenciesController {
	private List<Currency> currencies = new ArrayList<>();
	
	{
		currencies.add(new Currency("USD", "Dollar", '$'));
		currencies.add(new Currency("EUR", "Euro", '€'));
	}
	
	
	@GetMapping("/currencies")
	public ResponseEntity<List<Currency>> getCurrencies() {
	    return new ResponseEntity<>(currencies, HttpStatus.OK);
	}
	
	@GetMapping("/currencies/{code}")
	public ResponseEntity<Currency> getCurrency(@PathVariable("code") String code) {
		Currency currencyToReturn = currencies.stream().filter(ccy -> code.equals(ccy.getCode()) ).findFirst()
		        .orElse(null);
		
	    if (currencyToReturn == null) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    
	    return new ResponseEntity<>(currencyToReturn, HttpStatus.OK);
	}
	
	
	@PostMapping("/currencies")
	public ResponseEntity<Currency> addCurrency(@RequestBody Currency currencyToAdd) {
		currencies.add(currencyToAdd);
		return new ResponseEntity<>(currencyToAdd, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/currencies/{code}")
	public ResponseEntity<Currency> deleteCurrency(@PathVariable("code") String code) {
		Currency currencyToDelete = currencies.stream()
				.filter(ccy -> code.equals(ccy.getCode()))
				.findFirst()
		        .orElse(null);
		
	    if (currencyToDelete == null) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    
	    currencies.remove(currencyToDelete);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
