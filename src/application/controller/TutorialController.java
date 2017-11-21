package application.controller;
//import java.awt.event.ActionEvent;
import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class TutorialController {

	String scene = "Tutorial";
	int sceneCnt = 0;
    @FXML
    private Button nextSceneButton;

    @FXML
    void nextScene(ActionEvent event) {
   
    	try {
    		Parent tutor;
    		sceneCnt++;
    		
    		tutor = FXMLLoader.load(getClass().getClassLoader().getResource("/" + scene + "" + sceneCnt +".fxml"));
    		
    		
 
    		Main.stage.setScene(new Scene(tutor, 500, 575));
    		Main.stage.show();
    		//Math.ceil();
    	}catch(IOException e) {
    		e.printStackTrace();
    		System.err.println("Tutorial page not found!");
    	}

    }
    @FXML
    void getStarted(ActionEvent event) {
    	Stage create =  new Stage();
    	
    	create.initModality(Modality.APPLICATION_MODAL);
    	create.initOwner(Main.stage);
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