package org.barrypress.wizdcc.screens;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Window;

import org.barrypress.wizdcc.util.WizDB;

public class MainScreen implements Application {
	
    @BXML private Window window = null;
    private static MainScreen instance = null;
    
    private ManageCharacters manageCharacters = new ManageCharacters();
    private ManageParty manageParty = new ManageParty();
    private SelectCharacter selectCharacter = new SelectCharacter();
    private MainMenu menuDialog = new MainMenu();
    private AddCharacter addCharacter = new AddCharacter();
    
    private WizDB wizDB;
    
	public MainScreen() {
        instance = this;
    }
	
    @Override
    public void startup(Display display, Map<String, String> properties) throws Exception {
    	
    	BXMLSerializer bxmlSerializer = new BXMLSerializer();
        bxmlSerializer.getNamespace().put("WizarDCC", this); 
        
        wizDB = new WizDB();
        wizDB.clearCharacterWorkList();
        wizDB.clearPartyWorkList();
        populateData();
        
        window = (Window) bxmlSerializer.readObject(MainScreen.class, "main_screen.bxml"); 
        menuDialog.create();
        manageCharacters.create();
        manageParty.create();
        addCharacter.create();
        selectCharacter.create();
                        
        window.setTitle("WizarDCC");
        window.open(display);
        window.requestFocus();       
        menuDialog.open();

    }
    
    @Override
    public boolean shutdown(boolean optional) {
        if (window != null) {
            window.close();
        }

        return false;
    }
	
    @Override
    public void suspend() {}

    @Override
    public void resume() {}
    
    public static MainScreen getInstance() { return instance; }
    public Window getWindow() { return this.window; }
    public MainMenu getMainMenu() { return this.menuDialog; }
    public ManageCharacters getCharManager() { return this.manageCharacters; }
    public ManageParty getPartyManager() { return this.manageParty; }
    public AddCharacter getCreateCharacter() { return this.addCharacter; }
    public SelectCharacter getSelectCharacter() { return this.selectCharacter; }
    public WizDB getWizDB() { return this.wizDB; }
    
    public static void main(String[] args) { DesktopApplicationContext.main(MainScreen.class, args); }
    
    private void populateData() {
    	
    }



}
