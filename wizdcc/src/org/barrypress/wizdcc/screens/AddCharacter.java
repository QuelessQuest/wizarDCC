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
    @BXML private Label sAgi;
    @BXML private Label sSta;
    @BXML private Label sInt;
    @BXML private Label sPer;
    @BXML private Label sLuc;
    @BXML private Label mStr;
    @BXML private Label mAgi;
    @BXML private Label mSta;
    @BXML private Label mInt;
    @BXML private Label mPer;
    @BXML private Label mLuc;
    @BXML private Label sHP;
    @BXML private Label sAC;
    @BXML private Label className;
    @BXML private ListButton alignment;
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
        sAgi         = (Label)      bxmlSheet.getNamespace().get("sAgi");
        sSta         = (Label)      bxmlSheet.getNamespace().get("sSta");
        sInt         = (Label)      bxmlSheet.getNamespace().get("sInt");
        sPer         = (Label)      bxmlSheet.getNamespace().get("sPer");
        sLuc         = (Label)      bxmlSheet.getNamespace().get("sLuc");
        mStr         = (Label)      bxmlSheet.getNamespace().get("mStr");
        mAgi         = (Label)      bxmlSheet.getNamespace().get("mAgi");
        mSta         = (Label)      bxmlSheet.getNamespace().get("mSta");
        mInt         = (Label)      bxmlSheet.getNamespace().get("mInt");
        mPer         = (Label)      bxmlSheet.getNamespace().get("mPer");
        mLuc         = (Label)      bxmlSheet.getNamespace().get("mLuc");
        sHP          = (Label)      bxmlSheet.getNamespace().get("sHP");
        sAC          = (Label)      bxmlSheet.getNamespace().get("sAC");
        className    = (Label)      bxmlSheet.getNamespace().get("className");
        alignment    = (ListButton) bxmlSheet.getNamespace().get("alignment");
        cancelButton = (PushButton) bxmlSheet.getNamespace().get("cancelButton");
        reroll       = (PushButton) bxmlSheet.getNamespace().get("reroll");
        saveChar     = (PushButton) bxmlSheet.getNamespace().get("saveChar");
        cName        = (TextInput)  bxmlSheet.getNamespace().get("cName");
        
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
        
        alignment.getListButtonSelectionListeners().add(new ListButtonSelectionListener() {
            @Override
            public void selectedIndexChanged(ListButton listButton, int previousSelectedIndex) {
            	guy.setAlignment((String) alignment.getSelectedItem());
            }
            @Override
            public void selectedItemChanged(ListButton listButton, Object previousSelectedItem) {}
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
    	if (!MainScreen.getInstance().getWizDB().validateName(guy.getName())) {
    		msg += "Character Name must be unique! ";
    	}
    	if (guy.getAlignment().isEmpty()) {
    		msg += "Alignment must be provided! ";
    	}
    	if (!msg.isEmpty()) {
    		Prompt.prompt(MessageType.WARNING, msg, window);
    		valid = false;
    	}

    	return valid;
    }
    
    private void rePaint() {
    	sStr.setText(guy.getStats().getStrength().toString());
    	sAgi.setText(guy.getStats().getAgility().toString());
    	sSta.setText(guy.getStats().getStamina().toString());
    	sInt.setText(guy.getStats().getIntelligence().toString());
    	sPer.setText(guy.getStats().getPersonality().toString());
    	sLuc.setText(guy.getStats().getLuck().toString());
    	mStr.setText(guy.getStats().getAdjStrength().toString());
    	mAgi.setText(guy.getStats().getAdjAgility().toString());
    	mSta.setText(guy.getStats().getAdjStamina().toString());
    	mInt.setText(guy.getStats().getAdjIntelligence().toString());
    	mPer.setText(guy.getStats().getAdjPersonality().toString());
    	mLuc.setText(guy.getStats().getAdjLuck().toString());
    	sHP.setText(guy.getCombatStats().getMaxHP().toString());
    	sAC.setText(guy.getCombatStats().getMaxAC().toString());
    	alignment.clear();
    	className.setText(guy.getZeroLevelOccupation().getName());
    	cName.setText("");
    }

}
