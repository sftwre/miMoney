package application.controller;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class TutorialController {

    @FXML
    private Button nextSceneButton;

    @FXML
    void nextScene(ActionEvent event) {
    	Parent tutor;
    	try {
    		tutor = FXMLLoader.load(getClass().getClassLoader().getResource("application.view.resources/Tutorial2.0.fxml"));
    	}catch(IOException e) {
    		e.printStackTrace();
    	}

    }
    @FXML
    void getStarted(ActionEvent event) {

    }

}