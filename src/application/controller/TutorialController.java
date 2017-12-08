package application.controller;

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

/**
 * @author Kelly Malcolm
 * @author Isaac Buitrago
 *
 */
public class TutorialController 
{

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
    
    /**
     * 
     * @param event
     */
    @FXML
    public void getStarted(ActionEvent event) 
    {
    	
    	
    	try
		{
    		Parent root;
    		Scene scene;
    		
    		if(Main.session.currentUser.isPassAuthenticated())
    			root = FXMLLoader.load(getClass().getResource("../view/resources/MainView.fxml"));
    		
    		// return to the CreateAccount view
    		else
    			root = FXMLLoader.load(getClass().getResource("../view/resources/CreateAccount2.fxml"));
    			
    		scene = new Scene(root);
    		
    		Main.stage.setWidth(1000);
    		Main.stage.setHeight(730);
    		Main.stage.setScene(scene);
    		
    		Main.stage.show();
    		
		} catch(IOException e){
			System.err.printf("The resource 'view/resources/MainView.fxml' could not be located");

		}    	
    	
    }

}