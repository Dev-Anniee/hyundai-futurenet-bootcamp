/*
게임 cd
1. 설치 > C:\Temp 폴더 > 설치파일 복사
2. 복사한 파일 > 프로그램을 설치
3. 정상 설치 > C:\Temp 폴더 내용 삭제
3.1 비정상 설치 >C:\Temp 폴더 내용 삭제

강제 실행 블럭 :finally
*/

import java.io.IOException;

public class Ex03_finally {
	static void copyFiles() {
		System.out.println("copy files");
	}
	static void startInstall() {
		System.out.println("Install ...");
	} 
	static void deleteFiles() {
		System.out.println("file delete ...");
	}
	public static void main(String[] args) {
		/*
		 * copyFiles(); 
		 * startInstall(); 
		 * deleteFiles(); 개발자가 필요에 따라서 예외를 만들고 강제로 던질 수 있다 (throw)
		 */
		try {
			copyFiles();
			startInstall();
			throw new IOException("install 하는 도중에 문제가 발생 ...");
		} catch (Exception e) {
			System.out.println("예외가 발생: "+e.getMessage());
		}finally { //DB, 네트워크 작업시에 finally 를 적극적으로 사용해야 한다
			//강제 실행 블럭 
			//정상 , 비정상 무조건 실행
			deleteFiles();
		}
	}
	
}
