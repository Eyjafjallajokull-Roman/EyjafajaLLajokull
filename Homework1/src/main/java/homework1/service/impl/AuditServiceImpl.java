package homework1.service.impl;


import homework1.dao.Audit;
import homework1.jdbc.MySqlConnector;
import homework1.service.AuditService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuditServiceImpl implements AuditService {

    @Override
    public void createAudit(Audit audit) throws SQLException, ClassNotFoundException {


        try(Connection connection = MySqlConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT  into school.audit(id,name,seats) VALUES(?,?,?)"))
        {
            preparedStatement.setInt(1,audit.getId());
            preparedStatement.setString(2,audit.getName());
            preparedStatement.setString(3,audit.getSeats());
            preparedStatement.execute();
        }


    }


}
