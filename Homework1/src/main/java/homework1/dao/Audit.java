package homework1.dao;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Audit {
    private int id;
    private String name;
    private String seats;
}