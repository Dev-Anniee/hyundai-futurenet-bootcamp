
/*
단어 맞추기 게임
구구단 게임

문제가 출제 답을...
시간이 주어지고 일정 시간이 지나면 종료

싱글쓰레드로는 불가능 -> 멀티쓰레드 
*/

import javax.swing.JOptionPane;

public class Ex03_Word_Game {

	public static void main(String[] args) {
		
		String inputdata = JOptionPane.showInputDialog("값을 입력하세요");
		System.out.println("입력값 : " + inputdata);

		timer();
	}
	
	static void timer() {
		for(int i=10; i > 0 ;i--) {
			try {
				   System.out.println("남은시간: " + i);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
