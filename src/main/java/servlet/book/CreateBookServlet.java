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

@WebServlet("/createBook")
public class CreateBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DB db;

    public void init() throws ServletException {
        db = new DB();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookName = request.getParameter("bookName");
        Book newBook = new Book();
        newBook.setName(bookName);

        try {
            BookDAO bookDAO = new BookDAO(db.getConnection());
            bookDAO.createBook(newBook);
            response.sendRedirect(request.getContextPath() + "/readBooks");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
