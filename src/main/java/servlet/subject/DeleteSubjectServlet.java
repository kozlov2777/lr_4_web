package servlet.subject;

import dao.SubjectDAO;
import db.DB;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteSubject")
public class DeleteSubjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DB db;

    public void init() throws ServletException {
        db = new DB();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));

        try {
            SubjectDAO subjectDAO = new SubjectDAO(db.getConnection());
            subjectDAO.deleteSubject(subjectId);
            response.sendRedirect(request.getContextPath() + "/readSubjects");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
