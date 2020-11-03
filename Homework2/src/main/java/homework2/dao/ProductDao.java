package homework2.dao;

import homework2.dao.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    List<Product> readAll() throws SQLException;

    Product read(int id) throws SQLException;

    void create(Product product) throws SQLException;

    boolean delete(int id) throws SQLException;

    boolean update(Product current) throws SQLException;

    boolean exists(int id) throws SQLException;
}
