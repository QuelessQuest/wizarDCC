package org.barrypress.wizdcc.screens;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.ListButtonSelectionListener;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.Sheet;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.Window;

import org.barrypress.wizdcc.pc.Character;

public class AddCharacter implements BarryDialog { 
	
    @BXML private Window window;
    @BXML private Sheet  charSheet;  
    @BXML private Label sStr;
    @BXML private Label sDex;
    @BXML private Label sCon;
    @BXML private Label sInt;
    @BXML private Label sWis;
    @BXML private Label sLuc;
    @BXML private ListButton classList;
    @BXML private PushButton cancelButton;
    @BXML private PushButton reroll;
    @BXML private PushButton saveChar;
    @BXML private TextInput cName;
    
    private Character guy;

    public AddCharacter() {}
	
    public void create() throws Exception {

        window = MainScreen.getInstance().getWindow();
		
        BXMLSerializer bxmlSheet = new BXMLSerializer();
        bxmlSheet.getNamespace().put("WizarDCC", this); 
        this.charSheet = (Sheet) bxmlSheet.readObject(AddCharacter.class, "add_character.bxml");
        
        sStr         = (Label)      bxmlSheet.getNamespace().get("sStr");
        sDex         = (Label)      bxmlSheet.getNamespace().get("sDex");
        sCon         = (Label)      bxmlSheet.getNamespace().get("sCon");
        sInt         = (Label)      bxmlSheet.getNamespace().get("sInt");
        sWis         = (Label)      bxmlSheet.getNamespace().get("sWis");
        sLuc         = (Label)      bxmlSheet.getNamespace().get("sLuc");
        classList    = (ListButton) bxmlSheet.getNamespace().get("classList");
        cancelButton = (PushButton) bxmlSheet.getNamespace().get("cancelButton");
        reroll       = (PushButton) bxmlSheet.getNamespace().get("reroll");
        saveChar     = (PushButton) bxmlSheet.getNamespace().get("saveChar");
        cName        = (TextInput)  bxmlSheet.getNamespace().get("cName");
        
        classList.setListData(MainScreen.getInstance().getWizDB().getClassList());
        
        cancelButton.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
            	charSheet.close();
            	MainScreen.getInstance().getCharManager().open();
            }
        });
        
        reroll.getButtonPressListeners().add(new ButtonPressListener() {
        	@Override
        	public void buttonPressed(Button button) {
        		guy.reRoll();
        		rePaint();
        	}
        });
        
        saveChar.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
            	if (saveCharacter()) {
            		charSheet.close();
            		MainScreen.getInstance().getCharManager().open();
            	}
            }
        });
        
        classList.getListButtonSelectionListeners().add(new ListButtonSelectionListener() {
            @Override
            public void selectedItemChanged(ListButton listButton, Object previousSelectedItem) {
            	guy.setClassName((String) classList.getSelectedItem());
            	guy.setClassId(MainScreen.getInstance().getWizDB().getClassId(guy.getClassName()));
            }

            @Override
            public void selectedIndexChanged(ListButton listButton, int previousSelectedIndex) {}
        });
    }
	
    public void open() {
    	guy = new Character();
    	charSheet.open(window);
    	rePaint();
    }
	
    public Sheet getCharSheet() {
        return this.charSheet;
    }
	
    public void close() {
    	guy = null;
    	charSheet.close();
    }
	
    public void clear() {}
    
    private Boolean saveCharacter() {
    	Boolean saved = false;
    	if (validateCharacter()) {
    		MainScreen.getInstance().getWizDB().updateCharacterWorkList(guy);
    		saved = true;
    	}
    	return saved;
    }
    
    private Boolean validateCharacter() {
    	
    	String msg = "";
    	Boolean valid = true;
    	
    	guy.setName(cName.getText());
    	if (guy.getName().isEmpty()) {
    		msg += "Character Name must be provided! ";
    	}
    	if (guy.getClassName().isEmpty()) {
    		msg += "A Character Class must be selected! ";
    	}
    	if (!msg.isEmpty()) {
    		Prompt.prompt(MessageType.WARNING, msg, window);
    		valid = false;
    	}

    	return valid;
    }
    
    private void rePaint() {
    	sStr.setText(guy.getStats().getStrength().toString());
    	sDex.setText(guy.getStats().getDexterity().toString());
    	sCon.setText(guy.getStats().getConstitution().toString());
    	sInt.setText(guy.getStats().getIntelligence().toString());
    	sWis.setText(guy.getStats().getWisdom().toString());
    	sLuc.setText(guy.getStats().getLuck().toString());
    	classList.clearSelection();
    	cName.setText("");
    }

}
