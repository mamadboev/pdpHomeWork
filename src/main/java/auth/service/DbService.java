package auth.service;

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

    private static void init() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean userSave(UserDto userDto) throws SQLException {
        init();
        String query = "Select * from users where username = '" + userDto.getUsername() + "'";
        try {
            if (!statement.execute(query)) {
                throw new SQLException("User already exsist");
            }

            String insertQuery = "Insert into users (username, password, full_name) values('" + userDto.getUsername() +
                    "','" + userDto.getPassword() +
                    "','" + userDto.getFull_name() +
                    "')";

            if (!statement.execute(insertQuery)) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        }
    }
}
