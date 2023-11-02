package dao;

import models.SubjectBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectBookDAO {
    private final Connection connection;

    public SubjectBookDAO(Connection connection) {
        this.connection = connection;
    }

    public void createSubjectBook(SubjectBook subjectBook) throws SQLException {
        String sql = "INSERT INTO subject_books (subject_id, book_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, subjectBook.getSubjectId());
            statement.setInt(2, subjectBook.getBookId());
            statement.executeUpdate();
        }
    }

    public List<SubjectBook> getSubjectBooksBySubjectId(int subjectId) throws SQLException {
        List<SubjectBook> subjectBooks = new ArrayList<>();
        String sql = "SELECT * FROM subject_books WHERE subject_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, subjectId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    subjectBooks.add(new SubjectBook(resultSet.getInt("id"),
                            resultSet.getInt("subject_id"), resultSet.getInt("book_id")));
                }
            }
        }
        return subjectBooks;
    }

    public List<SubjectBook> getSubjectBooksByBookId(int bookId) throws SQLException {
        List<SubjectBook> subjectBooks = new ArrayList<>();
        String sql = "SELECT * FROM subject_books WHERE book_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    subjectBooks.add(new SubjectBook(resultSet.getInt("id"),
                            resultSet.getInt("subject_id"), resultSet.getInt("book_id")));
                }
            }
        }
        return subjectBooks;
    }

}

