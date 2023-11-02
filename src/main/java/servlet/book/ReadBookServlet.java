package servlet.book;

import dao.BookDAO;
import db.DB;
import models.Book;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/readBooks")
public class ReadBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DB db;

    public void init() throws ServletException {
        db = new DB();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            BookDAO bookDAO = new BookDAO(db.getConnection());
            List<Book> books = bookDAO.getAllBooks();
            request.setAttribute("books", books);
            request.getRequestDispatcher("/books.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

