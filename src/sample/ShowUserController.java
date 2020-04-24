package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ShowUserController {
    @FXML
    private ListView<User> ListView;
    @FXML
    private TableView<User> tableView;
    private ArrayList<User> list = new Database().getUser();
    @FXML
    private ArrayList<User> list1 = new Database().getUser();
    @FXML
    private ObservableList <User> o_list = FXCollections.observableArrayList();
    private ObservableList <User> t_list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<User, String> tableColumPassword;
    @FXML
    private Button exitButton;
    @FXML
    private TableColumn<User, Integer> tableColumnId;

    @FXML
    private TableColumn<User, String> tableColumnLogin;

    public void initialize() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnLogin.setCellValueFactory(new PropertyValueFactory<>("Login"));
        tableColumPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
        o_list.setAll(list);
        t_list.setAll(list1);
        ListView.setItems(o_list);
        tableView.setItems(t_list);
        exitButton.setOnAction(event -> {
            change(exitButton,"admin");
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
