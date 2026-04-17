import java.io.BufferedReader;
import java.io.FileReader;

public class Ex06_Reader_Writer_Buffer {
	public static void main(String[] args) {
		
		FileReader fr = null;
		BufferedReader br = null;
		//BufferedReader  Line 단위 처리
		
		try {
				fr = new FileReader("Ex01_Stream.java");
				br = new BufferedReader(fr);
				
				//line 단위처리
				String line="";
				for(int i = 0 ; (line = br.readLine()) != null; i++) {
					//System.out.println(line);
					if(line.indexOf(";") != -1) { //";" 붙힌 줄 출력
						System.out.println(line);
					}
				}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				  br.close();
				  fr.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
