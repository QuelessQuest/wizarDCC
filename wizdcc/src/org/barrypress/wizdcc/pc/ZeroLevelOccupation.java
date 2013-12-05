package org.barrypress.wizdcc.pc;

import org.barrypress.wizdcc.screens.MainScreen;

public class ZeroLevelOccupation {
	
	private String name = "";
	private String weapon = "";
	private String equipment = "";
	private Integer quantity = 0;
	private String unit = "";
	
	private Equipment eqWeapon;
	private Equipment eqEquipment;
	
	public ZeroLevelOccupation() {
		eqWeapon    = new Equipment();
		eqEquipment = new Equipment();
	}
	
	public void setEquipment() {
		Equipment stuff = MainScreen.getInstance().getWizDB().getEquipmentByName(weapon);
		if (!stuff.getName().isEmpty()) {
			eqWeapon.setId(stuff.getId());
			eqWeapon.setName(stuff.getName());
			eqWeapon.setDamageDie(stuff.getDamageDie());
			eqWeapon.setDamageNum(stuff.getDamageNum());
			eqWeapon.setRangeLong(stuff.getRangeLong());
			eqWeapon.setRangeMedium(stuff.getRangeMedium());
			eqWeapon.setRangeShort(stuff.getRangeShort());
		}
		stuff = null;
		stuff = MainScreen.getInstance().getWizDB().getEquipmentByName(equipment);
		if (!stuff.getName().isEmpty()) {
			eqEquipment.setId(stuff.getId());
			eqEquipment.setName(stuff.getName());
			if (stuff.getDamageDie() > 0) {
				eqEquipment.setDamageDie(stuff.getDamageDie());
				eqEquipment.setDamageNum(stuff.getDamageNum());
				eqEquipment.setRangeLong(stuff.getRangeLong());
				eqEquipment.setRangeMedium(stuff.getRangeMedium());
				eqEquipment.setRangeShort(stuff.getRangeShort());
			} else {
				if (stuff.getAc() > 0) {
					eqEquipment.setAc(stuff.getAc());
					eqEquipment.setPenalty(stuff.getPenalty());
					eqEquipment.setSpeed(stuff.getSpeed());
					eqEquipment.setFumble(stuff.getFumble());
				}
			}
		}
	}
	
	public String getName() { return name; }
	public String getUnit() { return unit; }
	public String getWeapon() { return weapon; }
	public String getEquipment() { return equipment; }
	public Integer getQuantity() { return quantity; }
	public Equipment getEquipWeapon() { return eqWeapon; }
	public Equipment getEquipEquipment() { return eqEquipment; }
	
	public void setName(String nme) { name = nme; }
	public void setWeapon(String wpn) { weapon = wpn; }
	public void setEquipment(String equip) { equipment = equip; }
	public void setUnit(String unt) { unit = unt; }
	public void setQuantity(Integer qt) { quantity = qt; }
}
