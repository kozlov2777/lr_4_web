package dao;

import models.Book;
import models.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BookDAO {
    private final Connection connection;

    public BookDAO(Connection connection) {
        this.connection = connection;
    }

    public void createBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getName());
            statement.executeUpdate();
        }
    }

    public Book getBookById(int id) throws SQLException {
        String sql = "SELECT * FROM books WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Book(resultSet.getInt("id"), resultSet.getString("name"), null);
                }
                return null;
            }
        }
    }

    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                books.add(new Book(resultSet.getInt("id"), resultSet.getString("name"), null));
            }
        }
        return books;
    }

    public void updateBook(Book book) throws SQLException {
        String sql = "UPDATE books SET name=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getName());
            statement.setInt(2, book.getId());
            statement.executeUpdate();
        }
    }

    public void deleteBook(int id) throws SQLException {
        String sql = "DELETE FROM books WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public List<Teacher> findTeachersWithMaxExperienceByBookId(int bookId) throws SQLException {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT t.id, t.full_name, t.age, t.workplace, t.experience " +
                "FROM teachers t " +
                "INNER JOIN teacher_subjects ts ON t.id = ts.teacherId " +
                "INNER JOIN subjects s ON ts.subjectId = s.id " +
                "INNER JOIN subject_books sb ON s.id = sb.subjectId " +
                "WHERE sb.bookId = ? " +
                "AND t.experience = (SELECT MAX(experience) FROM teachers)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String fullName = resultSet.getString("full_name");
                int age = resultSet.getInt("age");
                String workplace = resultSet.getString("workplace");
                int experience = resultSet.getInt("experience");

                Teacher teacher = new Teacher(id, fullName, age, workplace, experience);
                teachers.add(teacher);
            }
        }

        return teachers;
    }
}

