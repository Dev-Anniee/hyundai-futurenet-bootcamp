/*
I/O 보조 스트림
출력 형식을 정의하는 보조 스트림
1. printf
2. String.format
3. I/O PrintWriter (파일에 예쁘게 형식 맞춰서 작성)

현업 : 세금계산서, 지출결의서, 품의서 (휴가원, 결제)
돈이 없다 :html 다 그림을 그린다 -> 비효율
돈이 있다 : 오즈, 크리스탈 레포트

PrinteWrite pw = new PrintWriter("C:\\tmp\\home.txt")
pw.println("****************************************")
pw.println("****************************************")
*/

import java.io.PrintWriter;

public class Ex11_PrintWriter {
	public static void main(String[] args) {
		PrintWriter pw = null;
		try {
		pw = new PrintWriter("C:\\tmp\\homework.txt");
		pw.println("****************************************");
		pw.println("***             HOMEWORK             ***");
		pw.printf("%3s : %5d  %5d  %5d  %5.1f", "홍길동",100,88,90,(float)((100+88+90)/3));
		pw.println();
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			pw.close();
		}
	}
}
