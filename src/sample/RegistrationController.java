package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class RegistrationController {
    @FXML
    private Label DateLabel;

    @FXML
    private Button employeeButton;

    @FXML
    private DatePicker Date;

    @FXML
    private Label RegionLabel;

    @FXML
    private Label registrLabel;

    @FXML
    private Label loginLabel;

    @FXML
    private Label NumberPhoneLabel;

    @FXML
    private Label PasswordLabel;

    @FXML
    private Button Registrbutton;

    @FXML
    private PasswordField passwrodField;


    @FXML
    private Label OperatorLabel;

    @FXML
    private TextField numberPhoneField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField answerField;
    @FXML
    private Label answerLabel;

    @FXML
    private Button Exitbutton;

    @FXML
    private ChoiceBox<String> ChoiceBoxRegion;
    ObservableList <String> list = FXCollections.observableArrayList();

    @FXML
    private ChoiceBox<String> ChoiceBoxNumberPhone;
       ObservableList <String> list1 = FXCollections.observableArrayList();
    public void initialize() {
       employeeButton.setOnAction(event -> {
            change(employeeButton,"registrationemployee");
       });
        RegionloadData();
       OperatorloadData();
        Registrbutton.setOnAction(event -> {
          Database database = new Database() ;
          String nickname = loginField.getText();
          String password = passwrodField.getText();
          if (nickname.isEmpty() || password.isEmpty()) {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setContentText("nickname field or password field is empty!!!");
              alert.showAndWait();
          }
          else if(database.checkUser(nickname)) {
              String city = ChoiceBoxRegion.getValue();
              String operator =  ChoiceBoxNumberPhone.getValue();
                String numberPhone = numberPhoneField.getText();
                LocalDate date = Date.getValue();
                String answer = answerField.getText();
                boolean check = checkNumberPhone(operator,numberPhone);;
                if (check) {
                    try {
                        if (!(answer.isEmpty() || city.isEmpty() || operator.isEmpty() || numberPhone.isEmpty() || date.toString().isEmpty() || ChoiceBoxRegion.toString().isEmpty() || ChoiceBoxNumberPhone.toString().isEmpty())) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("You are Registred!!!");
                            alert.showAndWait();
                            database.addUser(nickname, password, city, numberPhone, operator, date, answer);
                            loginField.setText("");
                            passwrodField.setText("");
                        }
                        }
                    catch (Exception e) {

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Date is empty!!!");
                        alert.showAndWait();

                    }
          }
                else  {
                    System.out.println("Zahodit");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("One or more fields Empty !!!");
                    alert.showAndWait();
                }
          }
          else  {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setContentText("Sorry but this login already exist!!!");
              alert.showAndWait();
              loginField.setText("");
              passwrodField.setText("");
          }

        });

      Exitbutton.setOnAction(event -> {
        change(Exitbutton,"sample");
      });

    }
    public void RegionloadData() {
        String a = "Almaty";
        String b = "Nursyltan";
        String c = "Karaganda";
        String d = "Pavlodar";
        String o = "Oskemen";
        list.addAll(a,b,c,d,o);
        ChoiceBoxRegion.setItems(list);
    }
    public boolean checkNumberPhone(String s, String p) {
      try {
          String Activ = "Activ";

          String Beline = "Beline";
          String Tele2 = "Tele2";
          String Altel = "Altel";
          String[] Activnumber = {"8775", "8778"};
          String[] Belinenumber = {"8702", "8701"};
          String[] Tele2number = {"8777", "8747"};
          String[] Altelnumber = {"8727", "8700"};
          if (s.equals(Activ)) {
              String q = p.substring(0, 4);
              for (int i = 0; i < Activnumber.length; i++) {
                  if (q.equals(Activnumber[i])) {
                      return true;
                  }
              }
          }
          if (s.equals(Beline)) {
              String q = p.substring(0, 4);
              for (int i = 0; i < Belinenumber.length; i++) {
                  if (q.equals(Belinenumber[i])) {
                      return true;
                  }
              }
          }
          if (s.equals(Tele2)) {
              String q = p.substring(0, 4);
              for (int i = 0; i < Tele2number.length; i++) {
                  if (q.equals(Belinenumber[i])) {
                      return true;
                  }
              }
          }
          if (s.equals(Altel)) {
              String q = p.substring(0, 4);
              for (int i = 0; i < Altelnumber.length; i++) {
                  if (q.equals(Altelnumber[i])) {
                      return true;
                  }
              }
          }
      }
      catch (Exception e) {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setContentText("Number Phone is Empty!!!");
          alert.showAndWait();
      }
        return false;
    }
    public void OperatorloadData() {
        String a = "Activ";
        String b = "Beline";
        String c = "Tele2";
        String d = "Altel";
        list1.addAll(a,b,c,d);
        ChoiceBoxNumberPhone.setItems(list1);
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
