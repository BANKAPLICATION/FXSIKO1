package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegistrationEmployee {
    @FXML
    private Label employeeLabel;

    @FXML
    private TextField loginTextfieldl;

    @FXML
    private TextField PasswordTextField;

    @FXML
    private Label loginLabel;

    @FXML
    private Label PasswordLabel;

    @FXML
    private Button LoginButton;
    private Database database = new Database();
    @FXML
    public void initialize() {
        LoginButton.setOnAction(event -> {
          String login = loginTextfieldl.getText();
          String password = PasswordTextField.getText();
          if (login.isEmpty() || password.isEmpty()) {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setContentText("Nickname or Passwor is empty!!!");
              alert.showAndWait();
          }
            else {
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setContentText("You are Logged!!!");
              alert.showAndWait();
              database.addEmployee(login, password);
            loginTextfieldl.setText("");
            PasswordTextField.setText("");
            }
            });
    }
}
