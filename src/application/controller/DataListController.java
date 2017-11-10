/**
 * 
 */
package application.controller;

/**
 * non-final DataList implementation. Should be a catch-all
 * for anything that needs to be displayed in list format with
 * (String) + (Double) pairs.
 * 
 * e.g. ItemName \t $20.15
 * @author Jonathan Remote
 *
 */
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class DataListController {

    //Create variables/fields
    static boolean answer;
    
    @FXML
    private Button addButton;
    
    @FXML
    private TextField tField;
    
    @FXML
    private Label itemLabel;
    
    @FXML
    private Label doubleLabel;
    
    @FXML
    private HBox hBox;
    
    @FXML
    private VBox vBox;

    /**
     * 
     * @param title
     * 				title is the name of the DataList window (e.g. Add Expense, Add Recurring Expense)
     * @param message
     * 				message is the prompt text of the TextField at bottom (e.g. add expense... | add recurring expense...)
     */
    
    public void display(String title, String message) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("application.view.resources/DatalistView.fxml"));			
		} catch(Exception e) {
			e.printStackTrace();
		}
    	    	
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        tField = new TextField();
        tField.setPromptText(message);

        //Clicking will set answer and close window
        addButton.setOnAction(e -> {            
            //TODO: vbox.add hBox.contains(item + total) pseudocode remove window.close()
            window.close();
        });

        return;
    }

}