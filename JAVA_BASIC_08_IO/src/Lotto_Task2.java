import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Set;
import java.util.TreeSet;

public class Lotto_Task2 {
	public static void main(String[] args) {
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		int count =5;

		try {
			fos = new FileOutputStream("lotto2.txt");
			PrintWriter pw = new PrintWriter(fos);

			for(int i = 0; i < count; i++) {
				Set<Integer> lotto = new TreeSet<>();
				while(lotto.size() < 6) {
					lotto.add((int)(Math.random() * 45 + 1));
				}
				pw.println((i + 1) + "회차: " + lotto.toString());
				pw.flush();
			}
			pw.close();
			
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
