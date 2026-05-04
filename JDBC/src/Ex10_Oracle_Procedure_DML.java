/*
 
  1. JDBC auto_commit  DML , procedure 안에 DML  auto-commit
  
  2. procedure  (commit 을 두는 경우) JDBC   쓰지 않고 DB에서 독자적으로 사용시
     exec usp_Insert_Emp  (commit , rollback)
  
create or replace procedure usp_Insert_Emp
(
   vempno IN emp.empno%TYPE,
   vename IN emp.ename%TYPE,
   vjob   IN emp.job%TYPE,
   p_outmsg OUT varchar2
)
is 
    begin
         insert into emp(empno,ename,job) values(vempno,vename,vjob);
         commit;
         p_outmsg :='success'; --할당은 이모티콘 :=
         EXCEPTION WHEN OtHERS THEN
         p_outmsg :=SQLERRM;
         rollback;
    end;
    
  alter table emp
  add constraint pk_emp_empno primary key(empno);

  select * from user_constraints where table_name ='EMP';
*/

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import kr.or.kosa.utils.ConnectionHelper;
import kr.or.kosa.utils.DBType;

public class Ex10_Oracle_Procedure_DML {

	public static void main(String[] args) {
		
		Connection conn = null;
		CallableStatement cstmt= null;
		
		try {
			   conn = ConnectionHelper.getConnection(DBType.ORACLE);
			   String sql="{call usp_Insert_Emp(?,?,?,?)}";
			   cstmt = conn.prepareCall(sql);
			   
			   //3개 input
			   cstmt.setInt(1, 9999);
			   cstmt.setString(2, "홍길동");
			   cstmt.setString(3, "IT");
			   cstmt.registerOutParameter(4, Types.VARCHAR); // p_outmsg OUT varchar2
			   
			   cstmt.execute();
			   
			   String msg = (String)cstmt.getObject(4);
			   
			   System.out.println("DB MSG : " + msg);
			   
			   //1개 output
		} catch (Exception e) {
			 System.out.println(e.getMessage());
		}finally {
			ConnectionHelper.close(cstmt);
			ConnectionHelper.close(conn);
		}

	}

}
