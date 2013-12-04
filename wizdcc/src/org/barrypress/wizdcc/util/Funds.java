package org.barrypress.wizdcc.util;

public class Funds {
	
	private Integer totalFunds;
	private Integer copper;
	private Integer silver;
	private Integer gold;
	
	public Funds() {
		copper = 0;
		silver = 0;
		gold   = 0;
		totalFunds = 0;
	}
	
	public Integer getCopper() { return copper; }
	public Integer getSilver() { return silver; }
	public Integer getGold()   { return gold; }
	public Integer getTotal()  { return totalFunds; }
	
	public void addToTotal(Integer money) {
		totalFunds += money;
		calculateCoins();
	}
	
	public void calculateCoins() {
		gold = totalFunds / 100;
		silver = (totalFunds % 100) / 10;
		copper = (totalFunds % 100) % 10;
	}

}
