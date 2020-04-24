package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class MyBankController {

    @FXML
    private TextField TakeDepositField;

    @FXML
    private Label AmountMoneyLabel;

    @FXML
    private Label MybankLabel;

    @FXML
    private Label AmountMoneyData;

    @FXML
    private Label TakeDepositLabel;

    @FXML
    private Button TakeDepositButton;

    @FXML
    private Button Exitbutton;

    private Database database = new Database();

    @FXML
    public void initialize() {
        ArrayList <Schets> schets = database.getUserWithSchets();
        int index = -1;
        for (int i = 0; i < schets.size(); i++) {
            if (schets.get(i).getUserid() == database.returnId()) {
                index = i;
                break;
            }
        }

        String AmountMoney = Integer.toString(database.getUserWithSchets().get(index).getAmount());
        AmountMoneyData.setText(AmountMoney);

    }



}
