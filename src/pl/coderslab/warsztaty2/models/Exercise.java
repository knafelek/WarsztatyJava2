package pl.coderslab.warsztaty2.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Exercise {

    private int id;
    private String title;
    private String description;

    public Exercise (){

    }
    public Exercise (String title, String description){
        this.title=title;
        this.description=description;
    }

    //-------------------------------------------------------------------------

    public void  saveToDB(Connection conn) throws SQLException { // Metoda zapisująca do bazy danych  // Początkowa byłą w try catchu ale przeniesiono połączenie z bazą danych do innej metody w Klasie App

        if(this.id==0) { // Jeżeli obiekt nie został jeszcze zapisany do bazy danych to:
            String query = "insert into exercise (title, description) values (?,?)";
            PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS); //Zwraca id ostatnio utworzonego obiektu
            ps.setString(1, this.title);
            ps.setString(2, this.description);
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();  // Żeby obiekty były zsynchronizowane z bazą danych
            if (generatedKeys.next()) {
                this.id = generatedKeys.getInt(1);
            }
        } else{
            String sql = "UPDATE exercise SET title=?, description=?, where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, this.title);
            ps.setString(2, this.description);
            ps.setInt(3, this.id);
            ps.executeUpdate();
        }
    }

    public static Exercise loadExerciseById (Connection conn, int id) throws SQLException { // Funkcja zwracająca użytkownika

        String query = "select * from exercise where id=?";
        PreparedStatement ps = conn.prepareStatement(query);//przypisanie do zmiennej //alt+ENTER automatyczne przypisanie do zmienenj
        ps.setInt(1, id);

        ResultSet resultSet = ps.executeQuery();
        if(resultSet.next()){
            Exercise exercise = new Exercise();
            exercise.id = resultSet.getInt("id");
            exercise.title = resultSet.getString("title");
            exercise.description = resultSet.getString("description");
            return exercise;
        }
        return null; //jeżeli coś nie zadziała to trzeba zwrócić nulla
    }

    public static Exercise[]loadAllExercises(Connection conn) throws SQLException{ // KOLEKCJE
        ArrayList<Exercise> exe = new ArrayList<Exercise>();
        String query = "select * from exercise";
        PreparedStatement pd = conn.prepareStatement(query);

        ResultSet resultSet = pd.executeQuery();

        while(resultSet.next()){
            Exercise exercise = new Exercise();
            exercise.id = resultSet.getInt("id");
            exercise.title = resultSet.getString("title");
            exercise.description = resultSet.getString("description");
            exe.add(exercise);
        }
        Exercise[] eArray = new Exercise [exe.size()];
        return eArray;

    }

    public void delete(Connection conn) throws SQLException {
        if (this.id != 0) {
            String sql = "DELETE FROM exercise WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, this.id);
            preparedStatement.executeUpdate();
            this.id = 0; // po usunięciu aktualizujemy id na == 0 żeby było wiadomo ze go tam już nie ma - synchronizacja z bazą danych
        }
    }

    //-------------------------------------------------------------------------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
