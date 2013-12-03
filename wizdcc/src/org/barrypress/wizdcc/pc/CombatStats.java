package org.barrypress.wizdcc.pc;

public class CombatStats {

	private Integer maxHP;
	private Integer curHP;
	private Integer maxAC;
	private Integer curAC;
	private Integer speed;
	private Integer reflex;
	private Integer will;
	private Integer fortitude;
	private Integer initiative;
	
	public CombatStats() {
		maxHP = 0;
		curHP = 0;
		maxAC = 0;
		curAC = 0;
		speed = 0;
		reflex     = 0;
		will       = 0;
		fortitude  = 0;
		initiative = 0;
	}
	
	public void clear() {
		maxHP = 0;
		curHP = 0;
		maxAC = 0;
		curAC = 0;
		speed = 0;
		reflex     = 0;
		will       = 0;
		fortitude  = 0;
		initiative = 0;
	}
	
	public Integer getMaxHP() { return maxHP; }
	public Integer getCurrentHP() { return curHP; }
	public Integer getMaxAC() { return maxAC; }
	public Integer getCurrentAC() { return curAC; }
	public Integer getSpeed() { return speed; }
	public Integer getReflex() { return reflex; }
	public Integer getWill() { return will; }
	public Integer getFortitude() { return fortitude; }
	public Integer getInitiative() { return initiative; }
	
	public void setMaxHP(Integer hp) { this.maxHP = hp; }
	public void setCurrentHP(Integer hp) { this.curHP = hp; }
	public void setMaxAC(Integer ac) { this.maxAC = ac; }
	public void setCurrentAC(Integer ac) { this.maxAC = ac; }
	public void setSpeed(Integer spd) { this.speed = spd; }
	public void setReflex(Integer rfx) { this.reflex = rfx; }
	public void setWill(Integer wll) { this.will = wll; }
	public void setFortitude(Integer ftd) { this.fortitude = ftd; }
	public void setInitiative(Integer intv) { this.initiative = intv; }
}
