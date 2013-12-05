package org.barrypress.wizdcc.util;

public class CharacterFlatDB {
	
	Integer id;
	Integer level;
	String  name;
	String  className;
	Integer strength;
	Integer agility;
	Integer stamina;
	Integer intelligence;
	Integer personality;
	Integer luck;
	Integer hp;
	Integer ac;
	Integer funds;
	
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Integer getStrength() {
		return strength;
	}
	public void setStrength(Integer strength) {
		this.strength = strength;
	}
	public Integer getAgility() {
		return agility;
	}
	public void setAgility(Integer agility) {
		this.agility = agility;
	}
	public Integer getStamina() {
		return stamina;
	}
	public void setStamina(Integer stamina) {
		this.stamina = stamina;
	}
	public Integer getIntelligence() {
		return intelligence;
	}
	public void setIntelligence(Integer intelligence) {
		this.intelligence = intelligence;
	}
	public Integer getPersonality() {
		return personality;
	}
	public void setPersonality(Integer personality) {
		this.personality = personality;
	}
	public Integer getLuck() {
		return luck;
	}
	public void setLuck(Integer luck) {
		this.luck = luck;
	}
	public Integer getHp() {
		return hp;
	}
	public void setHp(Integer hp) {
		this.hp = hp;
	}
	public Integer getAc() {
		return ac;
	}
	public void setAc(Integer ac) {
		this.ac = ac;
	}
	public void setFunds(Integer funds) { this.funds = funds; }
	public Integer getFunds() { return this.funds; }

}
