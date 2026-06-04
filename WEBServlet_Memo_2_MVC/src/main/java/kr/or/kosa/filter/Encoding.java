package kr.or.kosa.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;

/*
로그처리 
로깅처리
한글처리   
 
*/

@WebFilter(
		description = "어노테이션을 활용한 필터 적용하기", 
		urlPatterns = { "/*" }, 
		initParams = { 
				@WebInitParam(name = "encoding", value = "UTF-8", description = "인코딩방식")
		})
public class Encoding extends HttpFilter implements Filter {
       
    //개발자
	private String encoding;
	
    public Encoding() {
        super();
        // TODO Auto-generated constructor stub
    }
	public void init(FilterConfig fConfig) throws ServletException {
		this.encoding = fConfig.getInitParameter("encoding");
	}



	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//들어갈때
		if(request.getCharacterEncoding() == null) {
			request.setCharacterEncoding(this.encoding);
		}
		//모든 페이지는 필터 통과
		//UTF-8
		chain.doFilter(request, response);
		
		//나갈때
	}



}
