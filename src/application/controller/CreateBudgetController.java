package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Used to create a Budget and save the result to the Users profile
 * @author Isaac Buitrago
 *
 */
public class CreateBudgetController 
{

    @FXML
    private CheckBox gasExpense;

    @FXML
    private CheckBox rentExpense;

    @FXML
    private Button deleteButton;

    @FXML
    private Button clearButton;

    @FXML
    private GridPane budgetGridPane;

    @FXML
    private Button lContinue;

    @FXML
    private TextField lProjectName;

    @FXML
    private TextField lCost;

    @FXML
    private CheckBox foodExpense;

    @FXML
    private ComboBox<?> expensesComboBox;

    @FXML
    private TextField lType;

    @FXML
    private Button addExpenseButton;

    @FXML
    void lContinue(ActionEvent event) {

    }

    @FXML
    void deleteGoal(ActionEvent event) {

    }

    @FXML
    void clearTextFields(ActionEvent event) {

    }

    @FXML
    void showExpenseCategories(ActionEvent event) {

    }

    @FXML
    void addExpenseCategories(ActionEvent event) {

    }

}

