package homework1.service.impl;

import homework1.dao.Subject;
import homework1.exception.NoSuchSubjectException;
import homework1.jdbc.MySqlConnector;
import homework1.service.SubjectService;
import lombok.extern.log4j.Log4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class SubjectServiceImpl implements SubjectService {

    @Override
    public List<Subject> getAll() throws SQLException, ClassNotFoundException {
        List<Subject> subjects = new ArrayList<>();
        try(Connection connection = MySqlConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT *  from school.subject")) {
            while (resultSet.next()){
                subjects.add(new Subject(resultSet.getInt("id"),resultSet.getString("name"),
                        resultSet.getDouble("koef"), resultSet.getInt("audit_id")));
            }


        }
        return subjects;
    }

    @Override
    public Subject getById(int id) throws SQLException, ClassNotFoundException, NoSuchSubjectException {
        ResultSet resultSet = null;
        try(Connection connection = MySqlConnector.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from school.subject where id = ?")) {

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                log.info(new Subject(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getDouble("koef"), resultSet.getInt("audit_id")));
                return new Subject(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getDouble("koef"), resultSet.getInt("audit_id"));

            }else {
                throw new NoSuchSubjectException("No such Subject by id - " + id);

            }

        }finally {
            if (resultSet != null)
            {
                resultSet.close();
            }
        }

    }

    @Override
    public boolean checkSubjectById(int id) throws SQLException, ClassNotFoundException {
        log.info(getAll().stream().anyMatch(subject -> subject.getId()==id));
        log.warn(getAll().stream().anyMatch(subject -> subject.getId()==id));
        return getAll().stream().anyMatch(subject -> subject.getId()==id);


    }

    @Override
    public void updateSubjectName(String name, String newName) throws SQLException, ClassNotFoundException {

        try(Connection connection = MySqlConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement
                ("UPDATE school.subject set name = ? where name = ?")) {
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,newName);
            System.out.println(getAll());

        }
    }

    @Override
    public void deleteSubjectById(int id) throws SQLException, ClassNotFoundException {
        try(Connection connection = MySqlConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("DELETE from school.subject  where id = ?")) {
            preparedStatement.setInt(1,id);

            System.out.println(getAll());

        }

    }


}
