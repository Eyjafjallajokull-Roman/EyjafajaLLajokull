package homework2.service.impl;


import homework2.dao.model.User;
import homework2.exception.AlreadyExistException;
import homework2.exception.NotFoundException;
import homework2.jdbc.SessionFactoryUtil;
import homework2.service.UserService;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

@Log4j
public class UserServiceImpl implements UserService {
    Session session;

    public UserServiceImpl()
    {
        this.session = SessionFactoryUtil.createSession();
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    public List<User> readAll() throws SQLException
    {
        log.info("Trying to get all users");
        return session.createQuery("SELECT u FROM User u").list();
    }

    @Override
    public User read(int id) throws NotFoundException, SQLException
    {
        log.info("Trying to get user");
        return session.get(User.class, id);
    }

    @Override
    public void create(User user) throws AlreadyExistException
    {
        log.info("Trying to create user");

        Transaction transaction = session.beginTransaction();

        try
        {
            session.persist(user);
            transaction.commit();
        } catch (Exception e)
        {
            transaction.rollback();
        }

        log.info("New user with email " + user.getEmail() + " was created.");
    }

    @Override
    public void delete(int id) throws NotFoundException, SQLException
    {
        Transaction transaction = session.beginTransaction();

        try
        {
            User user = this.read(id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e)
        {
            transaction.rollback();
        }
    }

    @Override
    public User readByEmail(String email) throws SQLException
    {
        Query query = session.createQuery("FROM User u WHERE u.email = :email");
        query.setParameter("email", email);
        return (User) query.getSingleResult();


    }
}
