package application.controller;

import java.awt.TextField;
import application.model.SavingsCalculatorModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


/**
 * 
 * @author Sam Dash
 *
 */

public class SavingsCalculatorController{
	
    @FXML
    private TextField deposit;
    
    @FXML
    private TextField goal;
    
    @FXML
    private TextField time;
    
    private SavingsCalculatorModel model = new SavingsCalculatorModel();
    private boolean begin = true;
    private double num1 = 0;
    private double num2 = 0;

    
    private void handleSavings() {
        if (begin) {
            goal.setText("");
            begin = false;
        }

     //   String val = ((Button)event.getSource()).getText();
        //goal.setText(goal.getText() + val);
    }
    
    private void handleDeposit()
    {   if (begin) {
        deposit.setText("");
        begin = false;
    }

   // String val = ((Button)event.getSource()).getText();
    //deposit.setText(deposit.getText() + val);
    }
    
    
    private void handleTime() {
    	num1 = Double.parseDouble(goal.getText());
    	num2 = Double.parseDouble(deposit.getText());
    	time.setText(String.valueOf(model.savingsTime(num1, num2)));
    	begin = true;
    }
    
    /**
     * Do computations here, call the handle methods above if you have to
     * @param event
     */
    @FXML
    public void handle(ActionEvent event)
    {
    	
    }
}
