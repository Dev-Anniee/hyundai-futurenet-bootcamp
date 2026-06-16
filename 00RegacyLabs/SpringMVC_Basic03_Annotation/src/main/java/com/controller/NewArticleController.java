package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.NewArticleCommand;
import com.service.ArticleService;

@Controller
@RequestMapping("/article/newArticle.do")
public class NewArticleController {

	//NewArticleController 는 ArticleService에 의존합니다 (나는 너의 주소가 필요해)
	//연관관계 : member field ArticleService
	//DI (constructor injection , setter injection)
	// ArticleService articleService bean  객체 존재
	
	private ArticleService articleService;
	
	@Autowired
	public void NewArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	//http://localhost:8080/SpringMVC_Basic03_Annotation/article/newArticle.do
	
	//요청 GET
	@GetMapping
	public String form() { //화면처리
		
		/*
		 public ModelAndView form(){
		 	ModelAndView mv = new ModelAndView();
		 	mv.setViewName="hello.jsp"
		 	return mv
		 } 
		 규칙 : 함수의 return type String 이면 리턴값을 view 주소 
		  
		 */
		return "article/newArticleForm";
		//  /WEB-INF/views/ + article/newArticleForm + .jsp
	}
	
	
	//요청 POST
	@PostMapping
	public String submit(NewArticleCommand command) {  //로직처리 
		System.out.println("POST 처리");
		System.out.println("command :" + command.toString());
		this.articleService.writeArticle(command);
		
		/*
		public String submit(HttpServletRequest request) 
		생략 ...
		
		
		1. NewArticleCommand article = new NewArticleCommand();
		
		2. 자동 setter 불러지고 ....
		article.setParentId( Integer.parseInt(request.getParameter("parentId")));
		article.setTitle(request.getParameter("title"));
		article.setContent(request.getParameter("content"));
		
		
		3. 자동화
		ModelAndView mv = new ModelAndView();
	    mv.addObject("newArticleCommand", article);  //request.setAttribute("newArticleCommand", article)
	    mv.setViewName("article/newArticleSubmitted");
		
		4. return 
		*/
		
		//spring view 데이터 보내요 ... 
		//NewArticleCommand > newArticleCommand 자동 생성 
		//mv.addObject("newArticleCommand", article); 자동 
		
		/*
		  DAO > DB작업 ....
		  데이터 담고
		  뷰를 지정
		  리턴  
		  
		  view 데이터를 받아서 출력 ...
		 */
		
		return "article/newArticleSubmitted";
	}
}

/*
호랑이 담배 피던 시절에 했던 코드 .... HttpServletRequest request >> spring 고민 고민 ....
@PostMapping  //5.x.x
public ModelAndView sumbit(HttpServletRequest request) { //처리
	System.out.println("POST 처리해주세요");

	NewArticleCommand article = new NewArticleCommand();
	article.setParentId( Integer.parseInt(request.getParameter("parentId")));
	article.setTitle(request.getParameter("title"));
	article.setContent(request.getParameter("content"));


	this.articleService.writeArticle(article);
	ModelAndView mv = new ModelAndView();
	mv.addObject("newArticleCommand", article);  //request.setAttribute("newArticleCommand", article)
	mv.setViewName("article/newArticleSubmitted");


	return mv;

*/

/*

1. 전통적인 방법
public ModelAndView searchExternal(HttpServletRequest request) {
String id= request.getParameter("id")
}

2. DTO 객체를 통한 전달 방법(게시판, 회원가입 데이터) ^^
public ModelAndView searchExternal(MemberDto member){}

public String searchExternal(MemberDto member){

 return String view 주소 전달
 return String view (데이터 출력) > 페이지 > forward > memberDto 객체 만들어 > 자도 forward

 /search/external.do?id=hong&name=김유신&age=100
 2.1 DTO 있는 member field 이름이 >>
 private String id;
 private String name;

}

약속 : return String .. Model 만들면 데이터 전달
      return String  .. 화면 UI


3. 가장 만만한 방법
public ModelAndView searchExternal(String id, String name , int age){
  /search/external.do?id=hong&name=김유신&age=100
  ** 각각의 parameter 에 자동 매핑
}

4. @RequestParam  annotation 사용하기
4.1 유효성 처리
4.2 기본값 처리


5.REST 방식 (비동기 처리) method= GET , POST , PUT , DELETE
@PathVariable >>  /member/{memberid} >>  /member/100

100 추출해서 parameter  사용
*/








