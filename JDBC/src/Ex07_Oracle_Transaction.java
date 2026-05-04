/*
		create table trans_A(
    		num number,
    		name varchar2(20)
		);

		create table trans_B(
    		num number constraint pk_trans_B_num primary key,
    		name varchar2(20)
		);
	
	JDBC >> DML >> auto commit >> 실반영
	
	JDBC >> autocommit >> 변경(false) >> 개발자 직접 (commit , rollback) >> 주의 (반드시... commit , rollback 강제)
	
	은행업무 :   A계좌 B계좌 이체 
	쇼핑몰 포인트 : 게시글을 쓰면 회원에게 포인트 부여 (insert , update) 
	쇼핑몰 결제 처리 : 카드 ... 벤더 승인 ... 되면 카트 구매 ... (update , .....)
	
	OLTP 환경 (실시간 데이터 처리) > Back End (트랜잭션 구현 필수.....)
	
	
	*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.or.kosa.utils.ConnectionHelper;
import kr.or.kosa.utils.DBType;

public class Ex07_Oracle_Transaction {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		conn = ConnectionHelper.getConnection(DBType.ORACLE);
		
		String sql ="insert into trans_A(num,name) values(100,'A')";
		String sql2="insert into trans_B(num,name) values(100,'B')";
		
		//두개의 작업을 하나의 논리적인 작업으로 처리
		//둘다 성공 아니면 둘다 실패
		
		try {
			
				conn.setAutoCommit(false); //반드시 개발자 (commit, rollback) 강제
				//begin
					pstmt = conn.prepareStatement(sql);
					pstmt2 = conn.prepareStatement(sql2);
					
					pstmt.executeUpdate();
					pstmt2.executeUpdate();
				
					//여기까지 코드가 오면
					//둘다 문제가 없구나
					//실반영
					conn.commit();
				
				//end
			
		} catch (Exception e) {
			//	pstmt.executeUpdate();
			//  pstmt2.executeUpdate();
			//  실행되다가 예외가 발생 ... PK위반
			System.out.println("예외발생 : " + e.getMessage());
			
			try {
					conn.rollback();
			} catch (SQLException e1) {
					e1.printStackTrace();
			}
		}finally {
			//예외가 발생하던 , 발생하지 않던 강제실행
			ConnectionHelper.close(pstmt2);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
	}
}
