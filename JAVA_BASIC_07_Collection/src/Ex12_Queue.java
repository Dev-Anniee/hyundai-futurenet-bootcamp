import java.util.LinkedList;
import java.util.Queue;

import kr.or.kosa.Message;

public class Ex12_Queue {
	public static void main(String[] args) {
		/*
		 * FIFO 선입선출 
		 * Java API : Stack 
		 * Queue 인터페이스 구현한 클래스
		 *
		 * 예시
		 * 학사관리 수강신청
		 * 공용프린터 출력 요청 먼저 온 것 먼저 처리
		 */	
		
		Queue<Message> messageQueue = new LinkedList<Message>();
		messageQueue.offer(new Message("sendMail", "홍길동"));
		messageQueue.offer(new Message("sendSMS", "김유신"));
		messageQueue.offer(new Message("sendKaTalk", "이순신"));
		
		while(!messageQueue.isEmpty()) {
			Message message = messageQueue.poll();
			switch (message.getCommand()) {
			
			case "sendMail":System.out.println(message.getTo() +"님에게 메일을 보냄");
				break;
			case "sendSMS":System.out.println(message.getTo() +"님에게 SMS을 보냄");
			break;
			case "sendKaTalk":System.out.println(message.getTo() +"님에게 카톡을 보냄");
			break;
			}
		}
	}
}
