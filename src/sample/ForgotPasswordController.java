package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ForgotPasswordController {

    @FXML
    private Label ForgotPasswordLabel;

    @FXML
    private Button exitButton;

    @FXML
    private TextField answerField;

    @FXML
    private Button checkButton;

    @FXML
    private Label loginLabel;

    @FXML
    private TextField loginField;

    @FXML
    private Label answerLabel;

    private Database database = new Database();
    @FXML

    public void initialize() {
        checkButton.setOnAction(event -> {

            String login = loginField.getText();
            String answer = answerField.getText();
            database.getUserId(database.checkForgotPassword(login,answer));
            int check = database.checkForgotPassword(login,answer);
            if (check >= 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Your answer is correct!!!");
                alert.showAndWait();
                change(checkButton,"newpassword");
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Your answer is Incorrect!!!");
                alert.showAndWait();
            }
        });
        exitButton.setOnAction(event -> {
            change(exitButton,"login");
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
