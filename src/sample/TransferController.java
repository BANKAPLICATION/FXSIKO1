package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TransferController {
    @FXML
    private TextField AmountField;

    @FXML
    private TextField RecipientPhoneNumberLabel;

    @FXML
    private Label SenderLabel;

    @FXML
    private Button TranslateButton;

    @FXML
    private Label TransferLabel;

    @FXML
    private TextField SenderNumberPhoneField;

    @FXML
    private Label RecipientLabel;

    @FXML
    private Label TransferAmountLabel;

    public Database database = new Database();
    @FXML
    public void initialize() {
        TranslateButton.setOnAction(event -> {
          String senderPhone =  SenderNumberPhoneField.getText();
          String recipientPhone = RecipientPhoneNumberLabel.getText();
          int amount = Integer.parseInt(AmountField.getText());
            database.Transfer(senderPhone,recipientPhone,amount);
        });
    }
}
