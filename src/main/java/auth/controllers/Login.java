package auth.controllers;

import auth.dto.UserDto;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class Login extends HttpServlet {

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String username = req.getParameter("username");
        String full_name = req.getParameter("full_name");
        String password = req.getParameter("password");
        String prepassword = req.getParameter("prepassword");
        UserDto userDto = new UserDto();
        userDto.setPassword(password);
        userDto.setPrepassword(prepassword);
        userDto.setUsername(username);
        userDto.setFull_name(full_name);

        PrintWriter printWriter = resp.getWriter();
        boolean isSave = false;

        if (UserDto.isPassword(userDto)) {
            isSave = true;
        }

        try {
            if (isSave && UserDto.save(userDto)) {
                printWriter.write("<h3>Malumot saqlandi!</h3>");
            } else {
                printWriter.write("<h3>Ma'lumot saqlanmadi!</h3>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        }


    }
}
