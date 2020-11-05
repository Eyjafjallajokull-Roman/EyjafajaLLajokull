package homework2.jdbc;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class FactoryManager {
    public static EntityManager getEntityManager(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("AppStorePersistence");

        return factory.createEntityManager();
    }
}
