import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class Ex10_Lotto {

	public static void main(String[] args) {
		 try (BufferedWriter bw = new BufferedWriter(new FileWriter("lotto.txt"))) {

	            for (int round = 1; round <= 5; round++) {

	                Set<Integer> lotto = new TreeSet<>();

	                while (lotto.size() < 6) {
	                    int num = (int)(Math.random() * 45 + 1);
	                    lotto.add(num);
	                }

	                // 출력 문자열 생성
	                StringBuilder sb = new StringBuilder();
	                sb.append(round).append("회차 : ");

	                for (int n : lotto) {
	                    sb.append(n).append(" ");
	                }

	                // 파일에 쓰기
	                bw.write(sb.toString());
	                bw.newLine();
	            }

	            System.out.println("파일 저장 완료!");

	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally {
				//bw.close()
			}

	}

}
