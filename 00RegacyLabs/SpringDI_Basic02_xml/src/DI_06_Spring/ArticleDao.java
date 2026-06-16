package DI_06_Spring;

//OracleDao , MySqlDao 구현
public interface ArticleDao {

	//CRUD 함수 (추상)
	
	void insert(Article article);
}
