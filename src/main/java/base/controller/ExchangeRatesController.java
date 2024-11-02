package base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ExchangeRatesController {
	
	@GetMapping("/exchangeRates")
	public String getExchangeRates() {
		return "";
	}
	
	@GetMapping("/exchangeRate/${codes}")
	public String getCurrency(String firstCode, String secondCode) {
		return "";
	}
	
	@PostMapping("/exchangeRates")
	public void addExchangeRates() {
	}
}
