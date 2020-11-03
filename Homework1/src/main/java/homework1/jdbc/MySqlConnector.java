package homework1.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnector {
private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
private static final String url =
        "jdbc:mysql://localhost:3306/school?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";

private static final String user = "root";
private static final String password = "root";

public static Connection getConnection() throws SQLException, ClassNotFoundException {
    Class.forName(MYSQL_DRIVER);
    return DriverManager.getConnection(url,user,password);
}
}
