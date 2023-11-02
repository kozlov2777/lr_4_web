package dao;

import models.Teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TeacherDAO {
    private final Connection connection;

    public TeacherDAO(Connection connection) {
        this.connection = connection;
    }

    public void createTeacher(Teacher teacher) throws SQLException {
        String sql = "INSERT INTO teachers (full_name, age, workplace, experience) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, teacher.getFullName());
            statement.setInt(2, teacher.getAge());
            statement.setString(3, teacher.getWorkplace());
            statement.setInt(4, teacher.getExperience());
            statement.executeUpdate();
        }
    }

    public Teacher getTeacherById(int id) throws SQLException {
        String sql = "SELECT * FROM teachers WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Teacher(resultSet.getInt("id"), resultSet.getString("full_name"),
                            resultSet.getInt("age"), resultSet.getString("workplace"),
                            resultSet.getInt("experience"), null);
                }
                return null;
            }
        }
    }

    public List<Teacher> getAllTeachers() throws SQLException {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM teachers";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                teachers.add(new Teacher(resultSet.getInt("id"), resultSet.getString("full_name"),
                        resultSet.getInt("age"), resultSet.getString("workplace"),
                        resultSet.getInt("experience"), null));
            }
        }
        return teachers;
    }

    public void updateTeacher(Teacher teacher) throws SQLException {
        String sql = "UPDATE teachers SET full_name=?, age=?, workplace=?, experience=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, teacher.getFullName());
            statement.setInt(2, teacher.getAge());
            statement.setString(3, teacher.getWorkplace());
            statement.setInt(4, teacher.getExperience());
            statement.setInt(5, teacher.getId());
            statement.executeUpdate();
        }
    }

    public void deleteTeacher(int id) throws SQLException {
        String sql = "DELETE FROM teachers WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}

