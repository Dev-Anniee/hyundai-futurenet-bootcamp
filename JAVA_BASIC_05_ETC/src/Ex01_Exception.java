/*오류
1. 에러 (error) : 네크워크 장애, 메모리, 하드웨어 >> 개발자가 코드적으로 해결 불가
2. 예외 (exception) : 개발자가 코드 실수 발생 문제 > 문제 발생 원인 파악 >> 수정 >> 배포
2.2 예외처리 : 문제 원인 파악 > 일단 실행
2.3 컴파일시 안되면 OK
2.4 런타임 (실행도중) 문제 >> 강제 종료 >> 문제가 생긴 시정 >> 그 이후 코드는 실행이 안되요

예외처리 : 프로그램이 안정적으로 종료 >>그리고 나서 >> 분석 (로그, 상태 코드)
>> 분석이 끝나면 >> 코드 수정 >> 재배포

try,catch,final
try{
	>>문제가 의심되는 코드
	>>문제가 발생되면 >> 그 문제를 담을 수 있는 객체가 자동으로 만들어지고 (new..생성자)객체에 정보가 담겨서 catch 던져진다
	ex) [0][1][2] >> [3] =100 예외 발생 > new ArrayIndexOutofBoundException("...")
	Exception은 다형성을 나타내주는 예시, 가독성을 올리기 위한 방법
} catch(Exception e (객체 주소를 받는다), e라는 변수가 예외 객체의 주소를 받는다) {}
	(다형성) Exception 부모타입
	문제파악 (결과 출력)
	처리 (코드가 자동 수정되진 않아요)
	보고 (개발)
	1. 관리자에게 email
	2. 로그 기록 (장애 발생 기록)

	강제종료는 막아진다

	finally{
		문제가 발생하던, 발생하지 않던 강제적으로 실행되는 코드
		함수 종료 1. return (finally는 return을 만나도 로직을 탄다) 2. kill
		DB 작업 할 때 중요 (자원 해제)
		conn.close()
	}
*/

//예외처리는 개발자의 기본
public class Ex01_Exception {
    public static void main(String[] args) {

//		System.out.println("main start");
//		System.out.println(0/0); //예외가 터진다
//		System.out.println("main end");
//
//		main start
//		Exception in thread "main" java.lang.ArithmeticException: / by zero
//			at Ex01_Exception.main(Ex01_Exception.java:38)

        System.out.println("main start");
        try {
            System.out.println(0/0);
        } catch(Exception e) {
            //예외 확인 강제 종료를 막는다
            e.printStackTrace();
        }
        System.out.println("main end");
    }
}

