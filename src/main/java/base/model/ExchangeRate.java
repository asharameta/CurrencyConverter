package base.model;

public class ExchangeRate {
	private int id;
	private Currency baseCurrency;
	private Currency targetCurrency;
	private float rate;
	
	public ExchangeRate(Currency  baseCurrency, Currency  targetCurrency, float rate) {
		this.baseCurrency = baseCurrency;
		this.targetCurrency = targetCurrency;
		this.rate = rate;
	}
	
	public ExchangeRate() {
		
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(Currency baseCurrency) {
        this.baseCurrency = baseCurrency;
    }
    
    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }
	
    public double getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
