package auth.controllers;

import auth.dto.UserDto;
import auth.services.DbService;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Auth extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        PrintWriter printWriter = resp.getWriter();
        if (DbService.isAuth(userDto)) {
            printWriter.write("Kabinetga xush kelibsiz!");
            resp.sendRedirect("http://localhost:8080/cabinet");
        } else {
            printWriter.write("Bunday User mavjud emas username = " + username);
        }
    }
}
