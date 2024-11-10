package base.model;

public class Currency {
	private int id;
	private String code;
	private String name;
	private char symbol;
	
	public Currency(String code, String name, char symbol) {
		this.code = code;
		this.name = name;
		this.symbol = symbol;
	}
	
	public Currency() {
		
	}
	
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
	
	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
