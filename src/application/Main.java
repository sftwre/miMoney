package application;
	

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.chart.*;


public class Main extends Application {
	
	// Create the pie chart
		public static ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
						
					new PieChart.Data("Housing", 700),
					new PieChart.Data("AutoInsurance", 150),
					new PieChart.Data("AutoPayment",200), 
					new PieChart.Data("Food", 36.75 ),
					new PieChart.Data("Gas", 68.36));
		
		Stage stage;
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("view/MainView.fxml"));
			Scene scene = new Scene(root,500,500);
			
			
			primaryStage.setScene(scene);
			
			primaryStage.setTitle("miMoney");
			
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
