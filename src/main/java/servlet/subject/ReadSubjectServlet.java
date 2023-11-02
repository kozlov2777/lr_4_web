package servlet.subject;

import dao.SubjectDAO;
import db.DB;
import models.Subject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/readSubjects")
public class ReadSubjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DB db;

    public void init() throws ServletException {
        db = new DB();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SubjectDAO subjectDAO = new SubjectDAO(db.getConnection());
            List<Subject> subjects = subjectDAO.getAllSubjects();
            request.setAttribute("subjects", subjects);
            request.getRequestDispatcher("/subjects.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}





