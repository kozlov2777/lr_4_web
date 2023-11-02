package models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private int id;
    private String name;
    private String link;
    private double cost;
    private Date startDate;
    private int subjectId;
}
