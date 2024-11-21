package base.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import base.model.Exchange;
import base.model.ExchangeRate;
import base.model.ExchangeRateDTO;
import base.service.ExchangeRateService;


@Controller
public class ExchangeRateController {
	private final ExchangeRateService exchangeRateService;
	
	public ExchangeRateController(ExchangeRateService exchangeRateService) {
		this.exchangeRateService = exchangeRateService;
	}
	
	@GetMapping("/exchangeRates")
	public ResponseEntity<List<ExchangeRate>> getExchangeRates() {
		List<ExchangeRate> exchangeRates =  exchangeRateService.getAllExchangeRates();
		
		if(exchangeRates.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	    return new ResponseEntity<>(exchangeRates, HttpStatus.OK);
	}
	
	@GetMapping("/exchangeRates/{id}")
	public ResponseEntity<ExchangeRate> getExchangeRate(@PathVariable("id") int id) {
		ExchangeRate exchangeRates = exchangeRateService.getExchangeRate(id);
		
	    if (exchangeRates == null) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    
	    return new ResponseEntity<>(exchangeRates, HttpStatus.OK);
	}
	
	@PostMapping("/exchangeRates")
	public ResponseEntity<ExchangeRate> addExchangeRates(@RequestBody ExchangeRateDTO rateDTO) {		
		int rowsAffected = exchangeRateService.addExchangeRate(rateDTO);
	    var status = rowsAffected > 0 ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR;
		
		return new ResponseEntity<>(status);
	}
	
	@DeleteMapping("/exchangeRates/{id}")
	public ResponseEntity<ExchangeRate> deleteExchangeRates(@PathVariable("id") int id) {
		int rowsAffected = exchangeRateService.deleteExchangeRate(id);

	    if (rowsAffected == 0) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/exchange")
	public ResponseEntity<Exchange> showExchangeResult(
	        @RequestParam(name = "from") String from,
	        @RequestParam(name = "to") String to,
	        @RequestParam(name = "amount") double amount){
		Exchange exchange = exchangeRateService.getExchangeDetails(from, to, amount);
		
		 if (exchange==null) {
		        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		
		return new ResponseEntity<>(exchange, HttpStatus.OK);
	}
}
