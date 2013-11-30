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
    		//TODO
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
    		//TODO
    	}
    	
    	return cList;
    }
    
    public int getClassId(String className) {
    	int rc = 0;
    	try {
    		Statement stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT id from class where name='" + className + "'");
    		rc = rs.getInt("id");
    		stmt.close();
    		rs.close();
    	} catch (Exception e) {
    		//TODO
    	}
    	return rc;
    }
    
    public List<CharacterFlatDB> getCharacterWorkList() {
    	List<CharacterFlatDB> theList = new ArrayList<CharacterFlatDB>();
    	
    	try {
    		Statement stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery("select id, level, name, className, strength, dexterity, constitution, intelligence, wisdom, luck, hp, ac from ClassWorkList");
    		
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
    		//TODO
    		e.printStackTrace();
    	}
    	
    	return theList;
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
        	System.out.println(query);
        	
    		stmt.executeUpdate(query);
    		stmt.close();
    	} catch (Exception e) {
    		System.out.println("EXCEPTION: " + e.getMessage());
    		e.printStackTrace();
    	}
    }
    
    public void deleteFromCharacterWorkList(Integer id) {
    	try {
    		Statement stmt = conn.createStatement();
    		stmt.executeUpdate("delete from ClassWorkList where id = " + id.toString());
    		stmt.close();
    	} catch (Exception e) {
    		//TODO
    	}
    }
    
    public void clearCharacterWorkList() {
    	try {
    		Statement stmt = conn.createStatement();
    		stmt.executeUpdate("delete from ClassWorkList");
    		stmt.close();
    	} catch (Exception e) {
    		//TODO
    	}
    }

}
