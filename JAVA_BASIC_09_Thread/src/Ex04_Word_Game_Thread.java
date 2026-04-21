/*
시간 동작 -> 별도의 쓰레드
문제 동작 -> 별도의 쓰레드
*/

import javax.swing.JOptionPane;

class WordTime extends Thread {

	@Override
	public void run() {
		for(int i=10; i > 0 ;i--) {
			if(Ex04_Word_Game_Thread.inputCheck) return;  //함수의 종료
			try {
				   System.out.println("남은시간: " + i);
				   Thread.sleep(1000); //남은시간 출력하고 휴게실로 가요 1초 쉬었다가 runnable 상태
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
}

class WordInputThread extends Thread {
	@Override
	public void run() {
		String dan="2";
		String inputdata = JOptionPane.showInputDialog(dan + " 단 값을 입력하세요");
		if(inputdata != null && !inputdata.equals("")) {
			Ex04_Word_Game_Thread.inputCheck = true;
		}
		System.out.println("입력값 : " + inputdata);
				
	}
}

public class Ex04_Word_Game_Thread {
		static boolean inputCheck =false;
	public static void main(String[] args) {
		WordTime timer = new WordTime();
		timer.start();
		
		WordInputThread wordInputThread = new WordInputThread();
		wordInputThread.start();
	
	
	/*
	 * 상태 목성, 토성, 금성까지의 거리 (하나 재고 하나 잰다 or 한 번에 잰다) 
	 * 동시에 목성, 토성, 금성 합을 구할 때 총 거리의 합을 구할 때 하나의 Thread  다른 Thread 기다린다.
	 * main -> word thread, time thread 끝난 다음에 종료
	 */
	
	try {
		timer.join(); //main thread 에게 내가 끝날 때까지 기다려 달라는 의미
		wordInputThread.join(); //main thread 에게 내가 끝날 때까지 기다려 달라는 의미
	}catch (Exception e) {
		System.out.println();
	}System.out.println("Main End");
	}
}
