package application;

import javafx.application.Application;  
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class MainAccount extends Application{

	public static Stage stage;
	
	public Parent root;
	
	public void start(Stage primaryStage) {
		
		//Session session = new Session();
		try {
			//if(! session.currentUser.isPassAuthenticated()) {
				root = FXMLLoader.load(getClass().getResource("view/resourses/CreateAccount.fxml"));
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.show();
			//}
			
			//FXMLLoader loader = new FXMLLoader();
			//loader.setLocation(MainAccount.class.getResource("..view/resourses/CreateAccount.fxml"));
			//Parent root = FXMLLoader.load(getClass().getResource("..resourses/CreateAccount.fxml"));
			//Parent root = loader.load();
			//Scene scene = new Scene(root);
			//primaryStage.setScene(scene);
			//primaryStage.show();
		}catch(Exception e) {
			System.out.printf("The resource 'CreateAccount.fxml' could not be located");
		}
		MainAccount.stage = primaryStage;
	}
	public static void main(String[] args) {
		launch(args);
	}
}
