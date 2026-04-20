/*
객체 통신
직렬화 
현실적으로 파일 기반으로 
a.txt > 데이터를 (객체) >write (직렬화) > 탱크 분해
a.txt read > read(역직렬화) > 탱크 복원

객체 통신
프로세스, 네트워크, 파일 간 통신
직렬화, 역직렬화를 통해서 객체 단위의 통신 (탱크 만들어서 분해해서 보내고 받아서 조립해서 탱크 복원 가능)
직렬화 : 객체를 분해해서 줄을 세워 보내는 과정
역직렬화 : 객체를 다시 조립해서 원본을 복원하는 과정

모든 자원은 직렬화가 가능한가? -> X

클래스 만들때 부터 (직렬화 가능한 클래스)라고 명시
import java.io.Serializable;
class Test implements Serializable{}
*/

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import kr.or.kosa.UserInfo;

public class Ex15_ObjectDataOutputStream {
	public static void main(String[] args) {
		String filename = "UserData.txt";
		//직렬화 데이터 write 
		FileOutputStream fos = null; //주 스트림
		BufferedOutputStream bos = null; //성능 보조
		ObjectOutputStream out = null; //직렬화를 지원하는 보조

		try {
			fos = new FileOutputStream(filename);
			bos = new BufferedOutputStream(fos);
			//직렬화
			out = new ObjectOutputStream(bos);
			
			//객체를 생성 
			UserInfo u1 = new UserInfo("홍길동", "super", 100); //완제품
		    UserInfo u2 = new UserInfo("scott", "tiger", 50); //완제품
		    
		    //직렬화
		    out.writeObject(u1); //객체를 줄을 세워서 하나씩 write
		    out.writeObject(u2); //END
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				out.close();
				bos.close();
				fos.close();
				System.out.println("파일생성> buffer> 직렬화 > write");
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println(e2.toString());
			}
		}


	}
}
