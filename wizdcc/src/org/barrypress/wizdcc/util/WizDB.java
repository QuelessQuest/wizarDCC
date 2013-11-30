package org.barrypress.wizdcc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

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
    	try {
    		Statement stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT id from class where name='" + className + "'");
    		return rs.getInt("id");
    	} catch (Exception e) {
    		//TODO
    	}
    	return 0;
    }

}
