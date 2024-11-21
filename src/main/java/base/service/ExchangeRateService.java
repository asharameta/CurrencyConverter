package base.service;

import java.util.List;

import org.springframework.stereotype.Service;

import base.model.Currency;
import base.model.Exchange;
import base.model.ExchangeRate;
import base.model.ExchangeRateDAO;
import base.model.ExchangeRateDTO;

@Service
public class ExchangeRateService {
	private final ExchangeRateDAO exchangeRateDAO;

    public ExchangeRateService(ExchangeRateDAO exchangeRateDAO) {
        this.exchangeRateDAO = exchangeRateDAO;
    }
    
    public List<ExchangeRate> getAllExchangeRates(){
    	return exchangeRateDAO.getAllExchangeRates();
    }
    
    public ExchangeRate getExchangeRate(int id){
    	return exchangeRateDAO.getExchangeRate(id);
    }
    
    public int addExchangeRate(ExchangeRateDTO rateDTO) {
    	Currency baseCurrency = new Currency();
		baseCurrency.setId(rateDTO.getBaseCurrency());
		
		Currency targetCurrency = new Currency();
		targetCurrency.setId(rateDTO.getTargetCurrency());
		
		ExchangeRate exchangeRate = new ExchangeRate(baseCurrency, targetCurrency, rateDTO.getRate());

    	return exchangeRateDAO.addExchangeRate(exchangeRate);
    }
    
    public int deleteExchangeRate(int id) {
    	return exchangeRateDAO.deleteExchangeRate(id);
    }
    
    public Exchange getExchangeDetails(String from, String to, double amount) {
    	Exchange exchange = exchangeRateDAO.getExchangeDetails(from, to);
    	
    	exchange.setAmount(amount);
    	
    	exchange.setConvertedAmount(amount*exchange.getRate());
    	
    	return exchange;
    }
}
