package servlet.course;

import dao.CourseDAO;
import db.DB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Course;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/readCourses")
public class ReadCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DB db;

    public void init() throws ServletException {
        db = new DB();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Course> courses;
        try {
            CourseDAO courseDAO = new CourseDAO(db.getConnection());
            courses = courseDAO.getAllCourses();
        } catch (SQLException e) {
            e.printStackTrace();
            courses = new ArrayList<>();
        }

        request.setAttribute("courses", courses);
        request.getRequestDispatcher("courses.jsp").forward(request, response);
    }
}

