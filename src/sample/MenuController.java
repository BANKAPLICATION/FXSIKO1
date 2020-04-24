package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MenuController {
    @FXML
    private Button TransferButton;

    @FXML
    private Button ExitButton;

    @FXML
    private Label datapasswordLabel;

    @FXML
    private Label AmountMoneyLabel;

    @FXML
    private Button myBankButton;

    @FXML
    private Button TranslateButton;

    @FXML
    private TextField addemailField;

    @FXML
    private Label AmountMoneyData;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label addEmailLabel;

    @FXML
    private AnchorPane MyBankAnchorPane;

    @FXML
    private Button ProfilButton;

    @FXML
    private TextField RecipientPhoneNumberLabel;

    @FXML
    private Label informationlabel;

    @FXML
    private Button Exitbutton;

    @FXML
    private Button addemailButton;

    @FXML
    private AnchorPane profilAnchorPane;

    @FXML
    private Label profilLabel;

    @FXML
    private Label SenderLabel;

    @FXML
    private Label MybankLabel;

    @FXML
    private Label TransferLabel;

    @FXML
    private ImageView imageUser;

    @FXML
    private Label dataLoginlabel;

    @FXML
    private Button exitButton;

    @FXML
    private TextField TakeDepositField;

    @FXML
    private TextField AmountField;

    @FXML
    private AnchorPane VboxAnchorPane;

    @FXML
    private Button editButton;

    @FXML
    private Label LoginLabel;

    @FXML
    private TextField SenderNumberPhoneField;

    @FXML
    private Label RecipientLabel;

    @FXML
    private AnchorPane TransferAnchorPane;

    @FXML
    private Label TakeDepositLabel;

    @FXML
    private Button TakeDepositButton;

    @FXML
    private Label TransferAmountLabel;

    private Database database = new Database();
    public void initialize() {

        TranslateButton.setOnAction(event -> {
            String senderPhone =  SenderNumberPhoneField.getText();
            String recipientPhone = RecipientPhoneNumberLabel.getText();
            int amount = Integer.parseInt(AmountField.getText());
            int a = database.Transfer(senderPhone,recipientPhone,amount);
            AmountMoneyData.setText(Integer.toString(a));
        });
            ArrayList <Schets> schets = database.getUserWithSchets();
            int index = -1;
            int userID = -1;
            for (int i = 0; i < schets.size(); i++) {
                if (schets.get(i).getUserid() == database.returnId()) {
                    index = i;
                    userID = schets.get(i).getUserid();
                    break;
                }
            }
            String AmountMoney = Integer.toString(database.getUserWithSchets().get(index).getAmount());
            AmountMoneyData.setText(AmountMoney);
            ArrayList<User> users = database.getUser();
            int id = -1;
            for (int i = 0; i < users.size() ; i++) {
                if (users.get(i).getId() == Database.returnId()) {
                    id = i;
                    Database.getUserIndex(id);
                    break;
                }
            }
        int finalUserID = userID;

        TakeDepositButton.setOnAction(event -> {
                String depositfield = TakeDepositField.getText();
            System.out.println(depositfield);
                int credit = Integer.parseInt(depositfield);
            System.out.println(credit);
            int a = database.addUserCredit(finalUserID,credit);
            if (a != 0) {
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setContentText("Succesfully added credit!!!");
                  alert.showAndWait();
                  TakeDepositField.setText("");
                 AmountMoneyData.setText(Integer.toString(a));
              }
              else {
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                  alert.setContentText("EROR");
                  alert.showAndWait();
              }
        });
            System.out.println("GG");
        dataLoginlabel.setText(database.getUser().get(id).getLogin());
        datapasswordLabel.setText(database.getUser().get(id).getPassword());
        System.out.println(dataLoginlabel.getText());
        exitButton.setOnAction(event -> {
            change(exitButton,"menu");
        });
        addemailButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Succesufully add email!!!");
            alert.showAndWait();
            String email = addemailField.getText();
            String login = dataLoginlabel.getText();
            String password = datapasswordLabel.getText();
            database.updateEmail(Database.returnId(),email);
            addemailField.setText("");
        });
        editButton.setOnAction(event -> {
            change(editButton,"edit");
        });
        TransferAnchorPane.setVisible(false);
        profilAnchorPane.setVisible(false);
        MyBankAnchorPane.setVisible(false);
        ProfilButton.setOnAction(event -> {
            MyBankAnchorPane.setVisible(false);
            profilAnchorPane.setVisible(true);
            TransferAnchorPane.setVisible(false);
        });
        myBankButton.setOnAction(event -> {
            profilAnchorPane.setVisible(false);
            MyBankAnchorPane.setVisible(true);
            TransferAnchorPane.setVisible(false);
        });
        TransferButton.setOnAction(event -> {
            TransferAnchorPane.setVisible(true);
            MyBankAnchorPane.setVisible(false);
            profilAnchorPane.setVisible(false);
        });
        ExitButton.setOnAction(event -> {
            change(ExitButton,"login");
        });
    }
    public void change(Button button, String url) {
        button.getScene().getWindow().hide();
        FXMLLoader loader  = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/" + url + ".fxml"));
        try {
            loader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
