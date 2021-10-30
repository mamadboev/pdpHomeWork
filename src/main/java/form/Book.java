package form;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Book extends HttpServlet {

    private final List<form.dto.Book> books = new ArrayList<>(50);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        form.dto.Book book = new form.dto.Book();
        book.setName(name);
        book.setAuthor(author);
        books.add(book);

        resp.setContentType("text/html");
        String pages = "<form action='/add' method='post'><<label for=\"name\">Name</label>\n" +
                "    <input type=\"text\" id=\"name\" name=\"name\">\n" +
                "    <br>\n" +
                "    <label for=\"author\">Author</label>\n" +
                "    <input type=\"text\" id=\"author\" name=\"author\">\n" +
                "    <br>\n" +
                "    <input type=\"submit\" value=\"OK\">";

        String list = "<ul>";
        for (int i = 0; i < books.size(); i++) {
            list += "<li>" + books.get(i).getName() + " " + books.get(i).getAuthor() + "</li>";
        }
        list += "</ul>";
        String result = pages + list;
        PrintWriter writer = resp.getWriter();
        writer.write(result);
    }
}
