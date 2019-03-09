package pl.coderslab.warsztaty2;

import pl.coderslab.warsztaty2.models.User;

import java.sql.*;

public class App {

    public static void main(String[] args) {


        try (Connection conn = ConnectionManager.getConnection()){ //zamiast tu wywoływać połączenie z bazą to wywołano klasę oddzielną do nawiązywania połączenia

            System.out.println("Moja aplikacja!");

/*
                User[] users = User.loadAllUsers(conn);
                for(User user : users){
                    System.out.println(user);
                }*/

            User user = User.loadUserById(conn,12); // po podaniu id użytkownika istniejącego w bazie

            System.out.println(user);

            if(user != null){

/*                user.setUsername("Misiu");
                user.setEmail("misiek@wp.pl");
                user.setPassword("kotek");
                user.saveToDB(conn);*/

                user.delete(conn); // sprawdzenie czy id się zeruje po wywołaniu metody delete

                System.out.println(user);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
