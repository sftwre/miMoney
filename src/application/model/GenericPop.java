/**
 * 
 */
package application.model;

/**
 * non-final
 * @author Jonathan Remote
 *
 */
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;

public class GenericPop {

    //Create variables/fields
    static boolean answer;
    
    @FXML
    private Button topButton;
    
    @FXML
    private Button bottomButton;
    
    @FXML
    private TextArea tArea;

    public boolean display(String title, String message) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("application.view.resources/GenericPopUp.fxml"));			
		} catch(Exception e) {
			e.printStackTrace();
		}
    	
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        tArea = new TextArea(message);

        //Create two buttons
        topButton = new Button("okay");
        bottomButton = new Button("nah");

        //Clicking will set answer and close window
        topButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        bottomButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        //Add buttons
        //layout.getChildren().addAll(label, yesButton, noButton);
        //layout.setAlignment(Pos.CENTER);
        //Scene scene = new Scene(layout);
        //window.setScene(scene);
        //window.showAndWait();

        //Make sure to return answer
        return answer;
    }

}