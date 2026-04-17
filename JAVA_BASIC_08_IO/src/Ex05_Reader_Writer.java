import java.io.FileReader;
import java.io.FileWriter;

public class Ex05_Reader_Writer {
	public static void main(String[] args) {
		FileReader fr = null;
		FileWriter fw = null;
		
		try {
			fr = new FileReader("Ex01_Stream.java"); //read
			fw = new FileWriter  ("copy_Ex01.txt"); // FileWriter  생성자도 파일이 없으면 create
			
			int data = 0;
			while((data=fr.read())!=-1) {
				System.out.println((char)data); // 문자열은 char 배열/ char 정수 바꾸어서
				
				//엔터 , 탭 , 빈문자는 파일로 쓰지 않겠다 (압축버전)
				 if(data != '\n' && data != '\r' && data != '\t' && data != ' ') {
					 fw.write(data);
				 } // https://jquery.com/download/ 압축 (min) 버전 존재 이유
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fr.close();
				fw.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
