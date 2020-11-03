package homework2.service.impl;

import homework2.dao.UserDao;
import homework2.dao.imp.UserDaoImp;
import homework2.dao.model.User;
import homework2.exception.AlreadyExistException;
import homework2.exception.NotFoundException;
import homework2.service.UserService;
import lombok.extern.log4j.Log4j;

import java.sql.SQLException;
import java.util.List;

@Log4j
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(){
        this.userDao = new UserDaoImp();
    }



    @Override
    public List<User> readAll() throws SQLException, ClassNotFoundException {
        log.info("Trying to read all users");
        return userDao.readAll();
    }

    @Override
    public User read(int id) throws SQLException, NotFoundException, ClassNotFoundException {
        log.info("Trying to read current user");
        User user = userDao.read(id);
        if (user==null){
            throw new NotFoundException("Not found user by this id :" + id);
        }
        return user;
    }

    @Override
    public void create(User user) throws AlreadyExistException, ClassNotFoundException {
        log.info("Trying to create user");
        try
        {
            if (userDao.existByEmail(user.getEmail()))
            {
                throw new AlreadyExistException("User with email " + user.getEmail() + " already exist.");
            }

            userDao.create(user);

            log.info("New user with email " + user.getEmail() + " was create.");
        } catch (SQLException e)
        {
            log.error("Exception in SQL", e);
        }
        log.info("govno123");

    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException, NotFoundException {
      if (!userDao.delete(id))
        {
            throw new NotFoundException("User with this: " + id + " dont exist");
        }

    }

    @Override
    public User readByEmail(String email) throws SQLException, ClassNotFoundException {
        return userDao.readByEmail(email);
    }
}
