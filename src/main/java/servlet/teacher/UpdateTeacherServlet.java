package servlet.teacher;

import dao.TeacherDAO;
import db.DB;
import models.Teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/updateTeacher")
public class UpdateTeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DB db;

    public void init() throws ServletException {
        db = new DB();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int teacherId = Integer.parseInt(request.getParameter("teacherIdU"));
        String fullName = request.getParameter("fullNameU");
        int age = Integer.parseInt(request.getParameter("ageU"));
        String workplace = request.getParameter("workplaceU");
        int experience = Integer.parseInt(request.getParameter("experienceU"));

        try {
            TeacherDAO teacherDAO = new TeacherDAO(db.getConnection());
            Teacher teacher = teacherDAO.getTeacherById(teacherId);
            if (teacher != null) {
                teacher.setFullName(fullName);
                teacher.setAge(age);
                teacher.setWorkplace(workplace);
                teacher.setExperience(experience);
                teacherDAO.updateTeacher(teacher);
                response.sendRedirect(request.getContextPath() + "/readTeachers");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
