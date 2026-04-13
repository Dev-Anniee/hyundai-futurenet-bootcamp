
public class EX07_Array_Rank {
	public static void main(String[] args) {
		/*
		다차원 배열 (2차원)
		질문 : 이차원 [행][열]
		좌석 (PC), 영화관, 엑셀, 지도의 좌표값, 오목, 바둑 ... - 행과 열로 이루어져 있다
		1차 플젝에 많이 쓸 예정
		 */
		int[][] score = new int[3][2];
		
		score[0][0] = 100;
		score[0][1] = 200;

		score[1][0] = 300;
		score[1][1] = 400;
		
		score[2][0] = 500;
		score[2][1] = 600;
		
		//중첩 for
		for(int i=0; i<score.length; i++) { //행의 개수 
			for(int j=0; j<2; j++) {
				System.out.printf("score[%d][%d] = %d ",i,j,score[i][j]);
			}System.out.println();
		}
		
		System.out.println();
		//필수
		for(int i=0; i<score.length; i++) { //행의 개수 
			for(int j=0; j<score[i].length; j++) {
				System.out.printf("score[%d][%d] = %d ",i,j,score[i][j]);
			}System.out.println();
		}
		
		System.out.println();
		int[][] score2 = new int[][] {{11,12},{13,14},{15,16}};
		//개선된 for문 사용해서 출력 (2차원)
		for(int[] arr:score2) { //배열 타입을 명시 point for(Car car:Carlist)
			for(int value: arr) {
				System.out.print(value +" ");
			}System.out.println();
		}
	}
}
