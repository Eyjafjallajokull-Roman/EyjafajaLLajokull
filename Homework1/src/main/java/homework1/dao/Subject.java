package homework1.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Subject {
    private int id;
    private String name;
    private double koef;
    private int audit_id;
}
