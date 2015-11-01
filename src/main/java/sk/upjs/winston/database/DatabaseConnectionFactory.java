package sk.upjs.winston.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by stefan on 2/15/15.
 */
public class DatabaseConnectionFactory {
    private static final boolean PRODUCTION_ENVIRONMENT = true;
    //static reference to itself
    private static DatabaseConnectionFactory instance = new DatabaseConnectionFactory();
    // JDBC driver name and database URL
    static final String TEST_JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String TEST_DB_URL = "jdbc:mysql://db4free.org/winstondb?useUnicode=yes&characterEncoding=UTF-8";
    static final String TEST_USER = "winston";
    static final String TEST_PASS = "PRe-U9T-kKm-hPK";

    static final String PROD_JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String PROD_DB_URL = "jdbc:mysql://localhost/winston?useUnicode=yes&characterEncoding=UTF-8";
    static final String PROD_USER = "bocko";
    static final String PROD_PASS = "Stefan123";

    static String JDBC_DRIVER;
    static String DB_URL;
    static String USER;
    static String PASS;

    //private constructor
    private DatabaseConnectionFactory() {
        if (PRODUCTION_ENVIRONMENT) {
            JDBC_DRIVER = PROD_JDBC_DRIVER;
            DB_URL = PROD_DB_URL;
            USER = PROD_USER;
            PASS = PROD_PASS;
        } else {
            JDBC_DRIVER = TEST_JDBC_DRIVER;
            DB_URL = TEST_DB_URL;
            USER = TEST_USER;
            PASS = TEST_PASS;
        }
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }

    public static Connection getConnection() {
        return instance.createConnection();
    }
}
