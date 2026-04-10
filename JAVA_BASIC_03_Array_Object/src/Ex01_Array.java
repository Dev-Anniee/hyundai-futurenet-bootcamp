//Array는 객체다
import java.awt.CardLayout;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArraySet;
/*
 1. new를 통해서 생성 (주소)
2. heap 메모리 생성
3. 자바의 배열의 종료
3.1 정적 배열 : 배열의 크기가 고정되어 있다
3.2 자바는 동적 배열이 있을까요? (x) -> Collection (동적 배열처럼 쓸 수 있는..) - 동적 배열처럼 사용
*/

class Car {
    int door;
}

public class Ex01_Array {
    public static void main(String[] args) {
        int s,s2,s3,s4;
        int s5;
        int s6;
        int s7;

        // 같은 타입의 변수를 여러개 선어?

        // 배열
        int[] score = new int[5];
        // 아파트 101동 101호 102호 103호
        // 배열은 첨자로 접근 (index) : score[0],score[1]

        System.out.println(score[0]);

        score[0] = 100;
        score[1] = 200;
        //score[5] = 500; //없는 방

        for(int i=0; i<5; i++) {
            System.out.printf("%d =%d\t",i,score[i]);
        }

        //배열은 객체다
        //사용자가 만드는 모든 클래스 Object 상속
        //사용자가 만드는 배열은 Object 상속

        //JAVA API : 미리 수많은 클래스 (Math.....)

        //자바 코급이면 직접 손 코딩하기
        String resultArrayString = Arrays.toString(score);
        System.out.println(resultArrayString);
        // Java api 수많은 helper 클래스 -> Arrays

        //투데이 포인트 : 배열을 만드느 3가지
        int[] arr = new int[5];
        int[] arr2 = new int[] {10,20,30}; //초기값을 가지고 배열의 객수 정의
        int[] arr3 = {11,22,33,44,55}; // 컴파일러가 내부적으로 처리

        //javaScript 배열
        //Tip const arr = [1,2,3,4,5]; let arr = []; arr.push(10), arr.push(20), arr.pop();
        //Tip const obj = {} 객체 생성 (json)
        //javascript 동적 배열
        for (int i=0; i<arr3.length; i++){
            System.out.println(arr3[i]);
        }

        int[] arr4 = null; //arr4는 메모리를 가지고 있지 않다
        arr4 = new int[] {1,2,3,4,5};
        System.out.println(arr4);

        int[] arr5 = arr4;
        //주소값 할당 (같은 메모리 바라보다.)
        String[] strarr = new String[] {"가","나","다","라"};
//		char[]
//		float[]

        // 배열은 객체배열만 알면 개발에서 문제가 없다
        // 객체 배열은 방을 만드는 것과 방을 채우는 작업 2가지

        Car[] cars = new Car[3];
        cars[0] = new Car();
        cars[1] = new Car();
        cars[2] = new Car();

    }
}
