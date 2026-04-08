package practice;

/*
   클래스 == 설계도 == 데이터 타입(여러개의 작은 타입)

   1. 하나의 물리적인 java 파일은 여러개의 클래스를 가질 수 있다  (0) 그런데 실무 (x) 연습용


 */

class Apt extends Object {
    //설계도
    //설계도 구체화 되지 않으면 의미가 없다 (종이) > 구체화 (memory) > new 연산자
    //목적 : 재사용을 통해서 동일한 객체 생성
    //재사용성

    String doorColor;
}

public class Ex04_Object {

    public static void main(String[] args) {

        Apt apt = new Apt();
        System.out.println(apt); // toString()이 생략된 것, 아래와 같음
        System.out.println(apt.toString());

        Apt apt2 = new Apt();

        // apt  와 apt2 다른 주소를 가지고 있는 것을 증명
        System.out.println(apt == apt2); // false 다른 주소 ....


        Apt apt3 = apt;

        System.out.println(apt == apt3); //같은 집에 거주

        apt3.doorColor = "red";

        System.out.println(apt.doorColor);

    }

}
