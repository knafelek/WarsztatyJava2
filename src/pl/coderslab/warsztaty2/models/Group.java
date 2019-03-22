package pl.coderslab.warsztaty2.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Group{

    private int id;
    private String name;

    public Group(){
    }

    public Group(String name){
        this.name=name;
    }

    public void saveToDB (Connection conn) throws SQLException {
        if(this.id==0) { // Jeżeli obiekt nie został jeszcze zapisany do bazy danych to:
            String query = "insert into user_group (name) values (?)";
            PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS); //Zwraca id ostatnio utworzonego obiektu
            ps.setString(1, this.name);
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();  // Żeby obiekty były zsynchronizowane z bazą danych
            if (generatedKeys.next()) {
                this.id = generatedKeys.getInt(1);
            }
        } else{
            String sql = "UPDATE user_group SET name=? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, this.name);
            ps.setInt(2, this.id);
            ps.executeUpdate();
        }
    }

    public static Group loadGroupById (Connection conn, int id) throws SQLException { // Funkcja zwracająca użytkownika

        String query = "select * from user_group where id=?";
        PreparedStatement ps = conn.prepareStatement(query);//przypisanie do zmiennej //alt+ENTER automatyczne przypisanie do zmienenj
        ps.setInt(1, id);

        ResultSet resultSet = ps.executeQuery();
        if(resultSet.next()){
            Group group = new Group();
            group.id = resultSet.getInt("id");
            group.name = resultSet.getString("name");
            return group;
        }
        return null; //jeżeli coś nie zadziała to trzeba zwrócić nulla
    }

    public static Group[]loadAllGroups(Connection conn) throws SQLException{ // KOLEKCJE
        ArrayList<Group> groups = new ArrayList<Group>();
        String query = "select * from user_group";
        PreparedStatement pd = conn.prepareStatement(query);

        ResultSet resultSet = pd.executeQuery();

        while(resultSet.next()){
            Group group = new Group();
            group.id = resultSet.getInt("id");
            group.name = resultSet.getString("name");
            groups.add(group);
        }
        Group[] gArray = new Group [groups.size()];
        return gArray;

    }

    public void delete(Connection conn) throws SQLException {
        if (this.id != 0) {
            String sql = "DELETE FROM user_group WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, this.id);
            preparedStatement.executeUpdate();
            this.id = 0; // po usunięciu użytkownika aktualizujemy jego id na == 0 żeby było wiadomo ze go tam już nie ma - synchronizacja z bazą danych
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
