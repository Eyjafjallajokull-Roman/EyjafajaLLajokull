package homework2.service.impl;

import homework2.dao.UserDao;
import homework2.dao.imp.UserDaoImp;
import homework2.dao.model.User;
import homework2.exception.AlreadyExistException;
import homework2.exception.NotFoundException;
import homework2.jdbc.FactoryManager;
import homework2.service.UserService;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

@Log4j
public class UserServiceImpl implements UserService {
    EntityManager entityManager;

    public UserServiceImpl(){
        this.entityManager = FactoryManager.getEntityManager();
    }



    @Override
    public List<User> readAll() throws SQLException, ClassNotFoundException {
        log.info("Trying to read all users");
        Query query =entityManager.createQuery("select u from User u");
        return (List<User>) query.getResultList();
    }

    @Override
    public User read(int id) throws SQLException, NotFoundException, ClassNotFoundException {
        log.info("Trying to read current user");


        return entityManager.find(User.class, id);
    }

    @Override
    public void create(User user) throws AlreadyExistException, ClassNotFoundException {
        log.info("Trying to create user");
        if (!entityManager.getTransaction().isActive())
        {
            entityManager.getTransaction().begin();
        }

        entityManager.persist(user);
        entityManager.getTransaction().commit();
            log.info("New user with email " + user.getEmail() + " was create.");
        }



    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException, NotFoundException {
        if (!entityManager.getTransaction().isActive())
        {
            entityManager.getTransaction().begin();
            log.info("begin delete");
        }

        User user = this.read(id);

        entityManager.remove(user);
        log.info("deleted");
        entityManager.getTransaction().commit();

    }

    @Override
    public User readByEmail(String email) throws SQLException, ClassNotFoundException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> from = criteriaQuery.from(User.class);

        criteriaQuery.select(from);
        criteriaQuery.where(criteriaBuilder.equal(from.get("email"), email));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
