package practice;

public class Ex05_Operation {
    public static void main(String[] args) {

        // 자바 정수 연산의 기본은 int (4byte) 공간에서 계산
        byte b = 100;
        byte b2 = 1;

        //byte b3 = b+b2 >>컴파일러는 int+int >> int 를 byte에 담으면 쓰레기 값
        //ex) byte + short >> 컴파일러 >>  int + int
        //ex) char + char >> 컴파일러 >>  int + int
        // int 보다 작은 타입 다 int로 바꾸어서 연산
        //예외> long


		/*
		char + int >> int
		int + int >> int
		int + long >> long

		정수 + 실수 >> 타입 크기에 상관 없이 >> 실수가 승자
		*/

        long lo = 100000000000L;
        float fo = 1.2f;

        // lo + fo
        // long result3 = (long)(lo+fo);
        // System.out.println(result3);

        float result3 = lo+fo;
        System.out.println(result3);

        /* 필수지식
         * 변수
         * + 타입 (값 타입 + 참조 타입)
         * + 연산자(산수르 논리, 관계)
         * + 제어문(if,switch,while,for,break,continue)
         */

        int p = 10;
        int k =-10;

        //삼항연산자 사용 p 와 k 값을 비교 true p 값을 false f을 갖는 result 만드세요

        int result4 = (p == k)? p: k;
        System.out.println(result4);


        /*
         * 진리표 0 거짓 1 참 or, and 연산
         *
         * DB 쿼리
         * select * from emp where job ='IT' and sal >3000;
         * select * from emp where job ='IT' or sal >3000;
         *
         * 연산자 비트 연산 : | or 연산, & and 연산
         * 영상 처리, 데이터 판독에 쓰인다
         *
         * int  x =3;
         * int y = 5;
         *
         * System.out.println((x|y));
         * 128 64 32 16 8 4 2 1
         * 				0 0 1 1 >>3
         * 				0 1 0 1 >>5
         * 				0 1 1 1 >>비트 or 연산
         * 				4+2+1 >>7
         *
         * 128 64 32 16 8 4 2 1
         * 				0 0 1 1 >>3
         * 				0 1 0 1 >>5
         * 				0 0 0 1 >>비트 and 연산
         * 				1
         *
         * ||,&&
         *
         * 포인트 :
         * && and 연산
         * || or 연산
         *
         * if (10>0 && -1>1 &&100>2 &&1>-1) {A} else {B}
         * if (10>0 || -1>1 || 100>2 || 1>-1) {A} else {B}
         *
         */

        int data = 90;
        switch(data) {
            case 100: System.out.println("100");
            case 90: System.out.println("90");
            case 80: System.out.println("80");
            default: System.out.println("xxxxx");
        }

        int data2 = 90;
        switch(data2) {
            case 100: System.out.println("100");
                break;
            case 90: System.out.println("90");
                break;
            case 80: System.out.println("80");
                break;
            default: System.out.println("xxxxx");
        }

        //문제
        //public static double random()
        //a pseudorandom double greater than or equal to 0.0 and less than 1.0.
        //0.0 <=random <1.0

        System.out.println(Math.random()); //난수
        System.out.println(Math.random()*10); //0~9난수
        System.out.println((int)(Math.random()*10)+1); //1~10난수

    }
}

