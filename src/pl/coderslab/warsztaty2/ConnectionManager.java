package pl.coderslab.warsztaty2;

import pl.coderslab.warsztaty2.models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    public static Connection getConnection () throws SQLException {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/warsztaty2?useSSL=false&characterEncoding=utf8&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root", "coderslab")) {

            return conn;
        }
    }
}
