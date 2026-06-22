package service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import dao.NoticeDao;
import vo.Notice;

@Service
public class CustomerService {

	//CustomerService 는 SqlSessionTemplate 에 의존합니다
	
	private SqlSession sqlSession;

	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//DAO 와 거의 비슷한 코드 ....
	
	//글 목록보기 서비스 
	public List<Notice> notices(String pg , String f , String q) {
		
		int page = 1;
		String field="TITLE";
		String query = "%%";
		
		if(pg != null   && ! pg.equals("")) {
			page  = Integer.parseInt(pg);
		}
		
		if(f != null   && ! f.equals("")) {
			field = f;
		}
		
		if(q != null   && ! q.equals("")) {
			query  = q;
		}
		
		//DAO 작업
		//DAO 작업  예외 throws ClassNotFoundException, SQLException
		List<Notice> list = null;
		try {
				//list = noticedao.getNotices(page, field, query);  기존코드
			    
			    //변경 코드 (mybatis 사용) 
			    NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
			    //마치 인터페이스를 그냥 사용하면 되는 것처럼 편하게 사용
			    list = noticeDao.getNotices(page, field, query);
			    
				
		} catch (ClassNotFoundException e) {
					e.printStackTrace();
		} catch (SQLException e) {
					e.printStackTrace();
		}
	
				
		return list;
		
	}

	//글 상세보기 서비스
	public Notice noticesDetail(String seq) {
		
		Notice  notice = null;
		
		try {
			 
			 NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class); //추가
			 notice = noticeDao.getNotice(seq);
		
		} catch (ClassNotFoundException e ) {
				e.printStackTrace();
		} catch (SQLException e) {
				e.printStackTrace();
		}
				
