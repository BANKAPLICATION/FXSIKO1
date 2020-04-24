package sample;

import com.sun.org.apache.bcel.internal.generic.DADD;
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
import java.util.ArrayList;

public class EditController {

    private Database database = new Database();
    private ArrayList <User> users = database.getUser();
    @FXML
    private TextField LoginField;

    @FXML
    private Button exitButton;

    @FXML
    private Label dataOldPassword;

    @FXML
    private Button ChangeButton;

    @FXML
    private Label oldLoginLabel;

    @FXML
    private Label oldPasswordLabel;

    @FXML
    private TextField PasswordField;

    @FXML
    private Label EditLabel;

    @FXML
    private Label dataOldlabelLogin;


    public void initialize() {

        dataOldlabelLogin.setText(database.getUser().get(Database.returnIndex()).getLogin());
        dataOldPassword.setText(database.getUser().get(Database.returnIndex()).getPassword());

        ChangeButton.setOnAction(event -> {
             String login = LoginField.getText();
             String password = PasswordField.getText();
             database.editUser(Database.returnId(),login,password);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Succesufully changed!!!");
            alert.showAndWait();
          LoginField.setText("");
         PasswordField.setText("");
        });
        exitButton.setOnAction(event -> {
           change(exitButton,"profil");
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
