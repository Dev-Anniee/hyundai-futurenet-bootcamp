package practice;

/*
1. 자바 기본타입 8가지
2. 숫자 , 문자열

숫자   정수 (음의정수 0  양의정수) byte (8bit) -128 ~ 127  I/O 입출력 (배열)
                             char(한문자 2byte)  char c = 'A'  char c2 = '가'
                             영문자 특수문자 공백 (1byte)  한글 한글자(2byte)
                             ename varchar2(2) , char(2) , nvarchar2(10)
                             short (x)
                             int (4byte) -21억 ~ 21 정수 ***~~~ 정수 리터럴의 기본 타입 int
                             길어요
                             long(8byte)

      실수(부동소수점 : 대략적인 값) float (4byte)  7자리 정도의 소수 표현 가능 (반올림)
                               double(8byte)  15자리 정도의 소수 표현 가능  ***~~~ 실수 리터럴의 기본 타입 double

      + boolean (true , false)
      + String  9가지 ....

      정수 int , 실수 double , boolean , String

      //String str = new String("홍길동");
      String str = "홍길동";

      자바 타입은
      1. 값타입    8 + 1(String)
      2. 참조타입  (주소값을 저장) : 클래스 , 배열 , 인터페이스  >> 메모리의 주소값 > new 연산자

      class Car{}    Car car = new Car();  car 변수는 주소값 (kr.or.kosa@Oxaf111)
*/
public class Ex03_DataType {

    public static void main(String[] args) {

        int num = 100000000;
        System.out.println(num);

        //int num2 = 10000000000;
        //The literal 10000000000 of type int is out of range

        long num3 = 10000000000L;
        //The literal 10000000000 of type int is out of range

        long num4 = 100;
        //사실은 컴파일러가 내부적으로 일을 해요
        //long num4 = (long)100;
        //암시적 형변환 (컴파일러가 알라서 형변환)
        //작은타입을 큰 타입에 넣는 것은 당근 ....

        //개발자가 명식적으로(강제적으로) 형변환
        //버려지는 값이(쓰레기) 발생
        //int num5 = 10000000000; //개발자가 입력한 정수 리터털값의 범위가 int 범위를 초과
        //int num5 = 10000000000L;  //Type mismatch: cannot convert from long to int

		/*
		int num5 = (int) 10000000000L;
		System.out.println("num5 : " + num5); //쓰레기 값
		*/
        //정답 : 받는 타입 크기가 크면 되요

        long num5 = 10000000000L;
        System.out.println("num5 : " + num5);

        int num6 = (int)10000000L;  //정상
        //int num5 = (int) 10000000000L; 쓰레기

        char ch = '가';
        //char ch2 = 'ABC';
        //char 정수 타입 (정수값과 호환)
        //문자를 저장하지만 내부적으로 정수값을 가지고 있다)

        char ch2 = 'A';
        int chint = ch2;
        System.out.println("ch2 문자를 정수값으로 보면 : " + chint);
        //컴파일러는 int chint = (int)ch2;
		/*
		   1. 할당에 있어서는 값을 보지말고 값이 가지는 타입을 보자
		   1.1 리터럴 값에 대해서(개발자 직접 입력하는 값)
		   1.1.1 정수 리터럴에 기본 타입은 int
		   1.1.2 실수 리터럴에 기본 타입은 double

		   2. 형변환 (컴파일러:암시적 , 명시적)
		   2.1 큰 타입에 작은 타입을 넣는 것은 고민하지 말자 (컴파일러가 알아서 형변환)
		   2.2 작은 타입에 큰 타입을 넣고자 한다면 자동 형변환 지원하지 않아요(강제적 형변환)
		   2.3 데이터 손실이 발생할 수 도 있다 (해결 방법 : 받는 그릇을 크게 하자)
		   char c = (char)int;  책임은 개발자 .... 쓰레기 ....

		   char c = (char)10; //그롯은 int 값의 범위는 char 범위안에 ...
		   char c = (char)100000000; //손실 ....

		   String 클래스 타입
		   값타입 처럼 써도 문제 없다
		   String str = "홍길동";
		   자료구조 char 배열 :  ['홍']['길']['동']
		*/

        //java 특수문자
        //char sing = ' ';
        //char sing = '''; //Invalid character constant
        char sing = '\'';   //\   ' 데이터로 인정
        System.out.println(sing);

        String username = "홍\"길\"동";
        System.out.println(username);

        //언어마다 + 표현 방식
        //java   :  +  (산술 , 결합)
        //oracle :  +  (산술)  ...  || (결합)

        String str1 = "100000";
        String str2 = "10";
        String result = str1 + str2; //결합
        System.out.println(result);

        System.out.println("100" + 100); //100100
        System.out.println(100 + "100"); //100100
        System.out.println(100 + 100 + "100"); //200100
        System.out.println(100 + (100+"100")); //100100100
        System.out.println(100 + "100" + 100); //100100100

        // 경로  C:\Temp
        String path = "C:\\Temp";
        System.out.println(path);   //
        // \t  , \n  , \r .....

        path = "C:\\T\te\tm\nP";
        System.out.println(path);

        //실수(부동소수점)
        //float
        //double

        //float f = 3.14;  실수 리터럴의 기본 타입 double ... 3.14 double 그릇에

        float f = 3.14f;
        float f2 = (float)3.14;
        //데이터 손실 ...

        //현명한 개발자
        double d = 3.14;

        float data = 1.123456789f;
        System.out.println(data); //1.1234568  반올림 처리

        double data2 = 1.123456789123456789;
        System.out.println(data2); //1.1234567891234568  대략 16자리 ....

        //자바는  BigDecimal 클래스를 사용해서 정밀한 수 (화폐단위) ******~~~~~~~~

        double data3 = 100;
        System.out.println(data3); //100.0


        double data4 = 100;
        int number = 100;

        // data4 + number  결과 double

        //int result2 = (int)(data4 + number);
        //double result2 = data4 + number; 현명한 개발자

        int number2 = 100;
        //byte b2 = (byte)number2;
        //int b2 = number2;

        //Today point
        //1. 큰타입 + 작은 타입 연산하면 결과는 큰타입
        //2. 타입간 변환 >> 변수가 가지고 있는 값을 보지말고 타입을 보자
        //3. 명시적 (강제적) 형변환은 데이터 손실 ... 고민


    }

}

