package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherSubject {
    private int id;
    private int teacherId;
    private int subjectId;
}

