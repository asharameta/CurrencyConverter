package base.model;

public class Exchange {
	private Currency baseCurrency;
	private Currency targetCurrency;
	private double rate;
	private double amount;
	private double convertedAmount;
	
	public Exchange(Currency baseCurrency, Currency targetCurrency, double rate, double amount, double convertedAmount) {
		this.baseCurrency = baseCurrency;
		this.targetCurrency = targetCurrency;
		this.rate = rate;
		this.amount = amount;
		this.convertedAmount = convertedAmount;
	}
	
	public Exchange() {
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
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getConvertedAmount() {
		return convertedAmount;
	}
	public void setConvertedAmount(double convertedAmount) {
		this.convertedAmount = convertedAmount;
	}
	
    @Override
    public String toString() {
    	return "Exchange {" +
                "baseCurrency='" + baseCurrency + '\'' +
                ", targetCurrency='" + targetCurrency + '\'' +
                ", rate='" + rate + '\'' +
                ", amount='" + amount + '\'' +
                ", convertedAmount='" + convertedAmount + '\'' +
                '}';
    }
}
