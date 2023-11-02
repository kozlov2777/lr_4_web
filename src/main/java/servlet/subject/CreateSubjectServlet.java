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

@WebServlet("/createSubject")
public class CreateSubjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DB db;

    public void init() throws ServletException {
        db = new DB();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String subjectName = request.getParameter("subjectName");

        Subject newSubject = new Subject();
        newSubject.setName(subjectName);

        try {
            SubjectDAO subjectDAO = new SubjectDAO(db.getConnection());
            subjectDAO.createSubject(newSubject);
            response.sendRedirect(request.getContextPath() + "/readSubjects");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
