import java.io.DataOutputStream;
import java.io.FileOutputStream;

/*
//보조 스트림
//DataOutputStream
//DataInputStream

장점
자바 지원하는 8가지 타입 형태로 (write, read) 가능
단 조건은 DataOutputStream 쓰면 반드시 DataInputStream 사용

성적 .txt
100,99,60,50
50,55,30,100

100,99,60,50, > split(,)> array > Integer.ParseInt() 연산 -> 번거롭다 -> DataOutputStream 사용하기
*/

public class Ex13_DataOutputStream {
	public static void main(String[] args) {
		int[] score = {100,50,55,95,60};
		
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		
		try {
			fos = new FileOutputStream("score.txt"); // 생성
			dos = new DataOutputStream(fos); //생성된 파일 write
			
			for(int i=0; i<score.length; i++) {
				dos.writeInt(score[i]); //정수 형태 write
				//dos.writeUTF(null); 채팅시 한글 
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				  dos.close();
				  fos.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
	}
}
