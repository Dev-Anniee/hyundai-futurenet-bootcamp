
public class Ex02_Exception {
	public static void main(String[] args) {
		System.out.println("main start");
		
		int num =100;
		int result =0;
		//의심되면 무조건 예외 처리
		try {
			for(int i=0; i<10; i++) {
				result = num/ (int)(Math.random()*10);
				System.out.println(result + i);
			}

		}catch (Exception e) {
			System.out.println("문제 발생");
			System.out.println("원인 :" + e.getMessage());
		}
		System.out.println("main end");
	
	/*
	 * for(int i=0; i<10; i++) { result = num / (int)(Math.random()*10); //0~9 난수
	 * System.out.println("result: " + result + "i: "+i); }
	 * System.out.println("main end");
	 */
	}
}
