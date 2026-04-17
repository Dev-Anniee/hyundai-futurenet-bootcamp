/*
파일 정보, 폴더 (directory) 정보

JAVA:  File (Directory 작업까지)
C# : File,  Directory

자바 - File class (파일 작업, 폴더 작업) > 파일 생성, 수정, 삭제, 폴더 생성, 수정, 삭제)  하나의 클래스에서 수행
>>a.txt 생성, 수정, 삭제 >> 정보를 read
>> tmp 폴더 생성, 수정, 삭제 >> 정보를 read

절대 경로 : C:\ D:\
상대 경로 : ..root 현재 파일 중심으로 ../ >>/ 
*/

import java.io.File;
public class Ex07_File {
	public static void main(String[] args) { //javac... 실행할 때 사용 java Ex07_File a b c
		String path = "C:\\tmp\\file.txt";
		File f = new File(path);
		
		//File 클래스를 통해서 다양한 정보 취득
		System.out.println("파일 존재 여부 확인? : "+f.exists());
		System.out.println("너 폴더야? : "+f.isDirectory());
		System.out.println("너 파일이야? : "+f.isFile());
		System.out.println("너 파일명? : "+f.getName());
		System.out.println("절대경로?  : "+f.getAbsolutePath());
		System.out.println("파일크기 : "+f.length()+ "byte");
		System.out.println("부모경로?  : "+f.getParent());
}
}
