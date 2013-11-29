package org.barrypress.wizdcc.pc;

public class Character {
	
	private Stats stats;
	private String name;
	
	public Character() {
		stats = new Stats();
	}
	
	public void reRoll() {
		stats.reRoll();
	}
	
	public Stats getStats() { return stats; }
	public String getName() { return name; }
	
	public void setName(String nme) { name = nme; }
	

}
