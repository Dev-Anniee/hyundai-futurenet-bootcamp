package kr.or.kosa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//요청 하나당 서블릿 하나가 실행
//객체로 생성 ..
public class HelloController implements Controller {

	//  /hello.do 요청이 오면 자동으로 handleRequest 함수가 자동으로 실행 
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("HelloController 요청 실행 : handleRequest 자동 호출");
		
		/*
		 1. 한글처리
		 2. 데이터 받기
		 3. 비니지스(업무)
		 4. 데이터 담기
		 5. view 정의
		 6. forward  
		 */
		 ModelAndView mav = new ModelAndView();  //데이터 담고 뷰지정하기
		 mav.addObject("name","kosauser"); //request.setAttribute("name","kosauser")
		 mav.setViewName("Hello");
		
		 /*
		  InternalResourceViewResolver
		  	<property name="prefix">
				<value>/WEB-INF/views/</value>
			</property>
			<property name="suffix">
				<value>.jsp</value>
			</property> 
		   
		  */
		return mav;
	}

}
