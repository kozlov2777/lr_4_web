package servlet.teacher;

import dao.TeacherDAO;
import db.DB;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteTeacher")
public class DeleteTeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DB db;

    public void init() throws ServletException {
        db = new DB();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));

        try {
            TeacherDAO teacherDAO = new TeacherDAO(db.getConnection());
            teacherDAO.deleteTeacher(teacherId);
            response.sendRedirect(request.getContextPath() + "/readTeachers");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

