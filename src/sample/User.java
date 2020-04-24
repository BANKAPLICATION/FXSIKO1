package sample;

import java.sql.Date;
import java.time.LocalDate;

public class User {
    
    private int id;
    private String login;
    private String password;
    private String email;
    private String NumberPhone;
    private String Operator;
    private String city;
    private LocalDate date;
    private String answer;

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public User(String login, String password) {
    }


    public User(String login, String password, String city, String numberphone, String operator, LocalDate date, String answer) {
        this.login = login;
        this.password = password;
       this.NumberPhone = numberphone;
        Operator = operator;
        this.city = city;
        this.date = date;
        this.answer = answer;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", date=" + date +
                '}';
    }
}
