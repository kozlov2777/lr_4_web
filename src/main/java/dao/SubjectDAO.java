package dao;

import models.Subject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {
    private final Connection connection;

    public SubjectDAO(Connection connection) {
        this.connection = connection;
    }

    public void createSubject(Subject subject) throws SQLException {
        String sql = "INSERT INTO subjects (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, subject.getName());
            statement.executeUpdate();
        }
    }

    public Subject getSubjectById(int id) throws SQLException {
        String sql = "SELECT * FROM subjects WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Subject(resultSet.getInt("id"), resultSet.getString("name"), null, null);
                }
                return null;
            }
        }
    }

    public List<Subject> getAllSubjects() throws SQLException {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM subjects";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                subjects.add(new Subject(resultSet.getInt("id"), resultSet.getString("name"), null, null));
            }
        }
        return subjects;
    }

    public void updateSubject(Subject subject) throws SQLException {
        String sql = "UPDATE subjects SET name=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, subject.getName());
            statement.setInt(2, subject.getId());
            statement.executeUpdate();
        }
    }

    public void deleteSubject(int id) throws SQLException {
        String sql = "DELETE FROM subjects WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}


