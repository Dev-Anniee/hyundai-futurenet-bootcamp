/*
Map 인터페이스 구현한 클래스
특수한 목적 <String, String> :key, value 타입이 고정 (String)

사용목적 1. APP 전체에서 사용되는 자원 관리
2. 관리자 EMAIL, 파일 업로드 경로 > 미리 생성하고 이용하도록 제공
3. WEB > Web.xml > 관리
4. 환경변수 (전역)
*/

import java.util.Properties;;

public class Ex11_Properties {
	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("admin","kosa@or.kr");
		prop.setProperty("version", "1.x.x.x");
		prop.setProperty("downpath", "C:\\Temp\\images");
		
		System.out.println(prop.getProperty("admin"));
		System.out.println(prop.getProperty("downpath"));
	}
}
