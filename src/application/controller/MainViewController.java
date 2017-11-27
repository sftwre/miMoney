package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

/**
 * Controls the actions occurring across Tabs in the MainView.fxml
 * @author Isaac Buitrago
 *
 */
public class MainViewController 
{
	
	@FXML
	public static TabPane tabPane;		// Tab Pane that contains all tabs
	
	
	/**
	 * Used to switch the currently selected tab in the MainView.fxml
	 * @param index of tabs to select
	 */
	public void switchTabs(int index)
	{
		
		tabPane.getSelectionModel().select(index);
	}

}
