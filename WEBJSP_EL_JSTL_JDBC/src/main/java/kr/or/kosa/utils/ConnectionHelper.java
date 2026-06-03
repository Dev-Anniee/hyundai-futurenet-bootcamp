package kr.or.kosa.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionHelper {
    private static final String DEFAULT_ORACLE_URL = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    private static final String DEFAULT_ORACLE_USERNAME = "KOSA";
    private static final String DEFAULT_ORACLE_PASSWORD = "1004";

    public static Connection getConnection(DBType dbType) {
        try {
            Properties prop = loadProperties();
            if (dbType == DBType.ORACLE) {
                Class.forName("oracle.jdbc.OracleDriver");
                return DriverManager.getConnection(
                    prop.getProperty("oracle.url", DEFAULT_ORACLE_URL),
                    prop.getProperty("oracle.username", DEFAULT_ORACLE_USERNAME),
                    prop.getProperty("oracle.password", DEFAULT_ORACLE_PASSWORD)
                );
            }
        } catch (Exception e) {
            System.out.println("Connection error: " + e.getMessage());
        }
        return null;
    }

    private static Properties loadProperties() {
        Properties prop = new Properties();
        try (InputStream input = ConnectionHelper.class.getClassLoader()
            .getResourceAsStream("kr/or/kosa/utils/properties")) {
            if (input != null) {
                prop.load(input);
            }
        } catch (Exception e) {
            System.out.println("Properties load error: " + e.getMessage());
        }
        return prop;
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                System.out.println("ResultSet close error: " + e.getMessage());
            }
        }
    }

    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
                System.out.println("Statement close error: " + e.getMessage());
            }
        }
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Connection close error: " + e.getMessage());
            }
        }
    }
}
