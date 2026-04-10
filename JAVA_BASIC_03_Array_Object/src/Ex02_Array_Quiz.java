import java.security.spec.ECPrivateKeySpec;

public class Ex02_Array_Quiz {
    public static void main(String[] args) {

//		1. 제어문을 통해서 max라는 변수에 시험 점수 중에서 가장 큰 최대값을 담고
//		min이라는 변수에는 시험점수 중에서 가장 작은 최소값을 담자
//		결과론적으로 max=97, min 65
//		단 for문은 한번만 사용해서 max와 min

        int[] score = new int[] {79,88,97,65,95};
        int max = score[0];
        int min = score[0];

        for(int i=0; i<score.length; i++) {
            max = (max<score[i])?score[i]:max;
            if(min>score[i])
                min = score[i];
        }
        System.out.println(max +" "+min);


        //2.10개의 방의 값을 1부터 10까지 초기화 및 출력
        int[] numbers = new int[10];
        for(int i=0; i<numbers.length; i++) {
            numbers[i] = i+1;
            System.out.print(numbers[i]+" ");
        }

        System.out.println();

        //3. 어느 학생의 기말고사 시험 점수 5과목
        int[] jumsu = {100,55,90,60,78};
        int sum =0;
        float avg = 0f;
        //총과목수, 과목의합, 과목의 평균 2,3은 하나의 for 문 이용
        for(int i=0; i<jumsu.length; i++) {
            sum +=jumsu[i];
            if(i==jumsu.length-1) { //중요한 코드
                avg =sum/(float)jumsu.length;
            }
        }
        System.out.println("과목수 : " +jumsu.length + " 합: " + sum + " 평균 "+ avg );
    }
}
