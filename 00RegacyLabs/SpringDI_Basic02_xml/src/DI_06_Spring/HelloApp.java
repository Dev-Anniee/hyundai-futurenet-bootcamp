package DI_06_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {
  public static void main(String[] args) {
	
	  //Controller 가정
	  
	  //ArticleService service = new ArticleService(new OracleDao());
	  /*
	  ArticleService service = new ArticleService(new MySqlDao());
	  Article article = new Article();
	  service.write(article);
	  */
	  
	  //1. 컨테이너 만들기 (spring memory)
	  //2. 다양한 방법 (컨테이너 생성될 객체를 제공 ....) xml
	  //3. 컨테이너 안에 객체를 사용하면 ... 싱글톤 
	  
	  
	  ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_06_Spring/DI_06.xml");
	  
	  ArticleService articleService =  context.getBean("articleService",ArticleService.class);
	  Article article = context.getBean("article",Article.class); //Spring Web > MVC > Parameter  자동 객체 생성
	  articleService.write(article);
	  
  }
}
