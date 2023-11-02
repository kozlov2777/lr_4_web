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

@WebServlet("/updateBook")
public class UpdateBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DB db;

    public void init() throws ServletException {
        db = new DB();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookIdU"));
        String bookName = request.getParameter("bookNameU");

        try {
            BookDAO bookDAO = new BookDAO(db.getConnection());
            Book book = bookDAO.getBookById(bookId);
            if (book != null) {
                book.setName(bookName);
                bookDAO.updateBook(book);
                response.sendRedirect(request.getContextPath() + "/readBooks");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

