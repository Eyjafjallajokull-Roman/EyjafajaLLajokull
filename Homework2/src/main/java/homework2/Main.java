package homework2;

import homework2.dao.model.Account;
import homework2.dao.model.PhoneNumber;
import homework2.dao.model.Product;
import homework2.dao.model.User;
import homework2.exception.AlreadyExistException;
import homework2.exception.NotFoundException;
import homework2.jdbc.SessionFactoryUtil;
import homework2.service.UserService;
import homework2.service.impl.UserServiceImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, AlreadyExistException, NotFoundException {
//        UserService userService = new UserServiceImpl();
//
//        System.out.println(userService.readAll());
////        User user = new User("Ivan","baran1@gmail.com","password", "user");
////        userService.create(user);
//        userService.delete(2);

        //@OnetoOne
        Session session = SessionFactoryUtil.createSession();
//        Transaction transaction = session.beginTransaction();
//        Product product = new Product();
//        product.setPrice(350.00);
//        product.setName("Title");
//        product.setDescription("not nuu");
//        User user = new User();
//        user.setName("Bogdan");
//        user.setEmail("dsada@ggmail.com");
//        user.setPassword("12345678");
//        user.setRole("user");
//        user.setProduct(product);
//
//        session.save(user);
//        session.save(product);
//
//        transaction.commit();
//
        Query getUser = session.createQuery("from User c where c.name = :name");
        getUser.setParameter("name", "Bogdan");
        User retrieved = (User) getUser.getSingleResult();

        System.out.println(retrieved.getName());
//        System.out.println(retrieved.getProduct().getName());

        Account account1 = new Account();
        account1.setName("1");
        account1.setUser(retrieved);
        Account account2 = new Account();
        account2.setName("2");
        account2.setUser(retrieved);
        Account account3 = new Account();
        account3.setName("3");
        account3.setUser(retrieved);

//        retrieved.setAccounts(new HashSet<>(Arrays.asList(account1,account2, account3)));
//        Transaction transactio1 = session.beginTransaction();
//        session.save(account1);
//        session.save(account2);
//        session.save(account3);
//        transactio1.commit();

        PhoneNumber phoneNumber1 = new PhoneNumber();
        phoneNumber1.setNumber("32121421");
        phoneNumber1.setAccounts(Arrays.asList(account1,account2));

        PhoneNumber phoneNumber2 = new PhoneNumber();
        phoneNumber2.setNumber("3987421421");
        phoneNumber2.setAccounts(Arrays.asList(account3,account2));

        PhoneNumber phoneNumber3 = new PhoneNumber();
        phoneNumber3.setNumber("4325431421");
        phoneNumber3.setAccounts(Arrays.asList(account1,account3));

        Transaction transaction2 = session.beginTransaction();
        session.save(phoneNumber1);
        session.save(phoneNumber2);
        session.save(phoneNumber3);
        transaction2.commit();
    }
}
