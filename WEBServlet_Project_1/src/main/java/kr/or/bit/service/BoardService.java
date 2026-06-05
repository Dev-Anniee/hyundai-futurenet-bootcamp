package kr.or.bit.service;

import java.util.List;

import javax.naming.NamingException;
import jakarta.servlet.http.HttpServletRequest;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;
import kr.or.bit.dto.Reply;

public class BoardService {
	private static BoardService instance = new BoardService();
	private BoardService() {}
	public static BoardService getInBoardService() {
		return instance; 
	}
	
		public int writeOk(Board boarddata) throws Exception {
			BoardDao dao = new BoardDao();
			int result = dao.writeok(boarddata);
			return result;
		}
		
		public List<Board> list(int cpage, int pagesize) throws Exception{
			BoardDao dao = new BoardDao();
			return dao.list(cpage, pagesize);
		}
		
		public int totalBoardCount() throws Exception {
			BoardDao dao = new BoardDao();
			return dao.totalBoardCount();
		}
		
		public Board content(int idx) throws NamingException {
			return new BoardDao().getContent(idx);
		}
		
		public boolean  addReadNum(String idx) throws NamingException {
			return new BoardDao().getReadNum(idx);
		}
		
		public int board_Delete(String idx , String pwd) throws NamingException {
			return new BoardDao().deleteOk(idx, pwd);
		}
		
		public int replyWrite(int idx_fk,String writer,String userid, String content,String pwd) throws NamingException {
			return new BoardDao().replywrite(idx_fk, writer, userid, content, pwd);
		}
		
		public List<Reply> replyList(String idx_fk) throws NamingException{
			return new BoardDao().replylist(idx_fk);
		}
		
		public int replyDelete(String no, String pwd) throws NamingException {
			return new BoardDao().replyDelete(no, pwd);
		}
		
		public int rewriteok(Board boardata) throws Exception {
			return new BoardDao().reWriteOk(boardata);
		}
		
		public Board board_EditContent(String idx) throws NamingException {
			return new BoardDao().getEditContent(idx);
		}
		
		public int board_Edit(HttpServletRequest req) throws NamingException {
			
			return new BoardDao().boardEdit(req);
		}
	
}







