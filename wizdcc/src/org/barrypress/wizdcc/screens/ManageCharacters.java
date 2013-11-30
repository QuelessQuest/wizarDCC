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

public class ManageCharacters implements BarryDialog { 
	
    @BXML private Window window;
    @BXML private Sheet  charSheet;  
    @BXML private PushButton addChar;
    @BXML private PushButton backButton;
    @BXML private PushButton delChar;
    @BXML private TableView  tableView;
    
    private static final ArrayList<String> options = new ArrayList<String>("Cancel", "YES");

    public ManageCharacters() {}
	
    public void create() throws Exception {

        window = MainScreen.getInstance().getWindow();
		
        BXMLSerializer bxmlSheet = new BXMLSerializer();
        bxmlSheet.getNamespace().put("WizarDCC", this); 
        this.charSheet = (Sheet) bxmlSheet.readObject(ManageCharacters.class, "manage_characters.bxml");

        addChar    = (PushButton) bxmlSheet.getNamespace().get("addChar");
        backButton = (PushButton) bxmlSheet.getNamespace().get("backButton");
        delChar    = (PushButton) bxmlSheet.getNamespace().get("delChar");
        tableView  = (TableView)  bxmlSheet.getNamespace().get("tableView");
        
        delChar.setEnabled(false);
        
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
            	Prompt prompt = new Prompt(MessageType.QUESTION, "Delete selected character?", options);
                prompt.getStyles().put("backgroundColor", "#FF0000");
                prompt.open(window, new SheetCloseListener() {
                    @Override
                    public void sheetClosed(Sheet sheet) {
                        // Process an OK click. Any other option will simply close the sheet
                        if (sheet.getResult() && ((Prompt) sheet).getSelectedOptionIndex() == 1) {
                            sheet.close();
                            List tableData = tableView.getTableData();
                            tableData.remove(tableView.getSelectedRow());
                            tableView.setTableData(tableData);
                        }
                    }
                });    	
            }
        });
        
        tableView.getTableViewSelectionListeners().add(new TableViewSelectionListener() {
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
    	tableView.setTableData(MainScreen.getInstance().getWizDB().getCharacterWorkList());
    }

}
