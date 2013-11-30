package org.barrypress.wizdcc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

import org.barrypress.wizdcc.pc.Character;

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
    	
    	try {
    		Statement stmt = conn.createStatement();
    		Integer maxId = 0;
    		ResultSet rs = stmt.executeQuery("select max(id) as maxid from ClassWorkList");
    		while (rs.next()) {
    			maxId = rs.getInt("maxid") + 1;
    		}
    		
    		String query = "insert into ClassWorkList values (";
    		query += maxId.toString() + ", ";
        	query += guy.getLevel().toString() + ", ";
        	query += "'" + guy.getName() + "', ";
        	query += "'" + guy.getClassName() + "', ";
        	query += guy.getStats().getStrength().toString() + ", ";
        	query += guy.getStats().getDexterity().toString() + ", ";
        	query += guy.getStats().getConstitution().toString() + ", ";
        	query += guy.getStats().getIntelligence().toString() + ", ";
        	query += guy.getStats().getWisdom().toString() + ", ";
        	query += guy.getStats().getLuck().toString() + ", ";
        	query += guy.getCombatStats().getMaxHP().toString() + ", ";
        	query += guy.getCombatStats().getMaxAC().toString() + ")";
        	
    		stmt.executeUpdate(query);
    		stmt.close();
    	} catch (Exception e) {
    		System.out.println("EXCEPTION: " + e.getMessage());
    		e.printStackTrace();
    	}
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
    	
    	try {
    		Statement stmt = conn.createStatement();
    		Integer maxId = 0;
    		ResultSet rs = stmt.executeQuery("select max(id) as maxid from PartyWorkList");
    		while (rs.next()) {
    			maxId = rs.getInt("maxid") + 1;
    		}
    		
    		String query = "insert into PartyWorkList values (";
    		query += maxId.toString() + ", ";
        	query += guy.getLevel().toString() + ", ";
        	query += "'" + guy.getName() + "', ";
        	query += "'" + guy.getClassName() + "', ";
        	query += guy.getStrength().toString() + ", ";
        	query += guy.getDexterity().toString() + ", ";
        	query += guy.getConstitution().toString() + ", ";
        	query += guy.getIntelligence().toString() + ", ";
        	query += guy.getWisdom().toString() + ", ";
        	query += guy.getLuck().toString() + ", ";
        	query += guy.getHp().toString() + ", ";
        	query += guy.getAc().toString() + ")";
        	
    		stmt.executeUpdate(query);
    		stmt.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void deleteFromPartyWorkList(Integer id) {
    	deleteFromWorkList(id, "PartyWorkList");
    }
    
    public void clearPartyWorkList() {
    	clearList("PartyWorkList");
    }
    
    public List<CharacterFlatDB> getAvailableWorkList() {
    	List<CharacterFlatDB> theList = new ArrayList<CharacterFlatDB>();
		String query = "select id, level, name, className, strength, dexterity, constitution, intelligence, wisdom, luck, hp, ac from ";
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
    			aChar.setDexterity(rs.getInt("dexterity"));
    			aChar.setConstitution(rs.getInt("constitution"));
    			aChar.setIntelligence(rs.getInt("intelligence"));
    			aChar.setWisdom(rs.getInt("wisdom"));
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
    		ResultSet rs = stmt.executeQuery("select id, level, name, className, strength, dexterity, constitution, intelligence, wisdom, luck, hp, ac from " + tableName);
    		
    		while (rs.next()) {
    			CharacterFlatDB aChar = new CharacterFlatDB();
    			aChar.setId(rs.getInt("id"));
    			aChar.setLevel(rs.getInt("level"));
    			aChar.setName(rs.getString("name"));
    			aChar.setClassName(rs.getString("className"));
    			aChar.setStrength(rs.getInt("strength"));
    			aChar.setDexterity(rs.getInt("dexterity"));
    			aChar.setConstitution(rs.getInt("constitution"));
    			aChar.setIntelligence(rs.getInt("intelligence"));
    			aChar.setWisdom(rs.getInt("wisdom"));
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

}
