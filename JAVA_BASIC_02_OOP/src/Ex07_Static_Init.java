/*
초기화 

1. static variable   : 공유자원  (객체간) 
2. instance variable : 생성되는 객체마다 다른 값을 가지게 하겠다 

두 자원은 default 값을 (초기화 할 필요 없다)

생성자 역할은 member field 초기화 

생성자 static variable 초기화 , instance variable 초기화 

그러면 new 하지 않으면 static variable 초기화 안되요 

고민 : static {} static 초기자 
*/

class Test2 {
    static int cv = 1000; //초기화
    static int cv2; //default 0

    int iv = 9; //초기화

    static {
        //실행시점 : static int cv , static int cv2 클래스 영역 올라간 직후 바로 실행 블럭안에 코드가
        cv2 = cv + 2222;  //조작된 논리가 필용  { } 제공
    }

    //일반 자원 초기자(초기화 블럭) : 기능이 생성자 겹쳐 (인지도가 없어요)
    {
        System.out.println("일반 초기화 블럭이 실행.....");
        if(iv < 10) iv = 55555;
    }
}

public class Ex07_Static_Init {

    public static void main(String[] args) {
        System.out.println(Test2.cv);
        System.out.println(Test2.cv2);


        //일반 초기화 블럭이 실행  new
        Test2 test2 = new Test2();
        System.out.println(test2.iv);


    }

}