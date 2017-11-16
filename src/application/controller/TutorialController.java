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


public class TutorialController {

    @FXML
    private Button nextSceneButton;

    @FXML
    void nextScene(ActionEvent event) {
    	//Parent tutor;
    	try {
    		Parent tutor;
    		tutor = FXMLLoader.load(getClass().getClassLoader().getResource("/Tutorial2.0.fxml"));
    		
 
    		Main.stage.setScene(new Scene(tutor, 500, 575));
    		Main.stage.show();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}

    }
    @FXML
    void getStarted(ActionEvent event) {

    }

}