/*
File 처리 대상 -> DISK > 입출력 단위 한 Byte 씩

파일 크기가 크다면 IO 성능 문제 발생
버퍼 -> 메모리
1. 디스크 I/O 발생하는 접근 횟수를 줄이자
2. Line 단위 엔터를 기준으로 한 줄

비유
학생 한명 한명을 목적지에 보내는 것이 아니라 bus (Buffer) 한 번에 다 태워서 목적지 가서 내리겠다

웹 (WAS) 자원을 가지고 있다 -> 클라이언트 요청 > 자원 > Buffer 담는다 (8k) > 클라이언트 (버퍼 차면 flush() or close())
우리가 사용하는 버퍼도 같다 

사용하는 방법 
Buffer 보조 Stream
 주 Stream 없으면 ... 보조 Stream도 의미가 없다
 
 설계 관점에서 봐보자
*/

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.Flushable;

public class Ex04_Stream_Buffer {
	public static void main(String[] args) {
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
	
		try {
			fos = new FileOutputStream("data.txt"); //생성된 파일이 없으면 default에서 파일이 생성
			bos = new BufferedOutputStream(fos);  //BufferedOutputStream은 FileOutputStream을 도와준다
			
			for(int i=0; i<10; i++) {
				bos.write('A');
			} 
			//bos.flush();
		    // Buffer는 다 차면 ...flush() >> close() 자동으로 호출
			/*
			 * java Buffer (8k, 8192byte) 
			 * 1. Buffer 안에 내용이 채워지면 스스로 내보내고 (버퍼를 비워요) 
			 * 2. 강제로 비우는 방법 (Flush()) 자원 해제 Close() > 내부적으로 flush() 
			 * 3. Finally {close()를 사용하면 문제 없다}
			 */
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally {
			
			try {
				 bos.close();
				 fos.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
