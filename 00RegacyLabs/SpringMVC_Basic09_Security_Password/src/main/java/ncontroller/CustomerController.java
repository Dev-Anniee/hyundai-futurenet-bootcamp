package ncontroller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import service.CustomerService;
import vo.Notice;

@Controller
@RequestMapping("/customer/")  //부분 경로 설정 가능 
public class CustomerController {
	
	
	private CustomerService customerService;
	
	@Autowired	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	
	
	@RequestMapping("notice.do")   
	public String notices(String pg , String f , String q , Model model) {
	    List<Notice> list= customerService.notices(pg, f, q);		
		model.addAttribute("list", list); 
		return "customer/notice";
	}
	
	@RequestMapping("noticeDetail.do") 
	public String noticesDetail(String seq , Model model) {
		Notice  notice =  customerService.noticesDetail(seq);
		model.addAttribute("notice", notice); 
		return "customer/noticeDetail";
	}
	
	@GetMapping(value="noticeReg.do")
	public String noticeReg() {
		return "customer/noticeReg";
	}
	
	
	@PostMapping(value="noticeReg.do")  
	public String noticeReg(Notice n , HttpServletRequest request ,Principal principal) {
		 String url = "redirect:notice.do"; // 예외가 발생했을때 화면처리 .. 게시판 화면
		 
		 try {
			    url = customerService.noticeReg(n, request ,principal);
			    
		} catch (Exception e) {
			    e.printStackTrace();
			    //service 던지 예외 .....
		}
		 
		 return url;
		 
	}
	
	
	@GetMapping(value="noticeEdit.do") //수정하기 화면 (데이터)
	public String noticeEdit(String seq, Model model) {
		
		Notice  notice = null;
		
		try {
			  notice = customerService.noticeEdit(seq);
		} catch (Exception e ) {
			  e.printStackTrace();
		} 
		
		model.addAttribute("notice", notice); 		
		return "customer/noticeEdit";
	
	}
	
	
	@PostMapping("noticeEdit.do")  //update 
	public String noticeEdit(Notice n , HttpServletRequest request) {
		 return customerService.noticeEdit(n, request);
	}

	@GetMapping("noticeDel.do")
	public String noticeDel(String seq) {
		return customerService.noticeDel(seq);
	}

	//파일 다운로드
	@RequestMapping("download.do")
	public void download(String p , String f , HttpServletRequest request , HttpServletResponse response) throws IOException {
		customerService.download(p, f, request, response);
	}
}
 