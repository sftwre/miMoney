package application.controller;

import java.awt.TextField;
import application.model.SavingsCalculatorModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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

    @FXML
    private void handleSavings(ActionEvent event) {
        if (begin) {
            goal.setText("");
            begin = false;
        }

        String val = ((Button)event.getSource()).getText();
        goal.setText(goal.getText() + val);
    }
    
    @FXML
    private void handleDeposit(ActionEvent event)
    {   if (begin) {
        deposit.setText("");
        begin = false;
    }

    String val = ((Button)event.getSource()).getText();
    deposit.setText(deposit.getText() + val);
    }
    
    @FXML
    private void handleTime(ActionEvent event) {
    	num1 = Double.parseDouble(goal.getText());
    	num2 = Double.parseDouble(deposit.getText());
    	time.setText(String.valueOf(model.savingsTime(num1, num2)));
    	begin = true;
    }
    
}