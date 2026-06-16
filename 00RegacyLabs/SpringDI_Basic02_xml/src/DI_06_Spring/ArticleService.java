package DI_06_Spring;


//ArticleService 는 ArticleDao 의존한다(연관관계) 복합연관
public class ArticleService {
	
	private ArticleDao articleDao;

	public ArticleService(ArticleDao articleDao) { //인터페이스 , 다형성 > 결합 > 스프링
		this.articleDao = articleDao;
		System.out.println("ArticleService 생성자 호출");
	}
	
	
	//서비스 (DAO 같은 의미의 함수...)
	//서비스 (암호화 , 다른 DAO 아닌)
	
	//글쓰기 서비스
	public void write(Article article) {
		this.articleDao.insert(article);
	}
	
}
