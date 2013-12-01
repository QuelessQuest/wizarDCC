package org.barrypress.wizdcc.pc;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;

public class Character {
	
	private CombatStats combatStats;
	private Stats stats;
	private Integer level;
	private String name;
	private String className;
	private int classId;
	private List<Equipment> equipment;
	
	public Character() {
		combatStats = new CombatStats();
		stats = new Stats();
		equipment = new ArrayList<Equipment>();
		name      = "";
		className = "";
		classId   = 0;
		level     = 1;
	}
	
	public void reRoll() {
		stats.reRoll();
		name      = "";
		className = "";
		classId   = 0;
		level     = 1;
		combatStats.clear();
	}
	
	public CombatStats getCombatStats() { return combatStats; }
	public Stats getStats() { return stats; }
	public String getName() { return name; }
	public String getClassName() { return className; }
	public int getClassId() { return classId; }
	public Integer getLevel() { return level; }
	public List<Equipment> getEquipment() { return equipment; }
	
	public void setName(String nme) { name = nme; }
	public void setClassName(String nme) { className = nme; }
	public void setClassId(int id) { classId = id; }	
	public void setLevel(int lvl) { level = lvl; }

}
