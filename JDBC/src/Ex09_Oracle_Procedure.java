/*
create or replace procedure usp_EmpList
(
  p_sal IN number,
  p_cursor OUT SYS_REFCURSOR -- APP 사용하기 타입
)
is
    begin
        open p_cursor 
        for select empno, ename ,sal from emp where sal > p_sal;
    end;

    var out_cursor REFCURSOR
    exec usp_EmpList(2000,:out_cursor)
    print out_cursor;


*/

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import kr.or.kosa.utils.ConnectionHelper;
import kr.or.kosa.utils.DBType;
import oracle.jdbc.OracleType;
import oracle.jdbc.OracleTypes;

public class Ex09_Oracle_Procedure {

	public static void main(String[] args) {
		
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		
		try {
			   conn = ConnectionHelper.getConnection(DBType.ORACLE);
			   //String sql="select "
			   String sql="{call usp_EmpList(?,?)}";
			   cstmt = conn.prepareCall(sql);
			   
			   //usp_EmpList(?,?)
			   cstmt.setInt(1, 2000);
			   cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			   
			   boolean result = cstmt.execute();
			   
			   rs = (ResultSet)cstmt.getObject(2);
			   
			   while(rs.next()) {
				   System.out.println(rs.getInt(1) + "," + rs.getString(2) +"," + rs.getInt(3));
			   }
			   
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(cstmt);
			ConnectionHelper.close(conn);
		}

	}

}
