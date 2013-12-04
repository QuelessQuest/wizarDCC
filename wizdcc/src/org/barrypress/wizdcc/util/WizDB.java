package org.barrypress.wizdcc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

import org.barrypress.wizdcc.pc.Character;
import org.barrypress.wizdcc.pc.ZeroLevelOccupation;

public class WizDB {
	
    private Connection conn;

    public WizDB() {
    	try {
    		conn = DriverManager.getConnection("jdbc:derby:wizdccDB");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public Connection getConnection() { return conn; }
    
    public List<String> getClassList() {
    	List<String> cList = new ArrayList<String>();
    	try {
    		Statement stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT name from class");
    		
    		while (rs.next()) {
    		    String cName = rs.getString("name");
    		    cList.add(cName);
    		}
    		rs.close();
    		stmt.close();
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return cList;
    }
    
    public int getClassId(String className) {
    	int rc = 0;
    	try {
    		Statement stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT id from class where name='" + className + "'");
    		while (rs.next()) {
    			rc = rs.getInt("id");
    		}
    		stmt.close();
    		rs.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return rc;
    }
    
    public Boolean validateName(String name) {
    	Boolean valid = true;
    	
    	try {
    		Statement stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery("select name from ClassWorkList where name = '" + name + "'");
    		while (rs.next()) {
    			valid = false;
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return valid;
    }
    
    public List<CharacterFlatDB> getCharacterWorkList() {
    	return getWorkList("ClassWorkList");
    }
    
    public Boolean verifyFunnel() {
    	Boolean valid = true;
    	
    	try {
    		Statement stmt = conn.createStatement();
    		Integer maxLvl = 0;
    		ResultSet rs = stmt.executeQuery("select max(level) as mlvl from PartyWorkList");
    		while (rs.next()) {
    			maxLvl = rs.getInt("mlvl");
    		}
    		if (maxLvl > 0) {
    			valid = false;
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return valid;
    }
    
    public Integer getPartyCount() {
    	Integer count = 0;
    	
    	try {
    		Statement stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery("select count(level) as mlvl from PartyWorkList");
    		while (rs.next()) {
    			count = rs.getInt("mlvl");
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return count;
    	
    }
    
    public void loadData() {
    	clearList("ClassWorkList");
    	clearList("PartyWorkList");
    	try {
    		Statement stmt = conn.createStatement();
    		stmt.executeUpdate("INSERT into ClassWorkList (Select * from CharacterList)");
    		stmt.executeUpdate("INSERT into PartyWorkList (Select * from PartyList)");
    		stmt.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void saveData() {
    	clearList("CharacterList");
    	clearList("PartyList");
    	try {
    		Statement stmt = conn.createStatement();
    		stmt.executeUpdate("INSERT into CharacterList (Select * from ClassWorkList)");
    		stmt.executeUpdate("INSERT into PartyList (Select * from PartyWorkList)");
    		stmt.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void updateCharacterWorkList(Character guy) {
    	updateWorkList(makeFlat(guy), "ClassWorkList");
    }
    
    public void deleteFromCharacterWorkList(Integer id) {
    	deleteFromWorkList(id, "ClassWorkList");
    }
    
    public void clearCharacterWorkList() {
    	clearList("ClassWorkList");
    }
    
    public List<CharacterFlatDB> getPartyWorkList() {
    	return getWorkList("PartyWorkList");
    }
    
    public void updatePartyWorkList(CharacterFlatDB guy) {
    	updateWorkList(guy, "PartyWorkList");
    }
    
    public void deleteFromPartyWorkList(Integer id) {
    	deleteFromWorkList(id, "PartyWorkList");
    }
    
    public void clearPartyWorkList() {
    	clearList("PartyWorkList");
    }
    
    public List<CharacterFlatDB> getAvailableWorkList() {
    	List<CharacterFlatDB> theList = new ArrayList<CharacterFlatDB>();
		String query = "select id, level, name, className, strength, agility, stamina, intelligence, personality, luck, hp, ac from ";
    	query += "ClassWorkList where not exists (select * from PartyWorkList where ";
		query += "PartyWorkList.name = ClassWorkList.name)";
		
		try {
    		Statement stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery(query);
    		
    		while (rs.next()) {
    			CharacterFlatDB aChar = new CharacterFlatDB();
    			aChar.setId(rs.getInt("id"));
    			aChar.setLevel(rs.getInt("level"));
    			aChar.setName(rs.getString("name"));
    			aChar.setClassName(rs.getString("className"));
    			aChar.setStrength(rs.getInt("strength"));
    			aChar.setAgility(rs.getInt("agility"));
    			aChar.setStamina(rs.getInt("stamina"));
    			aChar.setIntelligence(rs.getInt("intelligence"));
    			aChar.setPersonality(rs.getInt("personality"));
    			aChar.setLuck(rs.getInt("luck"));
    			aChar.setHp(rs.getInt("hp"));
    			aChar.setAc(rs.getInt("ac"));
    			theList.add(aChar);
    		}
    		
    		stmt.close();
    		rs.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    	return theList;
    }
    
    private List<CharacterFlatDB> getWorkList(String tableName) {
    	List<CharacterFlatDB> theList = new ArrayList<CharacterFlatDB>();
    	
    	try {
    		Statement stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery("select id, level, name, className, strength, agility, stamina, intelligence, personality, luck, hp, ac from " + tableName);
    		
    		while (rs.next()) {
    			CharacterFlatDB aChar = new CharacterFlatDB();
    			aChar.setId(rs.getInt("id"));
    			aChar.setLevel(rs.getInt("level"));
    			aChar.setName(rs.getString("name"));
    			aChar.setClassName(rs.getString("className"));
    			aChar.setStrength(rs.getInt("strength"));
    			aChar.setAgility(rs.getInt("agility"));
    			aChar.setStamina(rs.getInt("stamina"));
    			aChar.setIntelligence(rs.getInt("intelligence"));
    			aChar.setPersonality(rs.getInt("personality"));
    			aChar.setLuck(rs.getInt("luck"));
    			aChar.setHp(rs.getInt("hp"));
    			aChar.setAc(rs.getInt("ac"));
    			theList.add(aChar);
    		}
    		
    		stmt.close();
    		rs.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return theList;
    	
    }

    public void updateWorkList(CharacterFlatDB guy, String workList) {
    	
    	try {
    		Statement stmt = conn.createStatement();
    		
    		String query = "insert into " + workList;
    		query += " (level, name, classname, strength, agility, stamina, intelligence, personality, luck, hp, ac) values (";
        	query += guy.getLevel().toString() + ", ";
        	query += "'" + guy.getName() + "', ";
        	query += "'" + guy.getClassName() + "', ";
        	query += guy.getStrength().toString() + ", ";
        	query += guy.getAgility().toString() + ", ";
        	query += guy.getStamina().toString() + ", ";
        	query += guy.getIntelligence().toString() + ", ";
        	query += guy.getPersonality().toString() + ", ";
        	query += guy.getLuck().toString() + ", ";
        	query += guy.getHp().toString() + ", ";
        	query += guy.getAc().toString() + ")";
        	
    		stmt.executeUpdate(query);
    		stmt.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    public ZeroLevelOccupation getZeroLevelOccupation() {
    	
    	ZeroLevelOccupation zLvl = new ZeroLevelOccupation();
    	try {
    		Statement stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery("select name, weapon, equipment, quantity, unit from ZeroLevel where id = " + Dice.d100().toString()); 
    		while (rs.next()) {
    			zLvl.setName(rs.getString("name"));
    			zLvl.setWeapon(rs.getString("weapon"));
    			zLvl.setEquipment(rs.getString("equipment"));
    			zLvl.setQuantity(rs.getInt("quantity"));
    			zLvl.setUnit(rs.getString("unit"));
    		}
    		stmt.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	
    	return zLvl;   	
    }
    
    private void deleteFromWorkList(Integer id, String tableName) {
    	try {
    		Statement stmt = conn.createStatement();
    		stmt.executeUpdate("delete from " + tableName + " where id = " + id.toString());
    		stmt.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    private void clearList(String tableName) {
    	try {
    		Statement stmt = conn.createStatement();
    		stmt.executeUpdate("delete from " + tableName);
    		stmt.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    private CharacterFlatDB makeFlat(Character guy) {
    	CharacterFlatDB flatGuy = new CharacterFlatDB();
    	
    	flatGuy.setAc(guy.getCombatStats().getMaxAC());
    	flatGuy.setAgility(guy.getStats().getAgility());
    	flatGuy.setClassName(guy.getClassName());
    	flatGuy.setHp(guy.getCombatStats().getMaxHP());
    	flatGuy.setIntelligence(guy.getStats().getIntelligence());
    	flatGuy.setLevel(guy.getLevel());
    	flatGuy.setLuck(guy.getStats().getLuck());
    	flatGuy.setName(guy.getName());
    	flatGuy.setPersonality(guy.getStats().getPersonality());
    	flatGuy.setStamina(guy.getStats().getStamina());
    	flatGuy.setStrength(guy.getStats().getStrength());
    	
    	return flatGuy;
    }

}
