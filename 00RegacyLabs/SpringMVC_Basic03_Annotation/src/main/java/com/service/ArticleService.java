package com.service;

import org.springframework.stereotype.Service;

import com.model.NewArticleCommand;

/*
@Service
public class ArticleService

@Service 있다고 IOC 빈객체로 생성 안되요

1. <bean class="ArticleService"
2. <context:component-scan base-package="com.service"></context:component-scan>
   빈객체 자동 생성....
*/

public class ArticleService {
	public ArticleService() {
		System.out.println("서비스 생성자 호출");
	}
	
	public void writeArticle(NewArticleCommand command) {
		//DAO 있다고 가정
		//DAO dao = new DAO();  dao.insert(command)
		System.out.println("글쓰기 작업 완료 : " + command.toString());
	}
}
