package application;

import javafx.application.Application;  
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class CalculatorMain extends Application{

	public static Stage stage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
		
			Parent root = FXMLLoader.load(getClass().getResource("/CalcView.fxml"));
			primaryStage.setScene(new Scene(root, 400, 400));
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		CalculatorMain.stage = primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
