package base.service;

import java.util.List;

import org.springframework.stereotype.Service;

import base.model.Currency;
import base.model.CurrencyDAO;

@Service
public class CurrencyService {
	private final CurrencyDAO currencyDAO;

    public CurrencyService(CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }

    public List<Currency> getAllItems() {
        return currencyDAO.getAllCurrencies();
    }
    
    public int addCurrency(Currency currencyToAdd) {
    	return currencyDAO.addCurrency(currencyToAdd);
    }
    
    public Currency getCurrency(String code) {
    	return currencyDAO.getCurrency(code);
    }
    
	public int deleteCurrency(String code) {
		return currencyDAO.deleteCurrency(code);
	}
}
