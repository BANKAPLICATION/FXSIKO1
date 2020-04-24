    package sample;

    import com.mysql.cj.protocol.Resultset;
    import javafx.scene.control.Alert;

    import java.sql.*;
    import java.time.LocalDate;
    import java.util.ArrayList;
    import java.util.Scanner;

    public class Database {

        public static ArrayList <User> users = new ArrayList<>();

        public static ArrayList <Employee> employees = new ArrayList<>();
        public static ArrayList <Schets> schets = new ArrayList<>();

        public static ArrayList<Employee> getEmployees() {
            return employees;
        }

        public static void setEmployees(ArrayList<Employee> employees) {
            Database.employees = employees;
        }

        public static ArrayList<Schets> getSchets() {
            return schets;
        }

        public static void setSchets(ArrayList<Schets> schets) {
            Database.schets = schets;
        }

        public static ArrayList<User> getUsers() {
            return users;
        }

        public static void setUsers(ArrayList<User> users) {
            Database.users = users;
        }


        private PreparedStatement ps;
        private static ResultSet rs;
        private Scanner in = new Scanner(System.in);
        private Connection connection;
        private static String driver = "com.mysql.cj.jdbc.Driver";
        private String databaseURL = "jdbc:mysql://localhost:3306/sikodatabase?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        private static String user = "root";
        private static String password = "";

        public Database() {

            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(databaseURL, user , password);
            }

            catch (Exception e) {
                e.printStackTrace();
            }

        }
        public void addEmployee(String login , String password) {
            try {
                ps = connection.prepareStatement("INSERT INTO employee VALUES(null, ?, ?)");
                ps.setString(1,login);
                ps.setString(2,password);
                ps.execute();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        public ArrayList <Employee> getEmployee() {
               ArrayList <Employee> employees = new ArrayList<>();
               try {
                   ps = connection.prepareStatement("SELECT * FROM employee");
                   rs = ps.executeQuery();
                   while (rs.next()) {
                        int id = rs.getInt("id");
                        String login = rs.getString("login");
                        String password = rs.getString("password");
                    employees.add(new Employee(id,login,password));
                   }
                   ps.execute();
               }
               catch (Exception e) {
                   e.printStackTrace();
               }
               return  employees;
        }
        public void updateEmail(int id,String email) {
            try {
                ps = connection.prepareStatement("SELECT * FROM users");
                rs = ps.executeQuery();
                while (rs.next()) {
                      int id1 = rs.getInt("id");
                      if (id1 == id) {
                          ps = connection.prepareStatement("UPDATE users set email = ? WHERE id = ?");
                          ps.setString(1,email);
                          ps.setInt(2,id);
                          ps.execute();
                      }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        public ArrayList <User> getUser() {
            ArrayList <User> users = new ArrayList<>();
            try {
               ps = connection.prepareStatement("SELECT * FROM users");
               rs = ps.executeQuery();
               while (rs.next()) {
                int id = rs.getInt("id");
                   System.out.println(id);
                String login = rs.getString("login");
                   System.out.println(login);
                String password = rs.getString("password");
                   System.out.println(password);
                users.add(new User(id,login,password));
                 }
             return users;
            }

            catch (Exception e) {
                e.printStackTrace();
            }
            return users;
        }
        public ArrayList<Schets> getUserWithSchets() {
            ArrayList <Schets> schets = new ArrayList<>();
            try {
                ps = connection.prepareStatement("SELECT * FROM schets");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int userid = rs.getInt("userid");
                    String valuta = rs.getString("valuta");
                    int amount = rs.getInt("amount");
                    schets.add(new Schets(id,userid,valuta,amount));
                }
                return schets;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return schets;
        }
        public void addUserWithSchets(int id, String valuta, int amount) {
            try {
                ps = connection.prepareStatement("INSERT INTO schets(id, userid, valuta, amount) VALUES(null, ?, ? , ?)");
                ps.setInt(1,id);
                ps.setString(2,valuta);
                ps.setInt(3,amount);
                ps.execute();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        public int  checkUseronLogin(String login, String password) {
                try {
                    ps = connection.prepareStatement("SELECT * FROM users");
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String login1 = rs.getString("login");
                        String password1 = rs.getString("password");
                        if (login.equals(login1) && password.equals(password1)) {
                                return id;
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                return -1;
        }
        public void addUser(String login, String password, String city, String numberphone, String operator, LocalDate date, String answer) {
              try {
                  ps = connection.prepareStatement("INSERT INTO users(id,login,password,City,NumberPhone,Operator,date,Answer) VALUES(null, ? , ?, ?, ?, ?, ?, ?)");
                  ps.setString(1,login);
                  ps.setString(2,password);
                  ps.setString(3,city);
                  ps.setString(4,numberphone);
                  ps.setString(5,operator);
                    ps.setDate(6, Date.valueOf(date));
                    ps.setString(7, answer);
                  ps.executeUpdate();
                  users.add(new User(login,password,city,numberphone,operator,date, answer));
                   ps = connection.prepareStatement("SELECT * FROM users");
                   rs = ps.executeQuery();
                   while (rs.next()) {
                       int id = rs.getInt("id");
                       String valuta = "tng";
                       int amount = 0;
                       addUserWithSchets(id,valuta,amount);
                   }
              }
              catch (Exception e) {
                  e.printStackTrace();
              }
        }
        public int addUserCredit(int userid, int amount) {
           int oldAmount = 0;
            try {
                ps = connection.prepareStatement("SELECT * FROM schets");
                        rs = ps.executeQuery();
                        while (rs.next()) {
                    int userID = rs.getInt("userid");
                    if (userID == userid) {
                      oldAmount = rs.getInt("amount");
                     ps = connection.prepareStatement("UPDATE schets set amount = ? WHERE userid = ?");
                     ps.setInt(1,amount + oldAmount);
                     ps.setInt(2,userID);
                    ps.execute();
                 return amount + oldAmount;
                 }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return oldAmount;
        }
        public static int id = -1;
        public static int userid = -1;
       public static int oldAmount = 0;
        public static int amount1 = 0;
        public void Transfer(String senderPhone, String recipient, int amount) {
            try {
                 ps = connection.prepareStatement("SELECT * FROM users");
                 rs = ps.executeQuery();
                 while (rs.next()) {
                     String senderPhone1 = rs.getString("NumberPhone");
                     if (senderPhone.equals(senderPhone1)) {
                          userid = rs.getInt("id");
                         ps = connection.prepareStatement("SELECT * FROM schets");
                         rs = ps.executeQuery();
                         while (rs.next()) {
                             int userID = rs.getInt("userid");
                             if (userID == userid) {
                                 amount1 = rs.getInt("amount");
                                 if (amount1 < amount) {
                                     Alert alert = new Alert(Alert.AlertType.ERROR);
                                     alert.setContentText("Not enough money!!!");
                                     alert.showAndWait();
                                 break;
                                 }
                                 else {
                                        ps = connection.prepareStatement("SELECT * FROM users");
                                        rs = ps.executeQuery();
                                        while (rs.next()) {
                                            String recipientPhone = rs.getString("NumberPhone");
                                                if (recipientPhone.equals(recipient)) {
                                                    id = rs.getInt("id");
                                                    ps = connection.prepareStatement("SELECT * FROM schets");
                                                    rs = ps.executeQuery();
                                                    while (rs.next()) {
                                                        int userid2 = rs.getInt("userid");
                                                        if (id == userid2) {
                                                            oldAmount = rs.getInt("amount");
                                                        }
                                                    }
                                                }}
                                        ps = connection.prepareStatement("UPDATE schets set amount = ? WHERE userid = ?");
                                        ps.setInt(1,oldAmount + amount);
                                        ps.setInt(2,id);
                                        ps.execute();
                                        ps = connection.prepareStatement("SELECT * FROM schets");
                                        rs = ps.executeQuery();
                                        while (rs.next()) {
                                            int id = rs.getInt("userid");
                                            if (id == userid) {
                                                ps = connection.prepareStatement("UPDATE schets set amount = ? WHERE userid = ?");
                                                ps.setInt(1,amount1 - amount);
                                                ps.setInt(2,id);
                                                ps.execute();
                                            }
                                        }
                                     System.out.println(amount);
                                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                     alert.setContentText("Succesfully Translated!!!");
                                     alert.showAndWait();
                                 }
                             }
                         }
                     }
                 }
            }

            catch (Exception e) {
                e.printStackTrace();
            }

        }
         public boolean admin(String login,String password) {
            try {
                ps = connection.prepareStatement("SELECT * FROM admin"); {
                    rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String login1 = rs.getString("login");
                    String password1 = rs.getString("password");
                    if (login.equals(login1) && password.equals(password1)) {
                        return true;
                    }
                }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
        public void addUserWithEmail(User user) {
            try {
                ps = connection.prepareStatement("INSERT INTO users(id,login,password,email) VALUES(NULL, ? , ?, ?)");
                ps.setString(1,user.getLogin());
                ps.setString(2,user.getPassword());
                ps.setString(3,user.getEmail());
                ps.execute();
                users.add(user);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void editUser(int id,String login,String password) {
            try {
                ps = connection.prepareStatement("SELECT * FROM users");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id1 = rs.getInt("id");
                    if (id1  == id) {
                        ps = connection.prepareStatement("UPDATE users set login = ?, password = ? WHERE id = ?");
                        ps.setString(1,login);
                        ps.setString(2,password);
                        ps.setInt(3,id);
                        ps.execute();
                    }
                }

            }

            catch (Exception e) {
                e.printStackTrace();
            }

        }

        public int aluidUser(int id, String login, String password) {
            try {
                ps = connection.prepareStatement("SELECT * FROM users");
                rs =  ps.executeQuery();
                boolean check = false;
                int index = -1;
                while (rs.next()) {
                    int idi = rs.getInt("id");
                    String login1 = rs.getString("login");
                    String password1 = rs.getString("password");
                    for (int i = 0; i < users.size() ; i++) {
                        if (i == id) {
                            if (login.equals(login1) && password.equals(password1)) {
                                return idi;
                            }
                        }
                    }
                }
            }

            catch (SQLException e) {

                e.printStackTrace();

            }

            return -1;

        }
        public boolean checkUser(String nickname) {

            try {
             ps = connection.prepareStatement("SELECT * FROM users"); {
                 rs = ps.executeQuery();
                 while (rs.next()) {
                 String nicknamee = rs.getString("login");
                 if (nickname.equals(nicknamee)) {
                    return false;
                 }
                 }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }

                public void deleteUser(int id) {

            try {

                ps = connection.prepareStatement("DELETE FROM users WHERE id = ?");

                ps.setInt(1,id);

                ps.execute();

            }

            catch (Exception e) {

                e.printStackTrace();

            }

        }

        public int checkForgotPassword(String login, String answer) {
                try {
                        ps = connection.prepareStatement("SELECT * FROM users");
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            int id = rs.getInt("id");
                            String login1 = rs.getString("login");
                            if (login.equals(login1)) {
                                    String answer1 = rs.getString("Answer");
                                    if (answer.equals(answer1)) {
                                        return id;
                                    }
                            }
                        }
                }

                catch (Exception e) {

                    e.printStackTrace();

                }
            return -1;
        }

        public boolean changePassword(String newPassword, int id) {

            try {

                ps = connection.prepareStatement("SELECT * FROM users");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id1 = rs.getInt("id");
                    if (id1 == id) {
                     ps = connection.prepareStatement("update users set password = ? WHERE id = ?");
                        ps.setString(1,newPassword);
                        ps.setInt(2,id);
                    ps.execute();
                        return true;
                    }
                }
            }

            catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
        static  int  a = -1;
        public  static void getUserId(int i) {
            a = i;
        }
        public static int returnId() {
            return a;
        }
        static  int b = -1;
        public static void getUserIndex(int i) {
            b = i;
        }
        public static int returnIndex() {
            return b;
        }



    }
