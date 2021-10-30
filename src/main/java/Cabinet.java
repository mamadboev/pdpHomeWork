import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Cabinet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("text/html");
        String htmlPages = "<h1>Authentication page! </h1><form method=\"post\">\n" +
                "    <label for=\"username\">Username</label>\n" +
                "    <input type=\"text\" name=\"username\" id=\"username\">\n" +
                "    <br>\n" +
                "    <label for=\"password\">Password</label>\n" +
                "    <input type=\"password\" name=\"password\" id=\"password\">\n" +
                "    <br>\n" +
                "    <input type=\"submit\" value=\"Login\">\n" +
                "</form>";
        printWriter.write(htmlPages);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String message = "Bunday " + username + " user mavjud emas! ";
        String loogut = "Ortga qaytish";
        for (int i = 0; i < Login.users.size(); i++) {
            if (Login.users.get(i).getUsername().equals(username) && Login.users.get(i).getPassword().equals(password)) {
                message = "Tizimga xush kelibsiz!";
                loogut = "Logout";
            }
        }

        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("<h3>" + message + "</h3><a href='/cabinet'>" + loogut + "</a>");
    }
}
