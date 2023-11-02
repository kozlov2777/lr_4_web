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
import java.util.List;

@WebServlet("/readTeachers")
public class ReadTeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DB db;

    public void init() throws ServletException {
        db = new DB();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            TeacherDAO teacherDAO = new TeacherDAO(db.getConnection());
            List<Teacher> teachers = teacherDAO.getAllTeachers();
            request.setAttribute("teachers", teachers);
            request.getRequestDispatcher("/teachers.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


