/*
지금까지 08까지 싱글 스레드 이용
>> 메모리에 stack 하나만 사용해 왔다 (Sequential하다) -> 싱글쓰레드

프로그램 실행 > 프로세스 >> 최소 하나의 Thread >> 최소 하나의 stack 메모리
JVM >> OS >> 환경구성 >> stack 메모리 >> main 함수가 최초로 올라가서 실행

멀티 쓰레드 >> stack 2개 이상을 사용 >> 동시에 여러 개의 함수가 실행 가능 (CPU가 점유 가능 상태)
*/

// 동기식 프로그래밍
public class Ex01_Single_Thread {
    public static void main(String[] args) {
        System.out.println("나 main 일꾼이야");
        worker1();
        worker2();
    }

    static void worker1() {
        System.out.println("나 1번 일꾼이야");
    }

    static void worker2() {
        System.out.println("나 2번 일꾼이야");
    }
}