		return notice;
	}

	//글 쓰기 서비스
	//글쓴이 Point 증가 서비스
	//@Transactional
	public String noticeReg(Notice n, HttpServletRequest request , Principal principal) throws Exception {

		List<CommonsMultipartFile> files = n.getFiles(); //private List<CommonsMultipartFile> files;
		List<String> filenames = new ArrayList<String>(); // 파일명들 관리

		if (files != null && files.size() > 0) { // 1개라도 업로드 된 파일이 있다면
			for (CommonsMultipartFile multifile : files) {
				String filename = multifile.getOriginalFilename();
				String path = request.getServletContext().getRealPath("/customer/upload"); // 배포된 서버 경로
				String fpath = path + "\\" + filename;
				System.out.println(fpath);

				if (!filename.equals("")) { // 실제 파일 업로드
					FileOutputStream fs = null;
					try {
						fs = new FileOutputStream(fpath);
						fs.write(multifile.getBytes());

						// 파일 이름 추출 (DB insert)
						filenames.add(filename);
						//

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						try {
							fs.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		///////////////////////////////////////////////////////////
		//spring security 가지고 있는 인증정보 얻어오기
		// 로그인ID , 권한
		//     /login 요청 처리는 spring 담당하고 처리 (인증된 사용자 정보 저장 관리)
		
		/*
	    SecurityContext context = SecurityContextHolder.getContext(); //모든 시큐리티 정보를 가지고 와서
	    Authentication auth= context.getAuthentication(); //인증관련된 것만 추출
		UserDetails userinfo = (UserDetails)auth.getPrincipal();
		
		System.out.println("권한정보 : " + userinfo.getAuthorities()); // 인증된 사용자의 권한 정보들 
		System.out.println("사용자ID : " + userinfo.getUsername()); // 인증된 사용자 ID
		n.setWriter(userinfo.getUsername()); //글쓴이가 인증된 사용자 ID
		*/
		
		//public String noticeReg(Notice n, HttpServletRequest request , Principal principal) 
		//함수의 parameter :  Principal principal 인증 되면 객체 받아줍니다
		n.setWriter(principal.getName()); //글쓴이가 인증된 사용자 ID 
		
		//////////////////////////////////////////////////////////
		
		
		
		//n.setFileSrc(filenames.get(0));
		//n.setFileSrc2(filenames.get(1));
		if (filenames.size() > 0) {
		    n.setFileSrc(filenames.get(0));
		}
		if (filenames.size() > 1) {
		    n.setFileSrc2(filenames.get(1));
		}
		if (filenames.size() == 0) {
		    n.setFileSrc("");
		    n.setFileSrc2("");
		}
		if (filenames.size() == 1) {
		    n.setFileSrc2("");
		}
		try {
			////////////////////////////////////////////////////////////
			NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
			////////////////////////////////////////////////////////////
			/*
				DB작업
				noticeDao.insert(n) : insert
				noticeDao.updateOfMemberPoint("admin") : update
				
				1. @Transactional >> tx:annotation-driven 통해서 ...
				2. 둘다 성공(commit) , 둘중에 하나만 실패 (rollback)
				3. 처리하는 다양한 방법중 default 예외 발생하면 > rollback 
			    4. transaction-manager="transactionManager" 감시하고 있다가 예외가 발생하면 
			       모든 DML rollback .....
			
			*/
			noticeDao.insert(n); // DB insert
			//noticeDao.updateOfMemberPoint("admin"); //DB member Point >  update 
			
			//catch  빠지지 않고 이분 까지 도달하면 
			System.out.println("정상 : notices : insert , member : update > commit");
			
		} catch (Exception e) {
			//e.printStackTrace();
			//member check 제약 문제로 예외가 발생하면
			System.out.println("transaction 문제 발생 " + e.getMessage());
			
			//서비스를 실행한 주체가  Controller 에게 예외를 던지기 
			//public String noticeReg(Notice n, HttpServletRequest request) throws Exception
			throw e;
		}

		return "redirect:notice.do";
	}

	//글 수정하기 서비스
	public Notice noticeEdit(String seq) {
		
		Notice  notice = null;
		
		try {
			  NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class); //추가
			  notice = noticeDao.getNotice(seq);
			  
		} catch (ClassNotFoundException e ) {
				e.printStackTrace();
		} catch (SQLException e) {
				e.printStackTrace();
		}

		return notice;
	
	}

    //글 수정하기 처리 서비스
	public String noticeEdit(Notice n , HttpServletRequest request) {
		  //파일 업로드 가능
		List<CommonsMultipartFile> files = n.getFiles();
		List<String> filenames = new ArrayList<String>(); //파일명들 관리
		
		if(files != null && files.size() > 0) { //1개라도 업로드 된 파일이 있다면
			for(CommonsMultipartFile multifile : files) {
				 	String filename =multifile.getOriginalFilename();
					String path = request.getServletContext().getRealPath("/customer/upload"); //배포된 서버 경로 
					String fpath = path + "\\" + filename;
					System.out.println(fpath);
					
				if(!filename.equals("")) { //실제 파일 업로드 
					FileOutputStream fs =null;
					try {
						     fs = new FileOutputStream(fpath);
						     fs.write(multifile.getBytes());
						     
						     //파일 이름 추출  (DB insert)
						     filenames.add(filename); 
						     //
						     
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						 try {
							fs.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
			   
		
		//파일명 (DTO)
		//List<String> filenames = new ArrayList<String>();  
		//[0] > 1.jpg
		//[1] > 2.jpg
		//n.setFileSrc(filenames.get(0));
		//n.setFileSrc2(filenames.get(1));
		// 파일명 설정 안전 처리
		if (filenames.size() > 0) {
		    n.setFileSrc(filenames.get(0));
		}
		if (filenames.size() > 1) {
		    n.setFileSrc2(filenames.get(1));
		}
		if (filenames.size() == 0) {
		    n.setFileSrc("");
		    n.setFileSrc2("");
		}
		if (filenames.size() == 1) {
		    n.setFileSrc2("");
		}
	
		try {
				
				////////////////////////////////////////////////////////////
			    NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class); //추가
				////////////////////////////////////////////////////////////
			    System.out.println(n.toString());
			    noticeDao.update(n);  //DB update
			} catch (Exception e) {
				e.printStackTrace();
			} 
	//처리가 끝나면 상세 페이지로 : redirect  글번호를 가지고 ....
	return "redirect:noticeDetail.do?seq="+n.getSeq();    //서버에게 새 요청 ....
	}
	
	//글 삭제하기 서비스
	public String noticeDel(String seq) {
		
		 NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class); //추가\
		 
		 try {
			    noticeDao.delete(seq);
		} catch (Exception e) {
			    e.printStackTrace();
		}
		
		return "redirect:notice.do";
		
	}
	
	// 파일 다운로드 서비스 함수
	public void download(String p, String f, HttpServletRequest request, HttpServletResponse response)
				throws IOException {

					String fname = new String(f.getBytes("euc-kr"), "8859_1");
					response.setHeader("Content-Disposition", "attachment;filename=" + fname + ";");

					String fullpath = request.getServletContext().getRealPath("/customer/" + p + "/" + f);
					System.out.println(fullpath);
					FileInputStream fin = new FileInputStream(fullpath);

					ServletOutputStream sout = response.getOutputStream();
					byte[] buf = new byte[1024]; // 전체를 다읽지 않고 1204byte씩 읽어서
					int size = 0;
					while ((size = fin.read(buf, 0, buf.length)) != -1) {
						sout.write(buf, 0, size);
					}
					fin.close();
					sout.close();
	}
}
