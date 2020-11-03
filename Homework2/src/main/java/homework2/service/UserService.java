package homework2.service;

import homework2.dao.model.User;
import homework2.exception.AlreadyExistException;
import homework2.exception.NotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface UserService  {
    List<User> readAll() throws ClassNotFoundException, SQLException;
    User read(int id) throws SQLException, NotFoundException, ClassNotFoundException;

    void create(User user) throws SQLException, AlreadyExistException, ClassNotFoundException;

    void delete(int id) throws SQLException, ClassNotFoundException, NotFoundException;

    User readByEmail(String email) throws SQLException, ClassNotFoundException;
}
