package homework2.dao;

import homework2.dao.model.User;
import homework2.exception.AlreadyExistException;
import homework2.exception.NotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    List<User> readAll() throws SQLException, ClassNotFoundException;
    User read(int id) throws SQLException, ClassNotFoundException, NotFoundException;
    void create(User user) throws SQLException, ClassNotFoundException, AlreadyExistException;
    boolean delete(int id) throws SQLException, ClassNotFoundException;
    boolean existsById(int id) throws SQLException, ClassNotFoundException;
    boolean existByEmail(String email) throws SQLException, ClassNotFoundException;
    User readByEmail(String email) throws SQLException, ClassNotFoundException;

}
