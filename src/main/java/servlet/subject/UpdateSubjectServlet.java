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

@WebServlet("/updateSubject")
public class UpdateSubjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DB db;

    public void init() throws ServletException {
        db = new DB();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int subjectId = Integer.parseInt(request.getParameter("subjectIdU"));
        String subjectName = request.getParameter("subjectNameU");

        try {
            SubjectDAO subjectDAO = new SubjectDAO(db.getConnection());
            Subject subject = subjectDAO.getSubjectById(subjectId);
            if (subject != null) {
                subject.setName(subjectName);
                subjectDAO.updateSubject(subject);
                response.sendRedirect(request.getContextPath() + "/readSubjects");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


