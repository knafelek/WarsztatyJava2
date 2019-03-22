package pl.coderslab.warsztaty2.models;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;

public class User {

    private int id;
    private String username;
    private String password;
    private String email;

    public User(){
    }
    public User(String username, String password, String email){
        this.username=username;
        this.setPassword(password); // wywołanie settera bo w setterze już jest już zahashowane //dodano this.
        this.email=email;
    }

    //-------------------------------------------------------------------------

    public void  saveToDB(Connection conn) throws SQLException{ // Metoda zapisująca do bazy danych  // Początkowa byłą w try catchu ale przeniesiono połączenie z bazą danych do innej metody w Klasie App

            if(this.id==0) { // Jeżeli obiekt nie został jeszcze zapisany do bazy danych to:
                String query = "insert into user (name, password, email) values (?,?,?)";
                PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS); //Zwraca id ostatnio utworzonego obiektu
                ps.setString(1, this.username);
                ps.setString(2, this.password);
                ps.setString(3, this.email);
                ps.executeUpdate();

                ResultSet generatedKeys = ps.getGeneratedKeys();  // Żeby obiekty były zsynchronizowane z bazą danych
                if (generatedKeys.next()) {
                    this.id = generatedKeys.getInt(1);
                }
            } else{
                String sql = "UPDATE user SET name=?, email=?, password=? where id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, this.username);
                ps.setString(2, this.email);
                ps.setString(3, this.password);
                ps.setInt(4, this.id);
                ps.executeUpdate();
            }


    }

    public static User loadUserById (Connection conn, int id) throws SQLException { // Funkcja zwracająca użytkownika

            String query = "select * from user where id=?";
            PreparedStatement ps = conn.prepareStatement(query);//przypisanie do zmiennej //alt+ENTER automatyczne przypisanie do zmienenj
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                User user = new User();
                user.id = resultSet.getInt("id");
                user.username = resultSet.getString("name");
                user.password = resultSet.getString("password");
                user.email = resultSet.getString("email");
                return user;
            }
        return null; //jeżeli coś nie zadziała to trzeba zwrócić nulla
    }

    public static User[]loadAllUsers(Connection conn) throws SQLException{ // KOLEKCJE
        ArrayList<User>users = new ArrayList<User>();
        String query = "select * from user";
        PreparedStatement pd = conn.prepareStatement(query);

        ResultSet resultSet = pd.executeQuery();

        while(resultSet.next()){
            User user = new User();
            user.id = resultSet.getInt("id");
            user.username = resultSet.getString("name");
            user.password = resultSet.getString("password");
            user.email = resultSet.getString("email");

            users.add(user);
        }
        User[] uArray = new User [users.size()];
        return uArray;

    }

    public void delete(Connection conn) throws SQLException {
        if (this.id != 0) {
            String sql = "DELETE FROM user WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, this.id);
            preparedStatement.executeUpdate();
            this.id = 0; // po usunięciu użytkownika aktualizujemy jego id na == 0 żeby było wiadomo ze go tam już nie ma - synchronizacja z bazą danych
        }
    }


    //-------------------------------------------------------------------------

    /* Utwórz implementację dodatkowych metod realizujących zadania:
        pobranie wszystkich członków danej grupy (dopisz metodę loadAllByGroupId do klasy User).
    */

    //-------------------------------------------------------------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {   // jeżeli nie chcemy dać możliwości zmiany id to trzebaby było usunąć setter setId
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt()); //hashowanie hasła
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

/*
 metoda wyszukująca po imieniu:
przerobiona metoda loadALlUsers bo trzeba zwrócić tablicę a nie jedną wartość
loadAllUsersByName, przekazać imie do stringa na preparedstatement*/
