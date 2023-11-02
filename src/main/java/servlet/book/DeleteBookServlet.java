package servlet.book;

import dao.BookDAO;
import db.DB;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DB db;

    public void init() throws ServletException {
        db = new DB();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        try {
            BookDAO bookDAO = new BookDAO(db.getConnection());
            bookDAO.deleteBook(bookId);
            response.sendRedirect(request.getContextPath() + "/readBooks");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}

