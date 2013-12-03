package org.barrypress.wizdcc.pc;

import org.barrypress.wizdcc.util.Dice;

public class Stats {
	
	Integer strength;
	Integer agility;
	Integer stamina;
	Integer intelligence;
	Integer personality;
	Integer luck;
	
	public Stats() {
		strength     = Dice.d6(3);
		agility      = Dice.d6(3);
		stamina      = Dice.d6(3);
		intelligence = Dice.d6(3);
		personality  = Dice.d6(3);
		luck         = Dice.d6(3);
	}
	
	public void reRoll() {
		strength     = Dice.d6(3);
		agility      = Dice.d6(3);
		stamina      = Dice.d6(3);
		intelligence = Dice.d6(3);
		personality  = Dice.d6(3);
		luck         = Dice.d6(3);
	}
	
	private Integer getAdjustment(Integer stat) {
		Integer adj = 0;
		
		switch (stat) {
		case 3: 
			adj = -3;
			break;
		case 4: case 5:
			adj = -2;
			break;
		case 6: case 7: case 8: 
			adj = -1;
			break;
		case 13: case 14: case 15:
			adj = 1;
			break;
		case 16: case 17:
			adj = 2;
			break;
		case 18:
			adj = 3;
			break;
		default:
			adj = 0;				
		}
		
		return adj;
	}
	
	public Integer getAdjStrength() { return getAdjustment(strength); }
	public Integer getAdjAgility() { return getAdjustment(agility); }
	public Integer getAdjStamina() { return getAdjustment(stamina); }
	public Integer getAdjIntelligence() { return getAdjustment(intelligence); }
	public Integer getAdjPersonality() { return getAdjustment(personality); }
	public Integer getAdjLuck() { return getAdjustment(luck); }
	
	public Integer getStrength() { return strength; }
	public Integer getAgility() { return agility; }
	public Integer getStamina() { return stamina; }
	public Integer getIntelligence() { return intelligence; }
	public Integer getPersonality() { return personality; }
	public Integer getLuck() { return luck; }
	
	public void setStrength(Integer str) { strength = str; }
	public void setAgility(Integer dex) { agility = dex; }
	public void setStamina(Integer con) { stamina = con; }
	public void setIntelligence(Integer intel) { intelligence = intel ;}
	public void setPersonality(Integer wis) { personality = wis; }
	public void setLuck(Integer luc) { luck = luc; }

}
