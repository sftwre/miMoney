
package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainTutorial extends Application{

	public  Stage stage;
	
	public Scene currentScene;
	
	public void start(Stage primaryStage) {

		try {

			Parent root = FXMLLoader.load(getClass().getResource("view/resources/Tutorial.fxml"));
			currentScene = new Scene(root);
		
			primaryStage.setScene(currentScene);
			primaryStage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		stage = primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);

	}

}


