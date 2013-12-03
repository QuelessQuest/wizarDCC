package org.barrypress.wizdcc.pc;

public class ZeroLevelOccupation {
	
	private String name = "";
	private Integer weapon = 0;
	private Integer equipment = 0;
	
	public ZeroLevelOccupation() {
		
		
	}
	
	public String getName() { return name; }
	public Integer getWeapon() { return weapon; }
	public Integer getEquipment() { return equipment; }
	
	public void setName(String nme) { name = nme; }
	public void setWeapon(Integer wpn) { weapon = wpn; }
	public void setEquipment(Integer equip) { equipment = equip; }
}
