import java.io.File;
import java.io.FileReader;

public class Ex08_File_Dir {
	public static void main(String[] args) {
		if(args.length!=1) {
			System.out.println("사용법 : java 파일명 [디렉토리명]");
			//강제 종료 -> return
			System.exit(0); //프로그램 종료
		}
		File f = new File(args[0]);
		if(!f.exists() || !f.isDirectory()) {
			System.out.println("유효하지 않은 경로입니다");
			System.exit(0); //프로그램 종료
		}
		
		//실제 폴더가 존재하면 분석
		//Point
		File[] files = f.listFiles(); //tmp 폴더 안에 있는 파일과 폴더 다 가짐
		//[1. txt][ 기러기][참새][1.jpg]
		for(int i = 0 ; i < files.length ; i++) {
			String name = files[i].getName(); //파일 , 폴더
			System.out.println(files[i].isDirectory() ? "[DIR]" + name : name);
		}
		
	}
}
