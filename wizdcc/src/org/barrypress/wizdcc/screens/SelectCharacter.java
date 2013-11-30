package org.barrypress.wizdcc.screens;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.Sheet;
import org.apache.pivot.wtk.Span;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TableViewSelectionListener;
import org.apache.pivot.wtk.Window;

import org.barrypress.wizdcc.util.CharacterFlatDB;

public class SelectCharacter implements BarryDialog { 
	
    @BXML private Window window;
    @BXML private Sheet  charSheet;  
    @BXML private PushButton addChar;
    @BXML private PushButton cancelButton;
    @BXML private TableView  cView;

    public SelectCharacter() {}
	
    public void create() throws Exception {

        window = MainScreen.getInstance().getWindow();
		
        BXMLSerializer bxmlSheet = new BXMLSerializer();
        bxmlSheet.getNamespace().put("WizarDCC", this); 
        this.charSheet = (Sheet) bxmlSheet.readObject(SelectCharacter.class, "select_character.bxml");

        addChar      = (PushButton) bxmlSheet.getNamespace().get("addChar");
        cancelButton = (PushButton) bxmlSheet.getNamespace().get("cancelButton");
        cView        = (TableView)  bxmlSheet.getNamespace().get("cView");
        
        addChar.setEnabled(false);
        
        cancelButton.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
            	charSheet.close();
            	MainScreen.getInstance().getPartyManager().open();
            }
        });
        
        addChar.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
            	CharacterFlatDB theRow = (CharacterFlatDB) cView.getSelectedRow();
            	MainScreen.getInstance().getWizDB().updatePartyWorkList(theRow);
            	charSheet.close();
            	MainScreen.getInstance().getPartyManager().open();
            }
        });
        
        cView.getTableViewSelectionListeners().add(new TableViewSelectionListener() {
        	@Override
        	public void selectedRangeAdded(TableView tv, int rangeStart, int rangeEnd) {}
        	@Override
        	public void selectedRangeRemoved(TableView tv, int rangeStart, int rangeEnd) {}
        	@Override
        	public void selectedRangesChanged(TableView tv, Sequence<Span> previousSelectedRanges) {}
        	@Override
        	public void selectedRowChanged(TableView tv, Object previousSelectedRow) {
        		if (tv.getSelectedIndex() >= 0) {
        			addChar.setEnabled(true);
        		} else {
        			addChar.setEnabled(false);
        		}
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
    	cView.setTableData(MainScreen.getInstance().getWizDB().getAvailableWorkList());
    }

}
