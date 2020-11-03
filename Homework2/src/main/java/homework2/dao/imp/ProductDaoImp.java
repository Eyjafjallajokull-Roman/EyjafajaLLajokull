package homework2.dao.imp;

import homework2.dao.ProductDao;
import homework2.dao.model.Product;
import homework2.jdbc.MySqlConnectorAdv;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImp implements ProductDao {

    @Override
    public List<Product> readAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        try(Connection connection = MySqlConnectorAdv.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from internet_shop.product"))
        {
            while (resultSet.next()){
                products.add(new Product(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getString("description"), resultSet.getDouble("price")));
            }
            return products;

        }
    }

    @Override
    public Product read(int id) throws SQLException {
        ResultSet resultSet = null;
        try(Connection connection = MySqlConnectorAdv.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from internet_shop.product where id = ?")) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new Product(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getString("description"), resultSet.getDouble("price"));
            }
        }finally {
            if (resultSet!=null){
                resultSet.close();
            }
        }
return null;
    }

    @Override
    public void create(Product product) throws SQLException {
        try(Connection connection = MySqlConnectorAdv.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement
                ("Insert into internet_shop.product(title, description, price,id) values (?,?,?,?)"))
        {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3,product.getPrice());
            preparedStatement.setInt(4,product.getId());
            preparedStatement.execute();



        }

    }

    @Override
    public boolean delete(int id) throws SQLException {
        try(Connection connection = MySqlConnectorAdv.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE from internet_shop.product where id = ?"))
        {
           preparedStatement.setInt(1,id);
           return preparedStatement.executeUpdate() > 0;
        }

    }

    @Override
    public boolean update(Product current) throws SQLException {

        try(Connection connection = MySqlConnectorAdv.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement
                ("UPDATE internet_shop.product set  title = ?, description = ?, price = ? where id = ? "))
        {

            preparedStatement.setString(1, current.getName());
            preparedStatement.setString(2, current.getDescription());
            preparedStatement.setDouble(3,current.getPrice());
            preparedStatement.setInt(4,current.getId());
            return preparedStatement.executeUpdate() > 0;

        }

    }

    @Override
    public boolean exists(int id) throws SQLException {
        try (Connection connection = MySqlConnectorAdv.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT  1 from internet_shop.product where id = ?")) {

            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        }
    }
}
