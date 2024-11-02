package base.controller;

import base.model.Currencies;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CurrenciesController {
	
	@GetMapping("/currencies")
	public List<Currencies> getCurrencies() {
		List<Currencies> allCurrencies = new ArrayList<>();
		allCurrencies.add(new Currencies("EUR", "Euro", '€'));
	    return allCurrencies;
	}
	
	@GetMapping("/currency/${code}")
	public String getCurrency(String code) {
		return "";
	}
	
}
