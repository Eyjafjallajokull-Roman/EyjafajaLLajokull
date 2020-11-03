package homework2.dao.imp;

import homework2.dao.BucketDao;
import homework2.dao.model.Bucket;
import homework2.jdbc.MySqlConnectorAdv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BucketDaoImp implements BucketDao {
    @Override
    public Bucket read(int id) throws SQLException {
        ResultSet resultSet = null;
        try(Connection connection = MySqlConnectorAdv.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from internet_shop.bucket where id = ?;"))
        {
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            return new Bucket(resultSet.getInt("id"), resultSet.getTimestamp("purchaseDate"));


        }finally {
            if(resultSet!= null){
                resultSet.close();
            }
        }
        return null;
    }

    @Override
    public void create(Bucket bucket) throws SQLException {
        try(Connection connection = MySqlConnectorAdv.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement
                ("insert into internet_shop.bucket(id, purchase_date) values (?,?)")) {
            preparedStatement.setInt(1,bucket.getId());
            preparedStatement.setTimestamp(2,bucket.getPurchaseDate());
            preparedStatement.execute();



        }

    }

    @Override
    public boolean delete(int id) throws SQLException {
        try(Connection connection = MySqlConnectorAdv.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from internet_shop.bucket where id = ?"))
        {
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate() > 0;

        }

    }
}
