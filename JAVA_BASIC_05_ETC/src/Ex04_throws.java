import java.io.IOException;

import kr.or.kosa.utils.Exclass;

public class Ex04_throws {
	public static void main(String[] args) {
		/*
		try {
			ExClass exClass = new ExClass("C:\\Temp");
		} catch (NullPointerException | IOException e) {
			e.printStackTrace();
		}
		*/
        /*
		try {
			ExClass exClass = new ExClass("C:\\Temp");
		} catch (Exception e) {  모든 처리 다해요
			e.printStackTrace();
		} catch (IOException e) {  IOException 의미가 없어요
			e.printStackTrace();
		}
		*/

		try {Exclass exclass = new Exclass("C:\\Temp");
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {  
			e.printStackTrace();
		}
	}
}
