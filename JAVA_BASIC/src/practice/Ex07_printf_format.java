package practice;
import java.util.Scanner;

public class Ex07_printf_format {
    public static void main(String[] args) {

        //System.out.println()
        //C# : Console.writeLine();

        System.out.println();

        int num = 1000;
        System.out.println(num);
        System.out.println("num 같은" + num + "입니다");

        //형식 format
        System.out.printf("num 값은 %d 입니다\n", num);
        System.out.printf("num 값은 %d 입니다\n", num);

        System.out.printf("num 값은 %d 입니다 또 %d도 있어요\n", num, 12345);

        /*
         * %d 십진수
         * %f 실수
         * %s 문자열
         * %c 문자
         * + \t, \n
         */

        float f = 3.14f;
        System.out.println(f);
        System.out.printf("f 변수값은 %f입니다\n", f);
//
//		//입력 받기
        Scanner sc = new Scanner(System.in); // 무엇인가를 입력하고 엔터까지 대기 상태
//		String value = sc.nextLine();
//
//		System.out.println("value"+value);
//
//		int value2 = sc.nextInt();
//		System.out.println(value2);

        //포인트
        // 권장사항은 nextInt, nextFloat 사용을 권장하지 않는다
        // 권장사항은 일단 문자열로 받자, 필하다면 타입 변환을 해라

        //float value3 = sc.nextFloat();

        //중요 포인트
        // [문자열] ==> 숫자(정수, 실수)
        // 입력은 문자열로 받고 필요하다면 나중에 변환하면 된다

        //String data = sc.nextLine();
        //int age = Integer.parseInt(data); // 때로는 parameter로 객체만 사용되는 경우 (int,double 사용 불가)
        //int 하는 원시 타입 클래스 타입을 만들면 된다 -> Integer
        //Float.parseFloat(data);

        System.out.println("숫자를 입력하세요");
        int number = Integer.parseInt(sc.nextLine());
        System.out.printf("입력한 값 : %d",number);
    }
}

