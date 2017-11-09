package application.view;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Login extends Application{

	@Override
	public void start(Stage primaryStage) {
		
		try {
			Parent root =  FXMLLoader.load(getClass().getResource("Login.fxml"));
			
			Scene scene =  new Scene(root);
			
			primaryStage.setScene(scene);
	
			primaryStage.show();
			
		} catch(Exception e ){
			
			e.printStackTrace();
		}
	}
	
	
	

}
