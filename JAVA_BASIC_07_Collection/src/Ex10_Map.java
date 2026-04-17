import java.util.HashMap;
import java.util.Scanner;

public class Ex10_Map {

	public static void main(String[] args) {
		/*
		 * Map 구조를 갖는 데이터 집합 지역번호, 우편번호, 회원가입 (d=id, pwd)
		 * 
		 * Map의 사용사례 
		 * 주민번호 , 이름 
		 * 대화방 key, value (ArrayList Member 목록) 도
		 * 서관엥서 책 정보(ISBN, "스프링 입문")
		 */

		HashMap loginMap = new HashMap();
		loginMap.put("kim", "kim1004");
		loginMap.put("scott", "tiger");
		loginMap.put("lee", "kim1004");
		
		// 우리는 성능을 위해서 DB에서 사용자의 ID와 PWD를 추출해서 메모리에 적재 (메모리에 저장)
		// 로그인한 사용자가 회원인지?
		
//		Id (0) pwd (0)
//		Id (0) pwd (x) 비밀번호 재입력
//		Id (x) pwd (0)
//		Id (x) pwd (x)
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.print("ID : ");
			String id = scanner.nextLine().trim().toLowerCase();
			System.out.print("PWD : ");
			String pwd = scanner.nextLine().trim().toLowerCase();
			
			if(!loginMap.containsKey(id))
				System.out.println("ID 재입력");
			else {
				if(loginMap.get(id).equals(pwd)) {
					System.out.println("로그인 완료");
					break;
				}
				else {
					System.out.println("pwd 재입력");
				}
			}
		}
 		
		
	}

}
