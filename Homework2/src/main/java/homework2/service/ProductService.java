package homework2.service;

import homework2.dao.model.Product;
import homework2.exception.AlreadyExistException;
import homework2.exception.NotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    List<Product> readAll() throws SQLException;

    Product read(int id) throws SQLException, NotFoundException;

    void create(Product product) throws SQLException, AlreadyExistException;

    void delete(int id) throws SQLException, NotFoundException;

    void update(Product current) throws SQLException, NotFoundException;


}
