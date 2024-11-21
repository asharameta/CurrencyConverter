package base.model;

public class ExchangeRateDTO {
	private int baseCurrency;
	private int targetCurrency;
	private double rate;
	
	
	public ExchangeRateDTO(int baseCurrency, int targetCurrency, double rate) {
		this.baseCurrency = baseCurrency;
		this.targetCurrency = targetCurrency;
		this.rate = rate;
	}
	
	public ExchangeRateDTO() {
		
	}
	
	
	public int getBaseCurrency() {
		return baseCurrency;
	}
	
	public void setBaseCurrency(int baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	
	public int getTargetCurrency() {
		return targetCurrency;
	}
	
	public void setTargetCurrency(int targetCurrency) {
		this.targetCurrency = targetCurrency;
	}
	
	public double getRate() {
		return rate;
	}
	
	public void setRate(double rate) {
		this.rate = rate;
	}
	
    @Override
    public String toString() {
    	return "ExchangeRateDTO {" +
                "baseCurrency='" + baseCurrency + '\'' +
                ", targetCurrency='" + targetCurrency + '\'' +
                ", rate='" + rate + '\'' +
                '}';
    }
}
