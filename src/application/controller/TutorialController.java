package application.controller;
//import java.awt.event.ActionEvent;
import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class TutorialController {

	String scene = "Tutorial";
	int sceneCnt = 1;
    @FXML
    private Button nextSceneButton;
    @FXML
    private Button getStartedButton;
    
    /*public void initialize() {
    	nextSceneButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    			
    				nextScene(new ActionEvent());
    		}
    	});
    	
    	getStartedButton.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent e) {
    			getStarted(new ActionEvent());
    		}
    	});
    	
    }*/

    @FXML
    void nextScene(ActionEvent event) {
    	
    		try {
    			Parent tutor;
    			sceneCnt++;
	    		
    			tutor = FXMLLoader.load(getClass().getClassLoader().getResource("view/resouces/"+scene+""+sceneCnt+".fxml"));
    			System.out.println("view/resouces/" + scene + "" + sceneCnt +".fxml");
    			//tutor = FXMLLoader.load(getClass().getClassLoader().getResource("view/resouces/Tutorial2.fxml"));
	    		
	    		
	 
    			Main.stage.setScene(new Scene(tutor));
    			Main.stage.show();
	    		//Math.ceil();
    		}catch(IOException x) {
    			x.printStackTrace();
    			System.err.println("Tutorial page not found!");
    		}
		
    }
    	
  
    
    @FXML
    void getStarted(ActionEvent event) {
    	
    	Stage create =  new Stage();
    	
    	//create.initModality(Modality.APPLICATION_MODAL);
    	//create.initOwner(Main.stage);
    	try
		{
    		Parent root = FXMLLoader.load(getClass().getResource("../view/resources/MainView.fxml"));
    		Scene scene = new Scene(root);
    		create.setScene(scene);
    		create.show();
		} catch(IOException e){
			System.out.printf("The resource 'view/resources/MainView.fxml' could not be located");
		}// END try/catch load FXML
    }
    	
    	
    	

 }

