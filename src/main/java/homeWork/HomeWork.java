package homeWork;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class HomeWork extends HttpServlet {
    private static final String PC_LABEL = "pc";
    private static final String PRINTER_LABEL = "printer";
    private static final String LAPTOP_LABEL = "laptop";

    private final static String url = "jdbc:postgresql://localhost:5432/company_security";
    private final static String username = "company_security";
    private final static String password = "571632";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String queryString = req.getRequestURI();
        if (queryString.startsWith("/")) {
            queryString = queryString.substring(1);
            try {
                Class.forName("org.postgresql.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);

                String name = "";
                switch (queryString) {
                    case PC_LABEL:
                        name = PC_LABEL;
                        break;
                    case PRINTER_LABEL:
                        name = PRINTER_LABEL;
                        break;
                    case LAPTOP_LABEL:
                        name = LAPTOP_LABEL;
                        break;
                }
                String sql = "SELECT * from product where name='" + name + "'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                String text = "<div>";
                while (resultSet.next()) {
                    text += "<h3>" + resultSet.getString("name") + "</h4";
                    text += "<h5>" + resultSet.getString("price") + "</h5>";
                    text += "<h5>" + resultSet.getString("maker") + "</h5>";
                }
                text += "</div>";
                PrintWriter writer = resp.getWriter();
                resp.setContentType("text/html");
                writer.write(text);

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
