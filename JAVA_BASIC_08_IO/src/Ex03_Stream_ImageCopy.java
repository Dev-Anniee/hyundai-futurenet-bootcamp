import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Ex03_Stream_ImageCopy {
	public static void main(String[] args) {
		String oriFile = "C:\\tmp\\1.jpg";
		String targetFile = "Copy.jpg"; //파일명 생성 - 경로가 없으면 프로젝트 폴더 아래 생성
		
		FileInputStream fs= null;
		FileOutputStream fos= null;
		
		try {
			fs = new FileInputStream(oriFile);
			fos = new FileOutputStream(targetFile);
			
			int data =0;
			while((data=fs.read()) !=-1) {
				fos.write(data); //한 byte 씩 - 비효율
			}
			System.out.println("파일이 생성...");
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			try { 
				fs.close();
				fos.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
