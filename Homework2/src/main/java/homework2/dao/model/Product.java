package homework2.dao.model;

import homework2.jdbc.RandomIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;

@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

    public Product(int id, String name, String description, double price) {
        this.id = RandomIdGenerator.getRandomID();
//        System.out.println(this.id);
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public Product(String name, double price, String description) {
//        this.id = RandomIdGenerator.getRandomID();
//        System.out.println(this.id);
        this.price = price;
        this.name = name;
        this.description = description;
    }
}
