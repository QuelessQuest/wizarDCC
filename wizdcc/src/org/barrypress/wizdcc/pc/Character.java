package org.barrypress.wizdcc.pc;

public class Character {
	
	private Stats stats;
	private String name;
	private String className;
	private int classId;
	
	public Character() {
		stats = new Stats();
	}
	
	public void reRoll() {
		stats.reRoll();
		className = "";
		classId   = 0;
	}
	
	public Stats getStats() { return stats; }
	public String getName() { return name; }
	public String getClassName() { return className; }
	public int getClassId() { return classId; }
	
	public void setName(String nme) { name = nme; }
	public void setClassName(String nme) { className = nme; }
	public void setClassId(int id) { classId = id; }	

}
