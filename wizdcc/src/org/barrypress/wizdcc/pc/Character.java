package org.barrypress.wizdcc.pc;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;

import org.barrypress.wizdcc.screens.MainScreen;
import org.barrypress.wizdcc.util.Dice;
import org.barrypress.wizdcc.util.Funds;

public class Character {
	
	private ZeroLevelOccupation zeroLevel;
	private CombatStats combatStats;
	private Stats stats;
	private Funds funds;
	private Integer level;
	private String alignment;
	private String name;
	private String className;
	private String race;
	private int classId;
	private List<Equipment> equipment;
	
	public Character() {
		stats = new Stats();
		zeroLevel = MainScreen.getInstance().getWizDB().getZeroLevelOccupation();
		zeroLevel.setEquipment();
		combatStats = new CombatStats();
		setCombatStats();
		equipment = new ArrayList<Equipment>();
		alignment = "";
		name      = "";
		race      = "Human";
		className = "";
		classId   = 0;
		level     = 0;
		funds = new Funds();
		setInitialFunds();
	}
	
	public void reRoll() {
		combatStats.clear();
		stats.reRoll();
		setCombatStats();
		zeroLevel = null;
		zeroLevel = MainScreen.getInstance().getWizDB().getZeroLevelOccupation();
		zeroLevel.setEquipment();
		alignment = "";
		name      = "";
		race      = "Human";
		className = "";
		classId   = 0;
		level     = 0;
		funds     = new Funds();
		setInitialFunds();
	}
	
	private void setCombatStats() {
		combatStats.setMaxHP(Dice.d4() + stats.getAdjStamina());
		combatStats.setMaxHP(combatStats.getMaxHP() < 1 ? 1 : combatStats.getMaxHP());
		combatStats.setMaxAC(10 + stats.getAdjAgility());
		combatStats.setReflex(stats.getAdjAgility());
		combatStats.setWill(stats.getAdjPersonality());
		combatStats.setFortitude(stats.getAdjStamina());
		combatStats.setInitiative(stats.getAdjAgility());
		combatStats.setSpeed(30);
	}
	
	private void setInitialFunds() {
		funds.addToTotal(Dice.d12(5));
		if (zeroLevel.getEquipment().equals("Copper")) {
			funds.addToTotal(zeroLevel.getQuantity());
		}
	}
	
	public ZeroLevelOccupation getZeroLevelOccupation() { return zeroLevel; }
	public CombatStats getCombatStats() { return combatStats; }
	public Stats getStats() { return stats; }
	public String getAlignment() { return alignment; }
	public String getName() { return name; }
	public String getRace() { return race; }
	public String getClassName() { return className; }
	public int getClassId() { return classId; }
	public Integer getLevel() { return level; }
	public List<Equipment> getEquipment() { return equipment; }
	public Funds getFunds() { return funds; }
	
	public void setAlignment(String algn) { alignment = algn; }
	public void setName(String nme) { name = nme; }
	public void setClassName(String nme) { className = nme; }
	public void setClassId(int id) { classId = id; }	
	public void setLevel(int lvl) { level = lvl; }
	public void setFunds(Funds money) { funds = money; }
}
