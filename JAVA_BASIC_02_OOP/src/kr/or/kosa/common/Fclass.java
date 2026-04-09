package kr.or.kosa.common;

/*기능(행위) : 클래스 안에 있는 기능 : method
 *
method :  하나의 method는 하나의 기능 구현 원칙
만약 여러개의 기능 (다른 기능 호출해서 사용)

모든 함수는 호출에 의해서 동작 -> 맞을 수도 , 아닐 수도 (생성자)

event > load 함수 (사건) > DOM 객체 >memory > 자동으로 호출되는 함수(사건 발생) > 사건에 대해서 처리하는 handler 함수

자바 함수 종류 4가지
1. void, parameter(0) :public void print(String str) {실행구문} //자바도 var 타입 있다
2. void, parameter(X) : public void print() {실행구문}
3. return type, parameter(0) :public int print(String str) {실행구문 return 100;}
4. return type, parameter(X) :public int print() {실행구문 return 100;}
블랙박스 모드
함수란 입력하면 출력을 함수하는 것

void : return type 없다
return type : 8+1, Array, class, Interface, Collection
public boolean print() {return true}
public List<Emp> getEmp(){return List<Emp> list = ArrayList(); list.add(new Emp()); ...return list; }
public Car print(Car car//인자,인수,파라미터){Car c = new Car(); c.color = car.color; return c;}
public Car print(){return new Car();}

parameter type :  8+1, Array, class, Interface, Collection
----------------------------------------------------------
1. void, parameter(0)
void print2(int a, int b, int c)

*/

public class Fclass {
    public int data;

    // 함수 접근자 : 68% public - 쓰도록 만든 것이므로
    // 함수 private : 28% 클래스 설계 함수를 private 설계자의 의도 => 공통함수 (클래스 내부에서 다른 함수를 도와준다)
    // 설계에 모든 의도가 있어야 한다, 재사용, 목적을 위주로 말해야한다

    public void m() {
        System.out.println("일반함수 : void, parameter(x)");
    }
    public void m2(int i)  {
        System.out.println("void, parameter(O)");
        System.out.println("parameter i 값 :"+i);

    }
    public int m3()  {
        return 1000;
    }

    public int m4(int data)  {
        return 100+data;
    }
    //요기까지 4가지
    //확장 버전

    public int sum (int i, int j, int k) {
        return i+j+k;
    }
    private int subSum(int i) { // 객체 입장에서는 접근 불가 (클래스 내부...) 다른 함수 호출
        return i+100;
    }
    public void callSubSum() {
        int result = subSum(100);
        System.out.println("call result : "+result);
    }
    public int opSum(int data) {
        int result = subSum(data);
        if(result>0)
            return 1;
        else return -1;
    }

//	함수를 만드는데 정수 타입 파라미터 2개를 입력 받아서 둘 중에 큰 값 리턴하는 코드
//	max(10,5) return 10

    public int max(int a,int b) {
        if(a>b)
            return a;
        else
            return b;
    } //리턴 횟수를 줄이는게 좋은 코드
    //좋은 코드 return (a>b)?a:b; 삼항연산자, 변수에 넣는 건 사용하지 않으므로 쓸 필요 없다 -> 나쁜 코드 , 쓰지 않을 거면 담지 말자

}

