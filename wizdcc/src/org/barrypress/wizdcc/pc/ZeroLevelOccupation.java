package org.barrypress.wizdcc.pc;

public class ZeroLevelOccupation {
	
	private String name = "";
	private String weapon = "";
	private String equipment = "";
	private Integer quantity = 0;
	private String unit = "";
	
	public ZeroLevelOccupation() {
		
		
	}
	
	public String getName() { return name; }
	public String getUnit() { return unit; }
	public String getWeapon() { return weapon; }
	public String getEquipment() { return equipment; }
	public Integer getQuantity() { return quantity; }
	
	public void setName(String nme) { name = nme; }
	public void setWeapon(String wpn) { weapon = wpn; }
	public void setEquipment(String equip) { equipment = equip; }
	public void setUnit(String unt) { unit = unt; }
	public void setQuantity(Integer qt) { quantity = qt; }
}
