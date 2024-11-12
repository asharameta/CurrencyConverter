package base.service;

import java.util.List;

import base.model.Currency;
import base.model.CurrencyDAO;

public class CurrencyService {
	private final CurrencyDAO currencyDAO;

    public CurrencyService(CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }

    public List<Currency> getAllItems() {
        return currencyDAO.getAllCurrencies();
    }
}
