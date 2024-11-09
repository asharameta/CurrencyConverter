package base.model;

public class ExchangeRate {
	private int id;
	private int baseCurrencyId;
	private int targetCurrencyId;
	private float rate;
	
	public ExchangeRate(int baseCurrencyId, int targetCurrencyId, float rate) {
		this.baseCurrencyId = baseCurrencyId;
		this.targetCurrencyId = targetCurrencyId;
		this.rate = rate;
	}
	
	public ExchangeRate() {
		
	}

    public int getBaseCurrencyId() {
        return baseCurrencyId;
    }

    public void setBaseCurrencyId(int baseCurrencyId) {
        this.baseCurrencyId = baseCurrencyId;
    }
    
    public double getTargetCurrencyId() {
        return targetCurrencyId;
    }

    public void setTargetCurrencyId(int targetCurrencyId) {
        this.targetCurrencyId = targetCurrencyId;
    }
	
    public double getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
