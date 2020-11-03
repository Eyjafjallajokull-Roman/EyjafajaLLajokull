package homework2.jdbc;

import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Log4j
public class MySqlConnectorAdv {
private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
private static final String url =
        "jdbc:mysql://localhost:3306/school?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";

private static final String user = "root";
private static final String password = "root";

public static Connection getConnection(){
    Connection connection = null;
    try {
        Class.forName(MYSQL_DRIVER);
        connection = DriverManager.getConnection(url,user,password);
    }catch (ClassNotFoundException | SQLException e){
        log.error(e.getMessage(), e);
    }
    return connection;

}
}
