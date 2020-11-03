package homework2.dao.imp;


import homework2.dao.UserDao;
import homework2.dao.model.User;
import homework2.jdbc.MySqlConnectorAdv;
import lombok.extern.log4j.Log4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class UserDaoImp implements UserDao {
    @Override
    public List<User> readAll() throws SQLException {
        List<User> users = new ArrayList<>();
        try(Connection connection = MySqlConnectorAdv.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from internet_shop.users")) {
            while (resultSet.next()){
                users.add(new User(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("email"),resultSet.getString("password"),
                        resultSet.getString("role")));
            }

            return users;

        }
    }

    @Override
    public User read(int id) throws SQLException {
        ResultSet resultSet = null;

        try(Connection connection = MySqlConnectorAdv.getConnection();
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * from internet_shop.users where id = ?"))
        {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new User(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("email"), resultSet.getString("password"),
                        resultSet.getString("role"));
            }
        } finally {
            if (resultSet!=null){
                resultSet.close();
            }
        }
        return null;
    }

    @Override
    public void create(User user) throws SQLException {
        try(Connection connection = MySqlConnectorAdv.getConnection();
        PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT into internet_shop.users(name, email, password, role) values(?,?,?,?)"))
            {
                log.info(user.getName());
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getEmail());
                log.info(user.getEmail());
                preparedStatement.setString(3, user.getPassword());
                log.info(user.getPassword());
                preparedStatement.setString(4, user.getRole());
                log.info(user.getRole());
                preparedStatement.execute();


        }

    }

    @Override
    public boolean delete(int id) throws SQLException {
        try(Connection connection = MySqlConnectorAdv.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM internet_shop.users WHERE id = ?"))
        {
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate() > 0;

        }

    }


    @Override
    public User readByEmail(String email) throws SQLException  {
        ResultSet resultSet = null;

        try(Connection connection = MySqlConnectorAdv.getConnection();
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * from internet_shop.users where email = ?"))
        {
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
           while (resultSet.next()) {
               return new User(resultSet.getInt("id"), resultSet.getString("name"),
                       resultSet.getString("email"), resultSet.getString("password"),
                       resultSet.getString("role"));
           }
        } finally {
            if (resultSet!=null){
                resultSet.close();
            }
        }
        return null;
    }
    @Override
    public boolean existsById(int id) throws SQLException {
        ResultSet resultSet = null;
        try(Connection connection = MySqlConnectorAdv.getConnection();
        PreparedStatement preparedStatement = connection
                .prepareStatement("select 1 from internet_shop.users where id = ?"))
        {
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();


            return resultSet.next();
        }finally {
            if (resultSet!=null){
                resultSet.close();
            }
        }
    }

    @Override
    public boolean existByEmail(String email) throws SQLException  {
        ResultSet resultSet = null;
        try(Connection connection = MySqlConnectorAdv.getConnection();
        PreparedStatement preparedStatement = connection
                .prepareStatement("select 1 from internet_shop.users where email = ?"))
        {
            preparedStatement.setString(1,email);
            resultSet = preparedStatement.executeQuery();


            return resultSet.next();
        }finally {
            if (resultSet!=null){
                resultSet.close();
            }
        }
    }
}
