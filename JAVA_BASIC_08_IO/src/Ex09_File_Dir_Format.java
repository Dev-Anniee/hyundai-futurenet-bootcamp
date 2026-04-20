import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ex09_File_Dir_Format {
	public static void main(String[] args) {
		
		File dir = new File("C:\\tmp");
		File[] files = dir.listFiles();  //모든 파일과 폴더 정보 다 가지고 와서 배열에 담겠다
		
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			String name = file.getName();
			SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH-mma");
			String attribute="";
			String size="";
			
			if(files[i].isDirectory()) {
				attribute += "<DIR>";
			}else { //파일
				size = file.length() + "byte";
				attribute = file.canRead() ? "R":"";
				attribute += file.canWrite() ? "W":"";
				attribute += file.isHidden() ? "H":"";
				
			}
			
			System.out.printf("%s  %3s  %10s  %s  \n",dt.format(new Date(file.lastModified())),
					                                 attribute,
					                                 size,
					                                 name);
		}
	}
}
