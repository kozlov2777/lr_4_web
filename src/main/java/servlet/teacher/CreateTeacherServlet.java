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

@WebServlet("/createTeacher")
public class CreateTeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DB db;

    public void init() throws ServletException {
        db = new DB();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        int age = Integer.parseInt(request.getParameter("age"));
        String workplace = request.getParameter("workplace");
        int experience = Integer.parseInt(request.getParameter("experience"));

        Teacher newTeacher = new Teacher();
        newTeacher.setFullName(fullName);
        newTeacher.setAge(age);
        newTeacher.setWorkplace(workplace);
        newTeacher.setExperience(experience);

        try {
            TeacherDAO teacherDAO = new TeacherDAO(db.getConnection());
            teacherDAO.createTeacher(newTeacher);
            response.sendRedirect(request.getContextPath() + "/readTeachers");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

