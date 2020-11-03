package homework2.dao.model;

import homework2.jdbc.RandomIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private String description;
    private double price;


    public Product(String name, double price, String description) {
        this.id = RandomIdGenerator.getRandomID();
//        System.out.println(this.id);
        this.price = price;
        this.name = name;
        this.description = description;
    }
}
