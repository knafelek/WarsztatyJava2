package pl.coderslab.warsztaty2.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Solution {

    private int id;
    private String created;
    private String updated;
    private String description;
    private int exerciseId;
    private int usersId;

    public Solution(){

    }

    public Solution(String created, String updated, String description, int exerciseId, int usersId){
        this.created=created;
        this.updated=updated;
        this.description=description;
        this.exerciseId=exerciseId;
        this.usersId=usersId;
    }

    public void  saveToDB(Connection conn) throws SQLException {

        if(this.id==0) {
            String query = "insert into solution (created, updated, description, exercise_id, users_id ) values (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.created);
            ps.setString(2, this.updated);
            ps.setString(3, this.description);
            ps.setInt(4, this.exerciseId);
            ps.setInt(5, this.usersId);
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();  // Synchronizacja z bazą danych
            if (generatedKeys.next()) {
                this.id = generatedKeys.getInt(1);
            }
        } else{
            String sql = "UPDATE solution SET created=?, updated=?, description=?, exercise_id=?, users_id=? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, this.created);
            ps.setString(2, this.updated);
            ps.setString(3, this.description);
            ps.setInt(4, this.exerciseId);
            ps.setInt(5, this.usersId);
            ps.setInt(6, this.id);
            ps.executeUpdate();
        }
    }

    public static Solution loadSolutionById (Connection conn, int id) throws SQLException {

        String query = "select * from solution where id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);

        ResultSet resultSet = ps.executeQuery();
        if(resultSet.next()){
            Solution solution = new Solution();
            solution.id = resultSet.getInt("id");
            solution.created = resultSet.getString("created");
            solution.updated = resultSet.getString("updated");
            solution.description = resultSet.getString("description");
            solution.exerciseId = resultSet.getInt("exercise_id");
            solution.usersId = resultSet.getInt("users_id");
            return solution;
        }
        return null;
    }

    public static Solution[]loadAllSolutions(Connection conn) throws SQLException{
        ArrayList<Solution> solutions = new ArrayList<Solution>();
        String query = "select * from solution";
        PreparedStatement pd = conn.prepareStatement(query);

        ResultSet resultSet = pd.executeQuery();

        while(resultSet.next()){
            Solution solution = new Solution ();
            solution.id = resultSet.getInt("id");
            solution.created = resultSet.getString("created");
            solution.updated = resultSet.getString("updated");
            solution.description = resultSet.getString("description");
            solution.exerciseId = resultSet.getInt("exercise_id");
            solution.usersId = resultSet.getInt("users_id");
            solutions.add(solution);
        }
        Solution[] sArray = new Solution [solutions.size()];
        return sArray;

    }

    public void delete(Connection conn) throws SQLException {
        if (this.id != 0) {
            String sql = "DELETE FROM solution WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, this.id);
            preparedStatement.executeUpdate();
            this.id = 0; // po usunięciu aktualizujemy id na == 0 - synchronizacja z bazą danych
        }
    }

    //-----------------------------------------------------------------------------------------------------

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", description='" + description + '\'' +
                ", exercise_id='" + exerciseId + '\'' +
                ", users_id='" + usersId + '\'' +
                '}';
    }

}
