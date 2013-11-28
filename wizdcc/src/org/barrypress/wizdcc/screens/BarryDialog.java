package org.barrypress.wizdcc.screens;

/**
 * Interface for all user interface dialogs.
 * 
 */
public interface BarryDialog {
	
	public void create() throws Exception; 
	public void open();
	public void close();
	public void clear();

}
