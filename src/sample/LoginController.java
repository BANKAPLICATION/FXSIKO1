package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class LoginController {
    @FXML
    private TextField textfieldpassword;

    @FXML
    private Button backbutton;
    @FXML
    private Button ForgotPasswordButton;
    @FXML
    private TextField textfieldnickname;


    @FXML
    private Button loginbutton;

    @FXML
    public void initialize() {
        Database database = new Database();
        backbutton.setOnAction(event -> {
            change(backbutton,"sample");
        });
        loginbutton.setOnAction(event -> {
            String login = textfieldnickname.getText();
            String password = textfieldpassword.getText();
            if (login.isEmpty() || password.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Login or Password field is empty!!!");
                alert.showAndWait();
            }
            else {
                int index = -1;
               if (database.checkUseronLogin(login,password) >= 0) {
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setContentText("You are Logged!!!");
                   alert.showAndWait();
                   database.getUserId(database.checkUseronLogin(login,password));
                   change(loginbutton,"menu");
               }
               else if(database.admin(login,password)) {
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setContentText("Hello Admin!!!");
                   alert.showAndWait();
                       change(loginbutton,"admin");
               }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("In correct please try again!!!");
                    alert.showAndWait();
                    textfieldnickname.setText("");
                    textfieldpassword.setText("");
                }
            }
        });
        ForgotPasswordButton.setOnAction(event -> {
            change(ForgotPasswordButton,"forgotpassword");
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
