package org.barrypress.wizdcc.screens;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.Sheet;
import org.apache.pivot.wtk.SheetCloseListener;
import org.apache.pivot.wtk.Span;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TableViewSelectionListener;
import org.apache.pivot.wtk.Window;

import org.barrypress.wizdcc.util.CharacterFlatDB;

public class ManageParty implements BarryDialog { 
	
    @BXML private Window window;
    @BXML private Sheet  partySheet;  
    @BXML private PushButton addChar;
    @BXML private PushButton backButton;
    @BXML private PushButton delChar;
    @BXML private TableView  partyView;
    
    private static final ArrayList<String> options = new ArrayList<String>("Cancel", "YES");

    public ManageParty() {}
	
    public void create() throws Exception {

        window = MainScreen.getInstance().getWindow();
		
        BXMLSerializer bxmlSheet = new BXMLSerializer();
        bxmlSheet.getNamespace().put("WizarDCC", this); 
        this.partySheet = (Sheet) bxmlSheet.readObject(ManageParty.class, "manage_party.bxml");

        addChar    = (PushButton) bxmlSheet.getNamespace().get("addChar");
        backButton = (PushButton) bxmlSheet.getNamespace().get("backButton");
        delChar    = (PushButton) bxmlSheet.getNamespace().get("delChar");
        partyView  = (TableView)  bxmlSheet.getNamespace().get("partyView");
        
        delChar.setEnabled(false);
        
        backButton.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
            	partySheet.close();
            	MainScreen.getInstance().getMainMenu().open();
            }
        });
        
        addChar.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
            	partySheet.close();
            	MainScreen.getInstance().getSelectCharacter().open();
            }
        });
        
        delChar.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
            	Prompt prompt = new Prompt(MessageType.QUESTION, "Delete selected character?", options);
                prompt.getStyles().put("backgroundColor", "#FF0000");
                prompt.open(window, new SheetCloseListener() {
                    @SuppressWarnings("unchecked")
                    @Override
                    public void sheetClosed(Sheet sheet) {
                        // Process an OK click. Any other option will simply close the sheet
                        if (sheet.getResult() && ((Prompt) sheet).getSelectedOptionIndex() == 1) {
                            sheet.close();
                            List<CharacterFlatDB> tableData = (List<CharacterFlatDB>) partyView.getTableData();
                            CharacterFlatDB theRow = (CharacterFlatDB) partyView.getSelectedRow();
                            MainScreen.getInstance().getWizDB().deleteFromPartyWorkList(theRow.getId());
                            tableData.remove(theRow);
                            if (tableData.getLength() < 6) {
                            	addChar.setEnabled(true);
                            }
                            partyView.setTableData(tableData);
                        }
                    }
                });    	
            }
        });
        
        partyView.getTableViewSelectionListeners().add(new TableViewSelectionListener() {
        	@Override
        	public void selectedRangeAdded(TableView tv, int rangeStart, int rangeEnd) {}
        	@Override
        	public void selectedRangeRemoved(TableView tv, int rangeStart, int rangeEnd) {}
        	@Override
        	public void selectedRangesChanged(TableView tv, Sequence<Span> previousSelectedRanges) {}
        	@Override
        	public void selectedRowChanged(TableView tv, Object previousSelectedRow) {
        		if (tv.getSelectedIndex() >= 0) {
        			delChar.setEnabled(true);
        		} else {
        			delChar.setEnabled(false);
        		}
        	}
        });
    }
	
    public void open() {
    	loadParty();
    	partySheet.open(window);
    	if (partyView.getTableData().getLength() >= 6) {
    		addChar.setEnabled(false);
    	}
    }
	
    public Sheet getPartySheet() {
        return this.partySheet;
    }
	
    public void close() {
    	partySheet.close();
    }
	
    public void clear() {}
    
    private void loadParty() {
    	partyView.setTableData(MainScreen.getInstance().getWizDB().getPartyWorkList());
    }

}
