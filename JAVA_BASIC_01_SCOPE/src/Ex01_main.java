/*일반적으로 프로젝트 진행하면
필요한 클래스 별도의 package를 만들어서
kr.or.kosa.dto > a.java
kr.or.kosa.dao > a.java
kr.or.kosa.menu > a.java
경로가 다르므로 a.java파일 문제 없음
같은 패키지 안에 같은 이름의 자바 파일 존재할 수 없다

kr.or.kosa.dto > emp.java
kr.or.kosa.dao > empdao.java > 에서 emp.java  사용하고 싶을 때

클래스는 == 설계도 == 사용자가 정의하는 큰 타입 (작은 타입을 여러 개 포함, 클래스는 변수 앞에 쓸 수 있다) > 클래스 이름 변수명 > Emp emp;

클래스의 구성 요소
필드 + 메서드 + 생성자

필드 (member field) > 데이터, 정보를 담고 있다
>> 고유정보, 상태정보, 부품정보(가장 중요, 쪼개질 수 있다, 별도의 클래스로 빠질 수 있다, 쪼개고 쪼개기) - 엔진 : 여러개의 자원
	색상, 	속도, 	엔진

함수(method)
>> 기능 (행위) >> 한 번에 하나의 기능만 >> 달린다, 멈춘다, 소리가 난다, 문을 연다
생성자 함수 !!! 함수인 것이 중요 >> 특수한 목적을 가지고 있는 함수 (기능이 정해져 있다) >> 마음대로 쓰면 안됨 -> 자동화가 안되기 때문이다
데이터 베이스 속성 이름 = 멤버 필드 : 자동화 / 일일히 옮기는 번거로움
우리가 짜는 모든 코드는 약속을 기반함 -> 자동화 보장
생성자 함수 >> 특수한 목적을 (기능이 정해져 있다)>> 객체 생성시 멤버 필드 초기화 >> 강제성 >> 강제성 (여러가지) >> 오버로딩

+a
getter, setter >> 특수한 목적 >> 캡슐화 자원 >> read, write >> public 책임질 수 없으므로, 직접할당을 막고 간접할당을 통해서 오류를 막는다
설계자의 의도를 파악해보아야 한다 (문법을 외우는 건 코더)

class 설계도{
	고유정보
	상태정보
	부품정보 >> 클래스(다른 설계도)

	+
	필요에 따라서 (생성자, getter, setter)
	+기능(행위)}

	클래스, 필드, 생성자, 메소드(기능), >> 자기의 영역이 있다! scope >> 생성되는 위치 or  접근자 (한정자, 수정자) = modifier
	접근자 :default(가지고 있는 기본 값), private (같은 클래스에서만, 패키지 X), public, protected (상속 관계에서만 의미가 있다)
	장소(위치) : 폴더의 개념 (package) 기반으로 클래스 내부, 함수 내부, 제어문 내부

	변수 :
	static variable : 가장 큰 변수, 프로그램 종료시까지 사용됨 -> 메모리 관리적 차원에서 적당히 사용해야함
	instance variable? 의미 : 객체마다 생기는 변수
	local variable  : 함수에서 사용되는 변수
	block variable : 반복문에서 사용되는 변수

	변수 :  static >instance > local > block
	예시) 국회의원, 시의원 등등

*/

import kr.or.kosa.Emp; //별표(*)는 지양하기 -> 가독성(x)

class Emp2{ //디폴트 = 연습 (src안에서만)

}

class Dept{ //default class Dept
    int deptno; // default int deptno
    public String dname; // 의미 없는 코드 ,  문법은 문제 없다
    private int count; // 그대로, 같은 패키지안에 써도 숨기겠다, 직접 할당X, 간접할당O 의도 파악

    public int getCount() {
        return count;
    }

    public int setCount(int num) { //설계자의 의도를 파악하는 연습을 해야한다. (하나하나의 의지)
        if(num<0) { //음수는 쓰지 않겠다
            count = 0;
        }
        else {
            count = num;
        }
        return count;
    }
}
//현업에서 클래스 안에 클래스가 있는 경우는 없다. 연습용으로만 사용할 것!
//연습용 class Dept > 접근자 생략 > default > 같은 폴더에서만 사용할 것이므로

public class Ex01_main {
    public static void main(String[] args) {
        Emp emp = new Emp();
        emp.empno = 7788;
        emp.ename = "홍길동";
        emp.printInfo();

        //kr.or.kosa.Car car = new kr.or.kosa.Car();
    }
}
