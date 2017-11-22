package application;

import javafx.application.Application;  
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class MainAccount extends Application{

	public static Stage stage;
	
	
	public void start(Stage primaryStage) {
		
		try {

			
				Parent root = FXMLLoader.load(getClass().getResource("view/resources/CreateAccount2.fxml"));
				Scene scene = new Scene(root, 500, 500);
			
				primaryStage.setScene(scene);
				primaryStage.show();
			
			
			

		}catch(Exception e) {


			e.printStackTrace();

			System.err.printf("The resource 'view/resources/CreateAccount.fxml' could not be located");


			System.out.printf("The resource 'CreateAccount.fxml' could not be located");

		}
		MainAccount.stage = primaryStage;
	}
	public static void main(String[] args) {
		launch(args);
	}
}
