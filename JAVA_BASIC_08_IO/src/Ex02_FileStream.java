/*
 Byte 데이터를 File write/read (a.txt , memo.txt)
 
 FileInputStream
 FileOutputStream
 
 이미지 , 엑셀 .... read, write
 
 File> 1.txt , 2.txt 별도로 생성 ... I/O 관련 클래스 중복 기능 
 
 I/O 관리되지 않는 자원 (가비지 컬렉터의 대상이 아니다)
 
 파일 작업  ... 같은 파일 열면 ... 파일은 편집중입니다 .. 읽기전용 열겠습니까 .. >> 닫을 거야 >> close()
 
 직접 자원을 관리 (사용했으면 .. 자원 해제 (close()))
 try  catch finally { close() }
 
 
try-with-resources

선택
자동 close 처리
try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
    System.out.println(br.readLine());
}
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ex02_FileStream {
	public static void main(String[] args) {
		//File을 대상으로 read / write
		FileInputStream fs = null;
		FileOutputStream fos =null;
		
		String path = "C:\\tmp\\a.txt";
		
		try {
			fs = new FileInputStream(path);
			fos = new FileOutputStream("C:\\tmp\\new.txt");
			// "C:\\tmp\\a.txt" 내용을 write
			
			/*			
			현재 tmp 폴더 안에 new.txt가 없어요 
			파일 create new.txt
			File 클래스하는 역할
			
			FileOutPutStream*/
			// 1. new.txt 존재하지 않으면 > 자동 파일 생성 > Create
			
			// 2. fos = new FileOutStream("C:\\tmp\\new.txt", false);
			// false > overwrite
			
			// 3. fos = new FileOutStream("C:\\tmp\\new.txt", true);
			// true > append > 기존 파일 삭제 않고 내용 뒤로 추가
			
			int data =0; 
			while((data=fs.read())!= -1) {
					System.out.println("정수: "+ data+ (char)data);
					fos.write(data); // 한 byte 씩 라이트 할 예정 (성능에 안 좋다)
			}
		}catch (Exception e) {
		    	   e.printStackTrace();
		}finally{
			//정상 종료, 비정상 종료
			//강제 실행 블록
			//자원 해체
			// method를 강제 종료 return > 걸려도 finally 먼저 선행 자원해제 
			
			try {
				   fs.close();
				   fos.close();
			   } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			   }
		}
	}
}
