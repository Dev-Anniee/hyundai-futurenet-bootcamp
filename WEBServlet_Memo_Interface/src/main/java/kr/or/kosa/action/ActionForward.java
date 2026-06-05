package kr.or.kosa.action;

import lombok.Getter;
import lombok.Setter;

/*
 클라이언트 -> 서버 요청
 
 1. 화면
 2. 처리
 

*/
@Getter
@Setter
public class ActionForward {
	private boolean isRedirect = false; //뷰의 전환
	private String path=null;  //이동 경로 (forward 되는 주소) viewpage
}
