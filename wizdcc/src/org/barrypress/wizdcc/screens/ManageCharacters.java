package org.barrypress.wizdcc.screens;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.Sheet;
import org.apache.pivot.wtk.Window;

public class ManageCharacters implements BarryDialog { 
	
    @BXML private Window window;
    @BXML private Sheet  charSheet;  
    @BXML private PushButton addChar;
    @BXML private PushButton backButton;
    @BXML private PushButton delChar;

    public ManageCharacters() {}
	
    public void create() throws Exception {

        window = MainScreen.getInstance().getWindow();
		
        BXMLSerializer bxmlSheet = new BXMLSerializer();
        bxmlSheet.getNamespace().put("WizarDCC", this); 
        this.charSheet = (Sheet) bxmlSheet.readObject(ManageCharacters.class, "manage_characters.bxml");

        addChar    = (PushButton) bxmlSheet.getNamespace().get("addChar");
        backButton = (PushButton) bxmlSheet.getNamespace().get("backButton");
        delChar    = (PushButton) bxmlSheet.getNamespace().get("delChar");
        
        backButton.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
            	charSheet.close();
            	MainScreen.getInstance().getMainMenu().open();
            }
        });
        
        addChar.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
            	charSheet.close();
            	MainScreen.getInstance().getCreateCharacter().open();
            }
        });
        
        delChar.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
            	
            }
        });
    }
	
    public void open() {
    	loadCharacters();
    	charSheet.open(window);
    }
	
    public Sheet getCharSheet() {
        return this.charSheet;
    }
	
    public void close() {
    	charSheet.close();
    }
	
    public void clear() {}
    
    private void loadCharacters() {
    	
    }
	
}
