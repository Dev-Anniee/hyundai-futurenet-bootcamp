package kr.or.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import jakarta.servlet.http.HttpServletRequest;

import javax.sql.DataSource;

import kr.or.bit.dto.Board;
import kr.or.bit.dto.Reply;

public class BoardDao {
	DataSource ds = null;
	
	public BoardDao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	public int writeok(Board boarddata) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		try {
			conn = ds.getConnection();
			String sql="insert into jspboard(idx, writer, pwd, subject, content, email, homepage, writedate, readnum,filename,filesize,refer)"+ 
					   " values(jspboard_idx.nextval,?,?,?,?,?,?,sysdate,0,?,0,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boarddata.getWriter());
			pstmt.setString(2, boarddata.getPwd());
			pstmt.setString(3, boarddata.getSubject());
			pstmt.setString(4, boarddata.getContent());
			pstmt.setString(5, boarddata.getEmail());
			pstmt.setString(6, boarddata.getHomepage());
			pstmt.setString(7, boarddata.getFilename());
			
			
			int refermax = getMaxRefer();
			int refer = refermax + 1;
			pstmt.setInt(8,refer);
			
			row = pstmt.executeUpdate();
			
		}catch(Exception e) {
			
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
			
			}
		}
		
		
		return row;
	}

	private int getMaxRefer() {
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		int refer_max=0;
		try {
			conn = ds.getConnection();
			String sql="select nvl(max(refer),0) from jspboard";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				refer_max = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return refer_max;
		
	}

	public List<Board> list(int cpage , int pagesize){







		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list = null;
		try {
			conn = ds.getConnection();
			String sql = "select * from " +
			                           "(select rownum rn,idx,writer,email,homepage,pwd,subject , content, writedate, readnum " +
				                       ",filename,filesize,refer,depth,step " +
			                           " from ( SELECT * FROM jspboard ORDER BY refer DESC , step ASC ) "+
				                       " where rownum <= ?" +
				         ") where rn >= ?";
			pstmt = conn.prepareStatement(sql);
			int start = cpage * pagesize - (pagesize -1);
			int end = cpage * pagesize;
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<Board>();
			while(rs.next()) {
				Board board = new Board();
				board.setIdx(rs.getInt("idx"));
				board.setSubject(rs.getString("subject"));
				board.setWriter(rs.getString("writer"));
				board.setWritedate(rs.getDate("writedate"));
				board.setReadnum(rs.getInt("readnum"));
				
				board.setRefer(rs.getInt("refer"));
				board.setStep(rs.getInt("step"));
				board.setDepth(rs.getInt("depth"));
				
				list.add(board);
			}
			
		}catch (Exception e) {
			System.out.println("?ㅻ쪟 :" + e.getMessage());
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (Exception e2) {
				
			}
		}
			
		return list;
	}
	
	public int totalBoardCount() {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int totalcount = 0;
			try {
				conn = ds.getConnection();
				String sql="select count(*) cnt from jspboard";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					totalcount = rs.getInt("cnt");
				}
			}catch (Exception e) {
				
			}finally {
				try {
					pstmt.close();
					rs.close();
					conn.close();
				}catch (Exception e) {
					
				}
			}
			return totalcount;
		}

	public Board getContent(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board= null;
		
		try {
			conn = ds.getConnection();
			String sql="select * from jspboard where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String writer = rs.getString("writer");
				String email = rs.getString("email");
				String homepage = rs.getString("homepage");
				String pwd = rs.getString("pwd");
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				String filename = rs.getString("filename");
				
				java.sql.Date writedate = rs.getDate("writedate");
				int readnum = rs.getInt("readnum");
				int filesize = rs.getInt("filesize");
				
				int refer = rs.getInt("refer");
				int step = rs.getInt("step");
				int depth = rs.getInt("depth");
				
				board = new Board(idx, writer, pwd, subject, content, writedate, readnum, filename, filesize, homepage, email, refer, depth, step);
			}
			
		} catch (Exception e) {
			System.out.println("content: " + e.getMessage());
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (Exception e2) {
				
			}
		}
		
		return board;
	}

	public boolean getReadNum(String idx) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			boolean result = false;
			try {
				conn = ds.getConnection();
				String sql="update jspboard set readnum = readnum + 1 where idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, idx);
				
				int row = pstmt.executeUpdate();
				if(row > 0 ) {
					result = true;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					pstmt.close();
					conn.close();
				}catch (Exception e) {
					
				}
			}
			return result;
		}

	public int deleteOk(String idx , String pwd) {
		

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		try {
				conn = ds.getConnection();
				
				String sql_pwd="select pwd from jspboard where idx=?";
				
				String sql_reply = "delete from reply where idx_fk=?";
				
				String sql_board="delete from jspboard where idx=?";
				
				pstmt = conn.prepareStatement(sql_pwd);
				pstmt.setString(1, idx);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					 if(pwd.equals(rs.getString("pwd"))) {
						 conn.setAutoCommit(false);
						 	pstmt = conn.prepareStatement(sql_reply);
						 	pstmt.setString(1,idx);
						 	pstmt.executeUpdate();
						 	
						 	pstmt = conn.prepareStatement(sql_board);
						 	pstmt.setString(1,idx);
						 	row = pstmt.executeUpdate();
						 	
						 	if(row > 0) {
						 		conn.commit();
						 	}
	
					 }else {
						  row = -1;
					 }
				}else {
					row = 0;					
				}
				
				
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (Exception e2) {
				
			}
		}
		return row;
	}

	public int replywrite(int idx_fk , String writer , String userid, String content,String pwd) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			int row = 0;
			try {
				conn = ds.getConnection();
				String sql="insert into reply(no,writer,userid,content,pwd,idx_fk) "+
				           " values(reply_no.nextval,?,?,?,?,?)";
				pstmt =conn.prepareStatement(sql);
				pstmt.setString(1, writer);
				pstmt.setString(2, userid);
				pstmt.setString(3,content);
				pstmt.setString(4, pwd);
				pstmt.setInt(5, idx_fk);
				
				row = pstmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					pstmt.close();
					conn.close();
				}catch (Exception e) {
					
				}
			}
			
			return row;
		}
		
	public List<Reply> replylist(String idx_fk){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<Reply> list = null;
			
			try {
				conn = ds.getConnection();
				String reply_sql = "select * from reply where idx_fk=? order by no desc";
				
				pstmt = conn.prepareStatement(reply_sql);
				pstmt.setString(1, idx_fk);
				
				rs =pstmt.executeQuery();
				
				list = new ArrayList<>();
				while(rs.next()) {
					int no = Integer.parseInt(rs.getString("no"));
					String writer = rs.getString("writer");
					String userid = rs.getString("userid");
					String pwd = rs.getString("pwd");
					String content  =rs.getString("content");
					java.sql.Date writedate = rs.getDate("writedate");
					int idx = Integer.parseInt(rs.getString("idx_fk"));
					
					Reply replydto = new Reply(no, writer, userid, pwd, content, writedate, idx);
					list.add(replydto);
				}
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					pstmt.close();
					rs.close();
					conn.close();
				}catch (Exception e) {
					
				}
			}
			
			return list;
		}
		
	public int replyDelete(String no , String pwd) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int row = 0;
			
			try {
				
				String replyselect = "select pwd from reply where no=?";
				String replydelete = "delete from reply where no=?";
				
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(replyselect);
				pstmt.setString(1, no);
				rs =pstmt.executeQuery();
				if(rs.next()) {
					String dbpwd = rs.getString("pwd");
					if(pwd.equals(dbpwd)){
						pstmt.close();
						pstmt = conn.prepareStatement(replydelete);
						pstmt.setString(1, no);
						row = pstmt.executeUpdate();
					}else {
						row = 0;
					}
				}else {
					row =-1;
				}
			}catch(Exception e) {
				
			}finally {
				try {
					pstmt.close();
					rs.close();
					conn.close();
				}catch (Exception e) {
					
				}
			}
			
			return row;
		}

	public int reWriteOk(Board boardata) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			
			int idx = boardata.getIdx();
			
			String writer = boardata.getWriter();
			String email = boardata.getEmail();
			String homepage = boardata.getHomepage();
			String pwd = boardata.getPwd();
			String subject = boardata.getSubject();
			String content = boardata.getContent();
			String filename = boardata.getFilename();
			int filesize = 0;
			
			String refer_depth_step_sal ="select refer , depth , step from jspboard where idx=?";
			
			String step_update_sql = "update jspboard set step= step+1 where step  > ? and refer =? ";
			
			String rewrite_sql="insert into jspboard(idx,writer,pwd,subject,content,email,homepage,writedate,readnum,filename,filesize,refer,depth,step)" + 
				    		   " values(jspboard_idx.nextval,?,?,?,?,?,?,sysdate,0,?,0,?,?,?)";
			
			pstmt = conn.prepareStatement(refer_depth_step_sal);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int refer = rs.getInt("refer");
				int step = rs.getInt("step");
				int depth = rs.getInt("depth");
				
				pstmt = conn.prepareStatement(step_update_sql);
				pstmt.setInt(1, step);
				pstmt.setInt(2, refer);
				pstmt.executeUpdate();

				pstmt = conn.prepareStatement(rewrite_sql);
				pstmt.setString(1, writer);
				pstmt.setString(2, pwd);
				pstmt.setString(3, subject);
				pstmt.setString(4, content);
				pstmt.setString(5, email);
				pstmt.setString(6, homepage);
				pstmt.setString(7, filename);
				
				pstmt.setInt(8, refer);
				pstmt.setInt(9, depth+1);
				pstmt.setInt(10, step+1);
				
				int row = pstmt.executeUpdate();
				if(row > 0) {
					result = row;
				}else {
					result = -1;
				}

			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return result;
	}

	public Board getEditContent(String idx) {
		return this.getContent(Integer.parseInt(idx));
	}
	
	public int boardEdit(HttpServletRequest boarddata) {
		String idx= boarddata.getParameter("idx");
		String pwd= boarddata.getParameter("pwd");
		String writer= boarddata.getParameter("writer");
		String email= boarddata.getParameter("email");
		String homepage= boarddata.getParameter("homepage");
		String subject= boarddata.getParameter("subject");
		String content= boarddata.getParameter("content");
		String filename= boarddata.getParameter("filename");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql_idx = "select idx  from jspboard where idx=? and pwd=?";
			String sql_udpate = "update jspboard set writer=? , email=? , homepage=? ,"+
			                    " subject=? , content=? , filename=? where idx=?";
			pstmt = conn.prepareStatement(sql_idx);
			pstmt.setString(1, idx);
			pstmt.setString(2, pwd);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pstmt.close();
				pstmt = conn.prepareStatement(sql_udpate);
				pstmt.setString(1, writer);
				pstmt.setString(2, email);
				pstmt.setString(3, homepage);
				pstmt.setString(4, subject);
				pstmt.setString(5, content);
				pstmt.setString(6, filename);
				pstmt.setString(7, idx);
				row = pstmt.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (Exception e2) {
				
			}
		}
	
		return row;
	}
}

	






