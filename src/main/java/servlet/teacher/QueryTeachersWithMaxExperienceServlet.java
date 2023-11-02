package servlet.teacher;

import dao.BookDAO;
import db.DB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Teacher;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/queryTeachersWithMaxExperience")
public class QueryTeachersWithMaxExperienceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DB db;

    public void init() throws ServletException {
        db = new DB();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        try {
            BookDAO bookDAO = new BookDAO(db.getConnection());
            List<Teacher> teachers = bookDAO.findTeachersWithMaxExperienceByBookId(bookId);

            request.setAttribute("teachers", teachers);
            request.getRequestDispatcher("/query.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}

