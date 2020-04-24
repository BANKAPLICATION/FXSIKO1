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

public class NewPasswordController<publiс> {
    @FXML
    private Button exitButton;

    @FXML
    private TextField newPasswordField;

    @FXML
    private Label newPasswordLabel;

    @FXML
    private Label labelsubnewpassword;

    @FXML
    private Button changeButton;
    private  Database database = new Database();
    @FXML
    public void initialize() {
        changeButton.setOnAction(event -> {
            String newpassword = newPasswordField.getText();
            int id = database.returnId();
            boolean check = database.changePassword(newpassword,id);
            if (check) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Your password succesfully changed!!!");
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Do not changed Try again!!!");
                alert.showAndWait();
            }
        });
        exitButton.setOnAction(event -> {
            change(exitButton,"forgotpassword");
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
