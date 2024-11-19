package base.service;

import java.util.List;

import org.springframework.stereotype.Service;

import base.model.ExchangeRate;
import base.model.ExchangeRateDAO;

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
    
    public int addExchangeRate(ExchangeRate exchangeRateToAdd) {
    	return exchangeRateDAO.addExchangeRate(exchangeRateToAdd);
    }
    
    public int deleteExchangeRate(int id) {
    	return exchangeRateDAO.deleteExchangeRate(id);
    }
}
