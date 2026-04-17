import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Set;
import java.util.TreeSet;

public class Lotto_Task1 {
	public static void main(String[] args) {
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		
		try {
			fos = new FileOutputStream("lotto1.txt");
			bos = new BufferedOutputStream(fos);  
			
			for(int i=0; i<5; i++) {
				Set<Integer> lotto = new TreeSet<Integer>();
				String header = (i + 1) + "회차: ";
                bos.write(header.getBytes());
                
				for(int j=0; j<6; j++) {
					lotto.add((int)(Math.random()*45+1));
				}

				for(Integer num : lotto) {
				    String str = num + " ";
				    bos.write(str.getBytes()); 
				}bos.write("\n".getBytes());
			}
			
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
