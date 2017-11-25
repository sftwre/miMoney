/*package application;
 
 import javafx.application.Application;
 import javafx.fxml.FXMLLoader;
 import javafx.scene.Parent;
 import javafx.scene.Scene;
 import javafx.stage.Stage;
 
 public class MainTutorial extends Application{
 
 	public static Stage stage;
 	
 	public void start(Stage primaryStage) {
 		try {
 
 			
 			Parent root = FXMLLoader.load(getClass().getResource("view/resources/Tutorial4.fxml"));
 			Scene scene = new Scene(root);
 		
 			primaryStage.setScene(scene);
 			primaryStage.show();
 		}catch(Exception e) {
 			e.printStackTrace();
 		}
 	}
 	
 	public static void main(String[] args) {
 		launch(args);
 
 	}
 
 }*/

package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainTutorial extends Application{

	public static Stage stage;
	
	public static Scene currentScene;
	
	public void start(Stage primaryStage) {

		try {

			Parent root = FXMLLoader.load(getClass().getResource("view/resources/Tutorial.fxml"));
			currentScene = new Scene(root, 500, 500);
		
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
	
	public static void setScene(Scene scene)
	{
		currentScene = scene;
		stage.setScene(currentScene);
		
	}

}


