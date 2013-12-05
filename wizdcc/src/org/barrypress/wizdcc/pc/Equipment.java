package org.barrypress.wizdcc.pc;

public class Equipment {

	private Integer id;
	private String name;
	private Integer damageDie;
	private Integer damageNum;
	private Integer rangeShort;
	private Integer rangeMedium;
	private Integer rangeLong;
	private Integer cost;
	private Integer ac;
	private Integer penalty;
	private Integer speed;
	private Integer fumble;
	private Integer quantity;
	private Integer special;
	
	public Equipment() {
		id = 0;
		name = "";
		damageDie   = 0;
		damageNum   = 0;
		rangeShort  = 0;
		rangeMedium = 0;
		rangeLong   = 0;
		cost        = 0;
		ac          = 0;
		penalty     = 0;
		speed       = 0;
		fumble      = 0;
		quantity    = 0;
		special     = 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDamageDie() {
		return damageDie;
	}

	public void setDamageDie(Integer damageDie) {
		this.damageDie = damageDie;
	}

	public Integer getDamageNum() {
		return damageNum;
	}

	public void setDamageNum(Integer damangeNum) {
		this.damageNum = damangeNum;
	}

	public Integer getRangeShort() {
		return rangeShort;
	}

	public void setRangeShort(Integer rangeShort) {
		this.rangeShort = rangeShort;
	}

	public Integer getRangeMedium() {
		return rangeMedium;
	}

	public void setRangeMedium(Integer rangeMedium) {
		this.rangeMedium = rangeMedium;
	}

	public Integer getRangeLong() {
		return rangeLong;
	}

	public void setRangeLong(Integer rangeLong) {
		this.rangeLong = rangeLong;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Integer getAc() {
		return ac;
	}

	public void setAc(Integer ac) {
		this.ac = ac;
	}

	public Integer getPenalty() {
		return penalty;
	}

	public void setPenalty(Integer penalty) {
		this.penalty = penalty;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Integer getFumble() {
		return fumble;
	}

	public void setFumble(Integer fumble) {
		this.fumble = fumble;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getSpecial() {
		return special;
	}

	public void setSpecial(Integer special) {
		this.special = special;
	}
}
