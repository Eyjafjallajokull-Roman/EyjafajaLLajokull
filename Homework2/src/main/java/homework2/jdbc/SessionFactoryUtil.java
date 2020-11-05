package homework2.jdbc;


import homework2.dao.model.Product;
import homework2.dao.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class SessionFactoryUtil {
    public  static Session createSession(){

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Product.class);
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory.openSession();
    }
}
