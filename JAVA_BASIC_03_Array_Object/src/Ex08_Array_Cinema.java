import java.nio.file.attribute.FileOwnerAttributeView;
import java.util.Iterator;
import java.util.Random;

public class Ex08_Array_Cinema {
	public static void main(String[] args) {
		String[][] seat = new String[3][5];
		Random r = new Random();
		System.out.println(r.nextInt());
		System.out.println(r.nextInt(8)); //0~7사이의 정수
		System.out.println(r.nextInt(8)+1); //1~8사이의 정수
		
		//예매번호
		String ran = Integer.toString(r.nextInt(8)+1);
		for(int i=0; i<7; i++) {
			ran += Integer.toString(r.nextInt(8)+1);
		}
		
		//UUID
		System.out.println("예매번호: "+ ran);
//		[  ][  ][  ][  ][  ][  ]
//		[  ][  ][  ][  ][  ][  ]
//		[  ][  ][  ][  ][  ][  ]
		
		//좌석 초기화
		for(int i=0; i<seat.length; i++) {
			for(int j=0; j<seat[i].length; j++) {
				seat[i][j] = "--";
			}
		}
		
		seat[2][1] = "홍길동";
		seat[0][0] = "김유신";
		seat[2][2] = ran;
		
		//예매 현황
		for (int i = 0; i < seat.length; i++) {
			for (int j = 0; j < seat[i].length; j++) {
				System.out.printf("[%s]",  seat[i][j].equals("--")?"좌석":seat[i][j]);
			}System.out.println();
		}
		
		//예매 가능 여부 조회
		int row, col; // 고객으로 부터 행과 열 
		row =1; col =1;
		if(seat[row][col].equals("--")){
			System.out.println("예매 가능해요");
		}else {System.out.println("이미 예약된 좌석입니다");}
		
		//예매 종료
		for (int i = 0; i < seat.length; i++) {
			for (int j = 0; j < seat[i].length; j++) {
				System.out.printf("[%s]",  seat[i][j].equals("--")?"좌석":seat[i][j]);
			}System.out.println();
		}
	}
}
