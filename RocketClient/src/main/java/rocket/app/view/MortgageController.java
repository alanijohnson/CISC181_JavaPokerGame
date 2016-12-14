package rocket.app.view;



import java.text.DecimalFormat;

import eNums.eAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;
	
	//	TODO - RocketClient.RocketMainController
	
	//	Create private instance variables for:
	//		TextBox  - 	txtIncome
	//		TextBox  - 	txtExpenses
	//		TextBox  - 	txtCreditScore
	//		TextBox  - 	txtHouseCost
	//		ComboBox -	loan term... 15 year or 30 year
	//		Labels   -  various labels for the controls
	//		Button   -  button to calculate the loan payment
	//		Label    -  to show error messages (exception throw, payment exception)
	
	//labels
	@FXML
	private Label lblTitle;
	
	@FXML
	private Label lblDirections;

	@FXML
	private Label lblIncome;

	@FXML
	private Label lblExpenses;

	@FXML
	private Label lblCreditScore;

	@FXML
	private Label lblHouseCost;

	@FXML
	private Label lblMortgageTerm;

	@FXML
	private Label lblMortgagePayment;
	
	//TextFields
	@FXML
	private TextField txtIncome;

	@FXML
	private TextField txtExpenses;

	@FXML
	private TextField txtCreditScore;

	@FXML
	private TextField txtHouseCost;
	
	//ComboBoxes
	@FXML
	private ComboBox cmbTerm;
	
	//Buttons
	@FXML
	private Button btnCalc;
	
	

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void initialize(){
		cmbTerm.getItems().removeAll(cmbTerm.getItems());
		cmbTerm.getItems().addAll("15","30");
		lblMortgagePayment.setText("");
	}
	
	//	TODO - RocketClient.RocketMainController
	//			Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		//	TODO - RocketClient.RocketMainController
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		//	TODO - RocketClient.RocketMainController
		//			set the loan request details...  rate, term, amount, credit score, downpayment
		//			I've created you an instance of lq...  execute the setters in lq
		
		lblMortgagePayment.setText("");
		lq.setIncome(Double.parseDouble(txtIncome.getText()));
		lq.setExpenses(Double.parseDouble(txtExpenses.getText()));
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		lq.setdAmount(Double.parseDouble(txtHouseCost.getText()));
		lq.setiTerm(Integer.parseInt((String)cmbTerm.getSelectionModel().getSelectedItem()));
		
		a.setLoanRequest(lq);
		
		//	send lq as a message to RocketHub		
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		//	TODO - RocketClient.HandleLoanRequestDetails
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places
		
		double payment;
		double PITI1;
		double PITI2;
		
		payment = lRequest.getdPayment();
		
		PITI1 = lRequest.getIncome() / 12 * 0.28;
		PITI2 = (lRequest.getIncome()/12 * 0.36) - lRequest.getExpenses();
		
		double PITI = (PITI1>PITI2 ? PITI2 : PITI1);
		
		DecimalFormat df = new DecimalFormat("###.##");
		
		if (PITI<payment){
			lblMortgagePayment.setText("House Cost too High");
		} else{
			lblMortgagePayment.setText("Monthly Mortgage Payment: "+ df.format(payment));
		}
		
		
	}
}
