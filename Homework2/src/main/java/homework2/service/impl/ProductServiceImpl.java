package homework2.service.impl;



import homework2.dao.model.Product;
import homework2.exception.AlreadyExistException;
import homework2.exception.NotFoundException;
import homework2.jdbc.SessionFactoryUtil;
import homework2.service.ProductService;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class ProductServiceImpl implements ProductService {
    private Session session;

    public ProductServiceImpl() {
        this.session =  SessionFactoryUtil.createSession();
    }

    @Override
    public List<Product> readAll() throws SQLException {
        log.info("trying to read users");
        return (ArrayList<Product>)session.createQuery("select u from Product u ").list();
    }

    @Override
    public Product read(int id) throws SQLException, NotFoundException {
       log.info("trying to read product");

       return session.get(Product.class, id);
    }

    @Override
    public void create(Product product) throws SQLException, AlreadyExistException {
        log.info("try to create product");
        try {

            session.persist(product);
            session.beginTransaction().commit();
            log.info("good");

        }catch (Exception e){
            log.info("here we go again");
            session.beginTransaction().rollback();

        }


        log.info("create this product " + product.getName());

    }

    @Override
    public void delete(int id) throws SQLException, NotFoundException {
        log.info("try to delete by this id =" + id);
        try {
            Product product = read(id);

            session.delete(product);
            session.beginTransaction().commit();
            log.info("deleted");

        }catch (Exception e){
            log.info("no delete");
            session.beginTransaction().rollback();

        }

    }

    @Override
    public void update(Product current) throws SQLException, NotFoundException {
        try {
           Product product = read(current.getId());
            session.update(product);
            session.beginTransaction().commit();
            log.info("updated");

        }catch (Exception e){
            log.info("no update");
            session.beginTransaction().rollback();

        }


    }


}
