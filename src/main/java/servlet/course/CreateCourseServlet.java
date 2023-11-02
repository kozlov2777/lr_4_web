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
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/createCourse")
public class CreateCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DB db;

    public void init() throws ServletException {
        db = new DB();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String courseName = request.getParameter("courseName");
        String courseLink = request.getParameter("courseLink");
        double courseCost = Double.parseDouble(request.getParameter("courseCost"));
        Date startDate = Date.valueOf(request.getParameter("startDate"));
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));

        Course newCourse = new Course();
        newCourse.setName(courseName);
        newCourse.setLink(courseLink);
        newCourse.setCost(courseCost);
        newCourse.setStartDate(startDate);
        newCourse.setSubjectId(subjectId);

        try {
            CourseDAO courseDAO = new CourseDAO(db.getConnection());
            courseDAO.createCourse(newCourse);
            response.sendRedirect(request.getContextPath() + "/readCourses");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
