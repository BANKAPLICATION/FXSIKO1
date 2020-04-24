package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private Button exitButton;

    @FXML
    private Button registrButton;

    @FXML
    private Button loginbutton;
    @FXML
    public void initialize() {
        registrButton.setOnAction(event -> {
            change(registrButton,"Registr");
        });
        loginbutton.setOnAction(event -> {
            change(loginbutton,"login");
        });
        exitButton.setOnAction(event -> {
            System.exit(0);
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
