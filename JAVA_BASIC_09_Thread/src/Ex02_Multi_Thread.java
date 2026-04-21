/*
Thread 프로세스에서 하나의 최소 실행 단위(흐름) >>method >> 실행되는 공간 stack
결과 : stack 여러 개 만들어서 동시에 함수가 실행 가능하도록 한다 >> cpu 점유할 수 있는 상황

Thread 방법
1. Thread 클래스 상속 >> class Task extends Thread >> run 함수
2. Runnable 인터페이스 구현 >> class Task implements Runnable >> run 추상함수 강제 구현 : Thread 아님
그래서 별도의 작업

ex) class Task extends Thread .... class Task extends Car 이미 다른 클래스를 상속한 경우 -> 인터페이스 사용하자
								   Thread th = new Thread(new Task()) - Thread로 쓰려면 new를 해야하기 때문이다.
*/

import java.awt.geom.Path2D;

class Task_1 extends Thread{

    //Thread 추상 클래스 아니다 why : Thread th = new Thread(인터페이스를 구현한 객체를 사용하기 위하여)
    @Override
    public void run() {//run은 Thread에서 처음으로 올라가는 main 역할 >> stack >> run
        super.run();
        for(int i=0; i<1000; i++) {
            System.out.println(" Task_1 "+ i+ this.getName());
        }
        System.out.println("Task_1 run() 함수 END");
    }

}

class Task_2 implements Runnable{ //독자적 생존 불가능, Thread 아니다 .. Thread에 필요한 run 함수를 구현한 일반 클캐스
    @Override
    public void run() {
        for(int i=0; i<1000; i++) {
            System.out.println(" Task_2 "+ i);
        }
        System.out.println("Task_2 run() 함수 END");
    }

}


public class Ex02_Multi_Thread {
    public static void main(String[] args) {
        //main thread
        Task_1 th = new Task_1();
        th.start(); //1번째 스택
        // Point : start() 실행되면 main 함수 위에 올라간다
        // 1. memory에서 stack 하나 더 생성 그리고 Thread 가지고 있는 run 함수를 stack 올려 놓고 자기는 소멸

        Thread th2 = new Thread(new Task_2());
        th2.start(); //2번째 스택

        //익명 타입 *익명 클래스*
        Thread th3 = new Thread(new Runnable() {//문법 한 단계 발전 > 함수형 인터페이스 > 람다 표현식
            @Override
            public void run() {
                for(int i=0; i<1000; i++) {
                    System.out.println(" Task_3 "+ i);
                }
                System.out.println("Task_3 run() 함수 END");
            }
        });
        th3.start();//3번째 스택
        for(int i=0; i<1000; i++) {
            System.out.println(" main "+ i);
        }System.out.println("main run() 함수 END");
    }
}
