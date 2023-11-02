package dao;
import models.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseDAO {
    private final Connection connection;

    public CourseDAO(Connection connection) {
        this.connection = connection;
    }

    public void createCourse(Course course) throws SQLException {
        String sql = "INSERT INTO courses (name, link, cost, startDate, subjectId) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, course.getName());
            statement.setString(2, course.getLink());
            statement.setDouble(3, course.getCost());
            statement.setDate(4, new java.sql.Date(course.getStartDate().getTime()));
            statement.setInt(5, course.getSubjectId());
            statement.executeUpdate();
        }
    }

    public Course getCourseById(int id) throws SQLException {
        String sql = "SELECT * FROM courses WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Course(resultSet.getInt("id"), resultSet.getString("name"),
                            resultSet.getString("link"), resultSet.getDouble("cost"),
                            resultSet.getDate("startDate"), resultSet.getInt("subjectId"));
                }
                return null;
            }
        }
    }

    public List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                courses.add(new Course(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("link"), resultSet.getDouble("cost"),
                        resultSet.getDate("startDate"), resultSet.getInt("subjectId")));
            }
        }
        return courses;
    }

    public void updateCourse(Course course) throws SQLException {
        String sql = "UPDATE courses SET name=?, link=?, cost=?, startDate=?, subjectId=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, course.getName());
            statement.setString(2, course.getLink());
            statement.setDouble(3, course.getCost());
            statement.setDate(4, new java.sql.Date(course.getStartDate().getTime()));
            statement.setInt(5, course.getSubjectId());
            statement.setInt(6, course.getId());
            statement.executeUpdate();
        }
    }

    public void deleteCourse(int id) throws SQLException {
        String sql = "DELETE FROM courses WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}

