package kr.or.kosa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/*
요청 하나마다  Controller 구성 처리 방법
1. CRUD   개수만큼 ... implements Controller  구성 매핑 단위 객체
2. @Controller 클래스는 함수 단위의 매핑 (클래스의 CRUD 함수 단위 매핑)


*/

public class IntroController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {

		ModelAndView mav = new ModelAndView();
		mav.addObject("name","hong");
		mav.setViewName("Intro");
		return mav;
	}

}
