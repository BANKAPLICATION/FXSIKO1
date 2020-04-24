package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;

public class ProfilController {
    private ArrayList <User> users = new ArrayList<>();
    private Database database = new Database();
    @FXML
    private Label dataLoginlabel;

    @FXML
    private Button exitButton;

    @FXML
    private Button addemailButton;

    @FXML
    private Label profilLabel;

    @FXML
    private Label datapasswordLabel;

    @FXML
    private Button editButton;

    @FXML
    private Label informationlabel;

    @FXML
    private Label LoginLabel;

    @FXML
    private TextField addemailField;

    @FXML
    private ImageView imageUser;


    @FXML
    private Label passwordLabel;

    @FXML
    private Label addEmailLabel;
    @FXML

    public void initialize() {
        ArrayList <User> users = database.getUser();
        int id = -1;
        for (int i = 0; i < users.size() ; i++) {
            if (users.get(i).getId() == Database.returnId()) {
                    id = i;
                    Database.getUserIndex(id);
                    break;
            }
        }
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
