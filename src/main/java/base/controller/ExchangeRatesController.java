package base.controller;

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

import base.model.ExchangeRate;

@Controller
public class ExchangeRatesController {
	
	private List<ExchangeRate> exchangeRates = new ArrayList<>();
	
	{
		exchangeRates.add(new ExchangeRate(5, 7, 1.22f));
		exchangeRates.add(new ExchangeRate(3, 1, 1.34f));
	}
	
	
	@GetMapping("/exchangeRates")
	public ResponseEntity<List<ExchangeRate>> getExchangeRates() {
		return new ResponseEntity<>(exchangeRates, HttpStatus.OK);
	}
	
	@GetMapping("/exchangeRates/{id}")
	public ResponseEntity<ExchangeRate> getExchangeRates(@PathVariable("id") int id) {
		ExchangeRate ratesToReturn = exchangeRates.get(id);
		
	    if (ratesToReturn == null) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    
	    return new ResponseEntity<>(ratesToReturn, HttpStatus.OK);
	}
	
	@PostMapping("/exchangeRates")
	public ResponseEntity<ExchangeRate> addExchangeRates(@RequestBody ExchangeRate ratesToAdd) {
		exchangeRates.add(ratesToAdd);
		return new ResponseEntity<>(ratesToAdd, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/exchangeRates/{id}")
	public ResponseEntity<ExchangeRate> deleteExchangeRates(@PathVariable("id") int id) {
		ExchangeRate ratesToDelete = exchangeRates.get(id);
		
	    if (ratesToDelete == null) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    
	    exchangeRates.remove(ratesToDelete);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
