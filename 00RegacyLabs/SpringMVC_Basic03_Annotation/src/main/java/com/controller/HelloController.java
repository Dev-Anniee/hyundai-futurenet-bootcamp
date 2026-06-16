package com.controller;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
요청 하나당  HelloController  implements Controller
public class HelloController  implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

기존 : <bean id="/hello.do" class="kr.or.kosa.HelloController"></bean>


@Controller 클래스 안에서 함수 단위의 매핑
*/

@Controller
public class HelloController {
	
	public HelloController() {
		System.out.println("HelloController 생성자 호출");
	}
	
	//게시판 CRUD 관련 함수를 만들어서 함수 단위 URL 매핑
	@RequestMapping("/hello.do")
	public ModelAndView hello() {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("greeting",getGreeting());
		mv.setViewName("Hello");  // /WEB-INF/views/ + Hello + .jsp 
		return mv;
	}
	
	private String getGreeting() {
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		String data="";
		if(hour >= 6 && hour <= 10) {
			data ="학습시간";
		}else if (hour >= 11 && hour <= 13) {
			data ="배고픈시간";
		}else if(hour >= 14 && hour <= 18) {
			data ="졸려운시간";
		}else {
			data="go home";
		}
		return data;
	}
}







