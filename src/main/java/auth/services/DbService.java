package auth.services;

import auth.dto.UserDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbService {
    private static final String url = "jdbc:postgresql://localhost:5432/company_security";
    private static final String username = "company_security";
    private static final String password = "571632";
    private static Statement statement;

    private static void init() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        statement = connection.createStatement();
    }

    public static boolean isAuth(UserDto userDto) throws SQLException, ClassNotFoundException {
        init();
        String query = "Select * from users where username = '" + userDto.getUsername() + "' and password = '" + userDto.getPassword() + "'";
        if (statement.executeQuery(query).next()) {
            return true;
        } else {
            return false;
        }
    }

}
