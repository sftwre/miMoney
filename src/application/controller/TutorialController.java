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
    
    public void initialize() {
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
    	
    }

    @FXML
    void nextScene(ActionEvent event) {
    	
    		try {
    			Parent tutor;
    			sceneCnt++;
	    		
    			tutor = FXMLLoader.load(getClass().getClassLoader().getResource("view/resouces/" + scene + "" + sceneCnt +".fxml"));
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
    	try {
    		Parent start;
    		start = FXMLLoader.load(getClass().getClassLoader().getResource("view/resourses/MainView.fxml"));
    		Scene scene = new Scene(start);
    		create.setScene(scene);
    		create.show();
    				//Main.stage.setScene(new Scene(start, 500, 575));
    	}catch(IOException x) {
    		x.printStackTrace();
    	}
    }
    	
    	
    	

 }

