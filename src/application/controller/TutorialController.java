package application.controller;
//import java.awt.event.ActionEvent;
import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Kelly Malcolm
 * @author Isaac Buitrago
 *
 */
public class TutorialController 
{

	//String scene = "../view/resources/Tutorial";
	
		int buttonClickCnt = 1;			// Counter to keep track of button clicks
	
	 	@FXML
	 	private Label captionLabel;		// Label to for displaying captions

	    @FXML
	    private Button nextSceneButton;	// Button to change the caption and Image loaded into the ImageView
	    
	    @FXML
	    private Button lastSceneButton;	// Button to revert the caption and Image loaded into the ImageView

	    @FXML
	    private Button getStartedButton;// Button to change the Scene to the MainView.fxml file

	    @FXML
	    private ImageView imageView; 	// ImageView for displaying screen shots of the application
	    
	    private Image image;			// Image to displays
    


	/**
	 * 
	 * @param event
	 */
    @FXML
    void nextScene(ActionEvent event) 
    {
   
    	//new version of Tutorial using one controller and one FXML file
    	 
    	 if(((Button)event.getSource()).getId().equals("lastSceneButton"))
    		 buttonClickCnt--;
    	 
    	 else
    		 buttonClickCnt++;
    	
    	 if(1<= buttonClickCnt && buttonClickCnt <= 5)
    	 {
    		 
    		 switch(buttonClickCnt) 
    		 {
    		 	case 1:
	    		 	captionLabel.setText("miMoney: money management the way you want it");
	    		 	lastSceneButton.setVisible(false);
			 		imageView.setImage(null);
			 	break;
			 	
    		 	case 2:
    		 		captionLabel.setText("View your Fiancial Summary on the Financial Overview tab");
    		 		lastSceneButton.setVisible(true);
    		 		image = new Image("application/view/images/financial-overview.png", 410, 230, false, true);
    		 		imageView.setImage(image);
    		 	break;
    		 	
    		 	case 3:
    		 		captionLabel.setText("Track and add expenses on the Calender tab");
    		 		image = new Image("application/view/images/images.png", 410, 230, false, true);
    		 		imageView.setImage(image);
    		 	break;
    		 		
    		 	case 4:
    		 		captionLabel.setText("Create and manage you goals on the Goals tab");
    		 		nextSceneButton.setVisible(true);
    		 		image = new Image("application/view/images/Raining-Money.jpg", 410, 230, false, true);
    		 		imageView.setImage(image);
    		 	break;
    		 	
    		 	case 5:
    		 		captionLabel.setText("Create a financial budget on the Goals tab");
    		 		image = new Image("application/view/images/images.jpg",  410, 230, false, true);
    		 		imageView.setImage(image);
    		 		
    		 		//end of tutorial no next Button
    		 		nextSceneButton.setVisible(false);
    		 		
    		 		//add Button to enter the MainView.fxml
    		 		getStartedButton.setVisible(true);
    		 	break;
    		 		
    		 }
    	 }
    	 
    	
    	
    }
    
    //TODO Kelly and Johnathan need to be able to link this to the MainView.fxml 
    /**
     * 
     * @param event
     */
    @FXML
    public void getStarted(ActionEvent event) {
    	
    	
    	//Stage create =  new Stage();
    	
    	//create.initModality(Modality.APPLICATION_MODAL);
    	//create.initOwner(Main.stage);
    	try
		{
    		Parent root = FXMLLoader.load(getClass().getResource("../view/resources/MainView.fxml"));
    		Scene scene = new Scene(root);
    		Main.stage.setScene(scene);
    		Main.stage.show();
		} catch(IOException e){
			System.out.printf("The resource 'view/resources/MainView.fxml' could not be located");
		}// END try/catch load FXML
    	/*create.initModality(Modality.APPLICATION_MODAL);
    	create.initOwner(Main.stage);
    	try {
    		Parent start;
        	start = FXMLLoader.load(getClass().getClassLoader().getResource("view/resourses/MainView.fxml"));
        	Scene scene = new Scene(start);
        	create.setScene(scene);
        	create.show();
        	//Main.stage.setScene(new Scene(start, 500, 575));
    	}catch(IOException x) {
    		x.printStackTrace();
    	}*/
    	
    	
    	
    }

}