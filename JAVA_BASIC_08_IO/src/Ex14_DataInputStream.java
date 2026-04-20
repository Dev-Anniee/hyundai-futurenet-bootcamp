import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Ex14_DataInputStream {	
	public static void main(String[] args) {
		//score.txt read 해서
		//int > int read
		
		int sum = 0;
		int score = 0;
		FileInputStream fis = null;
		DataInputStream dis = null;
		
		try {
			fis = new FileInputStream("score.txt");
			dis = new DataInputStream(fis);
			
			while(true) {
				score = dis.readInt(); //이유 writeInt
				System.out.println("score int data: "+score);
				sum+=score;
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("sum : "+sum);
		}finally {
			try {
				fis.close();
				dis.close();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
	}
}
