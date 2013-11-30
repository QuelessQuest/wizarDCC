package org.barrypress.wizdcc.pc;

public class CombatStats {

	private Integer maxHP;
	private Integer curHP;
	private Integer maxAC;
	private Integer curAC;
	
	public CombatStats() {
		maxHP = 0;
		curHP = 0;
		maxAC = 0;
		curAC = 0;
	}
	
	public void clear() {
		maxHP = 0;
		curHP = 0;
		maxAC = 0;
		curAC = 0;
	}
	
	public Integer getMaxHP() { return maxHP; }
	public Integer getCurrentHP() { return curHP; }
	public Integer getMaxAC() { return maxAC; }
	public Integer getCurrentAC() { return curAC; }
	
	public void setMaxHP(Integer hp) { this.maxHP = hp; }
	public void setCurrentHP(Integer hp) { this.curHP = hp; }
	public void setMaxAC(Integer ac) { this.maxAC = ac; }
	public void setCurrentAC(Integer ac) { this.maxAC = ac; }
}
