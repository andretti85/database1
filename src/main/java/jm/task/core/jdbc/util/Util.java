package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static Util util;
    private Util() {}

    /*public synchronized Util getUtil() {
        if (util == null) {
            util = new Util();
        }
        return util;
    }*/
    public static Connection getMySQLConnection() throws SQLException,
            ClassNotFoundException {
        String hostName = "localhost";
        String dbName = "alfa";
        String userName = "root";
        String password = "1234qwer";
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;


       Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);

        return conn;

    }

}
