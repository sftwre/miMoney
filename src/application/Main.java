package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


/**
 *
 */
public class Main extends Application {
	
			
		public static Stage stage;		// stage for displaying scenes in the application
		
		Scene currScene;
		// the current scene
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("view/resources/MainView.fxml"));
			
			Scene scene = new Scene(root);
			
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			
			this.currScene = scene;
			
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		stage = primaryStage;
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public Scene getScene()
	{
		return (this.currScene);
	}
	
	public void setScene(Scene scene)
	{
		this.currScene = scene;
	}
	
}
