package dao;

import models.TeacherSubject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherSubjectDAO {
    private final Connection connection;

    public TeacherSubjectDAO(Connection connection) {
        this.connection = connection;
    }

    public void createTeacherSubject(TeacherSubject teacherSubject) throws SQLException {
        String sql = "INSERT INTO teacher_subjects (teacher_id, subject_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, teacherSubject.getTeacherId());
            statement.setInt(2, teacherSubject.getSubjectId());
            statement.executeUpdate();
        }
    }

    public List<TeacherSubject> getTeacherSubjectsByTeacherId(int teacherId) throws SQLException {
        List<TeacherSubject> teacherSubjects = new ArrayList<>();
        String sql = "SELECT * FROM teacher_subjects WHERE teacher_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, teacherId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    teacherSubjects.add(new TeacherSubject(resultSet.getInt("id"),
                            resultSet.getInt("teacher_id"), resultSet.getInt("subject_id")));
                }
            }
        }
        return teacherSubjects;
    }

    public List<TeacherSubject> getTeacherSubjectsBySubjectId(int subjectId) throws SQLException {
        List<TeacherSubject> teacherSubjects = new ArrayList<>();
        String sql = "SELECT * FROM teacher_subjects WHERE subject_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, subjectId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    teacherSubjects.add(new TeacherSubject(resultSet.getInt("id"),
                            resultSet.getInt("teacher_id"), resultSet.getInt("subject_id")));
                }
            }
        }
        return teacherSubjects;
    }

}

