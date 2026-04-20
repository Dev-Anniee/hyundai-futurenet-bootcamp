import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Ex12_PrintWriter_String_Finder {
	
	String baseDir = "C:\\tmp"; //내가 검색할 디랙토리
	String word = "hello"; //검색할 단어
	String savetxt= "result.txt"; //Hello 단어가 들어있는 파일 정보 저장
	
	void Find() throws IOException{
		File dir = new File(baseDir);
		
		if(!dir.isDirectory()) {
			System.out.println("유효한 폴더가 아니예요");
			System.exit(0); //강제 프로세스 종료 
		}
		PrintWriter writer = new PrintWriter(new FileWriter(savetxt));
		BufferedReader br = null;
		
		File[] files = dir.listFiles();
		
		for(int i=0; i<files.length; i++) {
			if(!files[i].isFile()) {
				continue; //아래 구문을 실행 않고 다시 for문
			}
			
			//파일이면
			br = new BufferedReader(new FileReader(files[i]));
			
			//a.txt, b.txt
			//한문장 read
			String line= "";
			while((line = br.readLine()) != null) {
				//read한 한 문장안에 특정 단어가 포함되어있는지 a.txt > 한 줄 > Hello
				if(line.indexOf(word)!=-1) {
					writer.write("word ="+files[i].getAbsolutePath()+"\n");
				}
			}writer.flush();
		}
		br.close();
		writer.close();
	}
	
	public static void main(String[] args) {
		Ex12_PrintWriter_String_Finder wordFinder = new Ex12_PrintWriter_String_Finder();
		try {
			   wordFinder.Find();   
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
