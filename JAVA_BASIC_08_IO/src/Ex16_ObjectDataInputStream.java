import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import kr.or.kosa.UserInfo;

public class Ex16_ObjectDataInputStream {
	public static void main(String[] args) throws IOException {
		String filename = "UserData.txt";

		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream in = null;

		try {
			fis = new FileInputStream(filename);
			bis = new BufferedInputStream(fis);
			in = new ObjectInputStream(bis); // 역직렬화

			//복원 
			//지금은 객체 2개를 write 알고 있으니까 2번 readObject
			/*
			UserInfo r1 = (UserInfo)in.readObject();
			UserInfo r2 = (UserInfo)in.readObject();
			
			System.out.println(r1.toString());
			System.out.println(r2.toString());
			*/

			Object users = null;
			while ((users = in.readObject()) != null) {
				System.out.println(((UserInfo) users).toString());
			}

		} catch (FileNotFoundException e) {
			System.out.println("파일이 존재하지 않아요");
		} catch (EOFException e) {
			System.out.println("끝" + e.getMessage());
		} catch (IOException e) {
			System.out.println("파일을 읽을 수 없어요");
		} catch (ClassNotFoundException e) {
			System.out.println("해당 객체가 존재하지 않아요");
		} catch (Exception e) {
			System.out.println("나머지 예외");
		} finally {
			try {
				in.close();
				bis.close();
				fis.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}

/*
 * 객체를 하나만 직렬화하면 되지 않나요 여러개의 객체 데이터 >> ArrayList 담거나 HashMap 담아서 한번에 write, read
 * List 하나를 통으로 write, read
 */