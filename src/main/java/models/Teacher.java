package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    public Teacher(int id, String fullName, int age, String workplace, int experience) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.workplace = workplace;
        this.experience = experience;
    }

    private int id;
    private String fullName;
    private int age;
    private String workplace;
    private int experience;
    private List<Subject> subjects;
}








