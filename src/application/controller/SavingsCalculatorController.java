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
    private TextField goal;
    
    @FXML
    private TextField deposit;
    
    @FXML
    private TextField time;
    
    private boolean begin = true;
    private double num1 = 0;
    private double num2 = 0;
    private SavingsCalculatorModel model = new SavingsCalculatorModel(num1, num2);
    
    /**
     * Do computations here, call the handle methods above if you have to
     * @param event
     */
    @FXML
    public void handle(ActionEvent event)
    {
    	String g = goal.getText();
    	String d = deposit.getText();
    	
    	if (begin) {
            time.setText("");
            begin = false;
        }
    	
    	String val = ((Button)event.getSource()).getText();
    	
    	if (val.equals("Calculate")) {
		    	if(time.getText().equals(""))
		    		return;
    	num1 = Double.parseDouble(g);
    	num2 = Double.parseDouble(d);
    	}
    	else {
    	time.setText(String.valueOf(model.savingsTime(num1,num2)));
    	begin = true;
    	}
    }
}