package homework2.service.impl;

import homework2.dao.ProductDao;
import homework2.dao.imp.ProductDaoImp;
import homework2.dao.model.Product;
import homework2.exception.AlreadyExistException;
import homework2.exception.NotFoundException;
import homework2.service.ProductService;
import lombok.extern.log4j.Log4j;

import java.sql.SQLException;
import java.util.List;

@Log4j
public class ProductServiceImpl implements ProductService {
    private  ProductDao productDao;

    public ProductServiceImpl() {
        this.productDao = new ProductDaoImp();
    }

    @Override
    public List<Product> readAll() throws SQLException {
        log.info("trying to read users");
        return productDao.readAll();
    }

    @Override
    public Product read(int id) throws SQLException, NotFoundException {
       log.info("trying to read user");
       Product product = productDao.read(id);
       if (product==null){
           throw new NotFoundException("Product don`t exist by this id");
       }
       return product;
    }

    @Override
    public void create(Product product) throws SQLException, AlreadyExistException {
        log.info("try to create product");
        if (productDao.exists(product.getId())){
            throw new AlreadyExistException("This product already exist");
        }
        productDao.create(product);
        log.info("create this product " + product.getName());

    }

    @Override
    public void delete(int id) throws SQLException, NotFoundException {
        log.info("try to delete by this id =" + id);
        if (!productDao.delete(id)){
            throw new NotFoundException("No product by this id");
        }
    }

    @Override
    public void update(Product current) throws SQLException, NotFoundException {
        log.info("trying to update product");
        if (productDao.update(current)){
            log.info("successful update");
        } else {
            throw new NotFoundException("Not found product");
        }


    }


}
