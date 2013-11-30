package org.barrypress.wizdcc.screens;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.Sheet;
import org.apache.pivot.wtk.Window;

public class MainMenu implements BarryDialog { 
	
    @BXML private Window window;
    @BXML private Sheet  menuSheet;  
    
    @BXML private PushButton characterButton = null;
    @BXML private PushButton partyButton     = null;

    public MainMenu() {}
	
    public void create() throws Exception {

        window = MainScreen.getInstance().getWindow();
        
        BXMLSerializer bxmlSheet = new BXMLSerializer();
        bxmlSheet.getNamespace().put("WizarDCC", this); 
        this.menuSheet = (Sheet) bxmlSheet.readObject(MainMenu.class, "main_menu.bxml");
        
        characterButton = (PushButton) bxmlSheet.getNamespace().get("characterButton");
        partyButton     = (PushButton) bxmlSheet.getNamespace().get("partyButton");
        
        characterButton.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
            	menuSheet.close();
                MainScreen.getInstance().getCharManager().open();
            }
        });
        
        partyButton.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
            	menuSheet.close();
                MainScreen.getInstance().getPartyManager().open();
            }
        });

        
    }
	
    public void open() {
        menuSheet.open(window);
    }
	
    public Sheet getMenuSheet() {
        return this.menuSheet;
    }
	
    public void close() {
        menuSheet.close();
    }
	
    public void clear() {}
	
}
