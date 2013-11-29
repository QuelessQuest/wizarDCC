package org.barrypress.wizdcc.util;

import java.sql.Connection;
import java.sql.DriverManager;

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

}
