import dto.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Login extends HttpServlet {
    public final static List<User> users = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        users.add(user);

        resp.setContentType("text/html");
        String pages = "<form action=\"/add\" method=\"post\">\n" +
                "    <label for=\"username\">Username</label>\n" +
                "    <input type=\"text\" name=\"username\" id=\"username\">\n" +
                "    <br>\n" +
                "    <label for=\"password\">Password</label>\n" +
                "    <input type=\"password\" name=\"password\" id=\"password\">\n" +
                "    <br>\n" +
                "    <input type=\"submit\" value=\"Add\">\n" +
                "</form>" +
                "<a href=\"/cabinet\">Login</a>";

        PrintWriter printWriter = resp.getWriter();
        printWriter.write(pages);
    }
}
