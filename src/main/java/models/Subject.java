package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    private int id;
    private String name;
    private List<Teacher> teachers;
    private List<Book> books;
}
