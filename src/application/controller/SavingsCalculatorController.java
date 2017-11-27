package application.controller;

import java.awt.TextField;
import application.model.SavingsCalculatorModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


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
    	num1 = Double.parseDouble(g);
    	num2 = Double.parseDouble(d);

    	time.setText(String.valueOf(model.savingsTime(num1,num2)));
    	System.out.println("clicked!");
    	}
}