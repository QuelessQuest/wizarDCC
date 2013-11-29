package org.barrypress.wizdcc.pc;

import org.barrypress.wizdcc.util.Dice;

public class Stats {
	
	Integer strength;
	Integer dexterity;
	Integer constitution;
	Integer intelligence;
	Integer wisdom;
	Integer luck;
	
	public Stats() {
		strength     = Dice.d6(3);
		dexterity    = Dice.d6(3);
		constitution = Dice.d6(3);
		intelligence = Dice.d6(3);
		wisdom       = Dice.d6(3);
		luck         = Dice.d6(3);
	}
	
	public void reRoll() {
		strength     = Dice.d6(3);
		dexterity    = Dice.d6(3);
		constitution = Dice.d6(3);
		intelligence = Dice.d6(3);
		wisdom       = Dice.d6(3);
		luck         = Dice.d6(3);
	}
	
	public Integer getStrength() { return strength; }
	public Integer getDexterity() { return dexterity; }
	public Integer getConstitution() { return constitution; }
	public Integer getIntelligence() { return intelligence; }
	public Integer getWisdom() { return wisdom; }
	public Integer getLuck() { return luck; }
	
	public void setStrength(Integer str) { strength = str; }
	public void setDexterity(Integer dex) { dexterity = dex; }
	public void setConstitution(Integer con) { constitution = con; }
	public void setIntelligence(Integer intel) { intelligence = intel ;}
	public void setWisdom(Integer wis) { wisdom = wis; }
	public void setLuck(Integer luc) { luck = luc; }

}
