package base.controller;

import base.model.Currency;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class CurrenciesController {
	private List<Currency> currencies = new ArrayList<>();
	
	{
		currencies.add(new Currency("USD", "Dollar", '$'));
	}
	
	
	@GetMapping("/currencies")
	public ResponseEntity<List<Currency>> getCurrencies() {
	    return new ResponseEntity<>(currencies, HttpStatus.OK);
	}
	
	@GetMapping("/currency/{code}")
	public ResponseEntity<Currency> getCurrency(@PathVariable int code) {
		return new ResponseEntity<>(currencies.get(code), HttpStatus.OK);
	}
	
	
	@PostMapping("/currencies")
	public void addCurrencies(@RequestBody Currency currencyToAdd) {
		currencies.add(currencyToAdd);
	}
}
