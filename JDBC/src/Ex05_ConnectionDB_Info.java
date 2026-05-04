import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import kr.or.kosa.utils.ConnectionHelper;
import kr.or.kosa.utils.DBType;

//관리자 모드 (DB 정보) 쉽게 볼 수 있게
public class Ex05_ConnectionDB_Info {

	public static void main(String[] args) {
		String sql = "select empno, ename, sal from emp";
		try (Connection conn = ConnectionHelper.getConnection(DBType.ORACLE);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql);
			 ){
			//String sql = "select empno, ename, sal from emp"; 기준 기타 정보
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			System.out.println(columnCount);
			System.out.println("정보 출력");
			for(int i=1; i<=columnCount; i++) {
				System.out.println("컬럼번호 : "+ i);
				System.out.println("이름 : "+ metaData.getColumnName(i));
				System.out.println("라벨 : "+ metaData.getColumnLabel(i));
				System.out.println("타입 : "+ metaData.getColumnTypeName(i));
				System.out.println("크기 : "+ metaData.getColumnDisplaySize(i));
				System.out.println("Null : "+ metaData.isNullable(i));
			}
			
			System.out.println("데이터 출력");
			while(rs.next()) {
				for(int i=1; i<=columnCount; i++) {
					System.out.println(rs.getObject(i)+"\t");
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
