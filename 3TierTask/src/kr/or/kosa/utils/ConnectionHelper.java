package kr.or.kosa.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionHelper {
    public static Connection getConnection(DBType dbType) {
        Connection conn = null;
        try{
            Properties prop = new Properties();

            prop.load(
                ConnectionHelper.class
                    .getClassLoader()
                    .getResourceAsStream("kr/or/kosa/utils/properties")
            );
            switch (dbType){
                case ORACLE:
                    conn = DriverManager.getConnection(
                        prop.getProperty("oracle.url"),
                        prop.getProperty("oracle.username"),
                        prop.getProperty("oracle.password")); break;
                case MARIADB:
                    conn = DriverManager.getConnection(
                        prop.getProperty("mariadb.url"),
                        prop.getProperty("mariadb.username"),
                        prop.getProperty("mariadb.password")); break;
            }
        } catch (Exception e) {
            System.out.println("connection Factory : "+e.getMessage());
        }
        return conn;
    }

    //자원해제
    public static void close(Connection conn) {
        //DB 서버에 로그인하여 작업을 수행할 수 있는 상태를 생성
        if (conn != null) {
            try{
                conn.close();
            } catch (Exception e) {
                System.out.println("close : "+e.getMessage());
            }
        }
    }

    public static void close(ResultSet rs) {
        //데이터베이스에서 가져온 데이터 행(row)들에 접근
        if (rs != null) {
            try{
                rs.close();
            } catch (Exception e) {
                System.out.println("close : "+e.getMessage());
            }
        }
    }

    public static void close(Statement stmt) {
        //SQL 쿼리를 DB로 전달하고 실행하는 객체
        if (stmt != null) {
            try{
                stmt.close();
            } catch (Exception e) {
                System.out.println("close : "+e.getMessage());
            }
        }
    }

    public static void close(PreparedStatement pstmt) {
        //SQL 파싱 결과를 재사용
        if(pstmt != null) {
            try {
                pstmt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    //ResultSet → Statement → Connection으로 닫기
}
