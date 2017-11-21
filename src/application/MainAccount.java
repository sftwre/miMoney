package application;

import java.io.IOException;

import javafx.application.Application;  
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class MainAccount extends Application{

	public static Stage stage;
	
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("> CreateAccount.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch(Exception e) {
			System.err.printf("The resource 'view/resources/CreateAccount.fxml' could not be located");
		}
		MainAccount.stage = primaryStage;
	}
	public static void main(String[] args) {
		launch(args);
	}
}
