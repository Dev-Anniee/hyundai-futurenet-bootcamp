package practice;

import java.util.Scanner;

public class Ex09_Statement {
    public static void main(String[] args) {

        int count=0;
        if(count < 1)System.out.println("true");

        if(count < 1) {
            System.out.println("true");
        }

        char data='A';
        switch (data) {
            case 'A':
                System.out.println("..");
                break;
            case 'B':
                System.out.println("..");
                break;
            default:
                break;
        }

        // 1~5까지의 합 (for 문과 while 사용하지 않고)
        // 1 부터 n까지의 합 ...
        int sum = 0;
        sum = 5 * (1 + 5) / 2; // 안할려고 for
        System.out.println(sum);


        //구구단 출력
        for (int i = 2; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if(i == j) break;
                //System.out.printf("%d*%d=%d\t",i,j,i*j);
                System.out.printf("*");
            }
            System.out.println();
        }


        for (int i = 2; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if(i == j) continue;  //skip 아래 구문을 ....
                System.out.printf("%d*%d=%d\t",i,j,i*j);
                //System.out.printf("*");
            }
            System.out.println();
        }

        for(int i = 100 ; i >= 0 ; i--) {
            System.out.println(i);
        }

        int a = 0 , b = 1 , c = 0; //피보나치 수열
        for(int i = 0; i < 10 ; i++) {
            a = b;
            b = c;
            c = a + b;
            System.out.printf("[%d]*[%d]=[%d]\t",a,b,c);
            System.out.println("   " + c);
        }

        int i = 10;
        while(i >= 10) {
            //반드시 증가감을 명시 (true , false)
            //--i;
            System.out.println(i);
            --i;
        }


        //while 1~100까지 합
        int sum2 = 0;
        int j = 1;
        while(j <= 100) {
            sum2+=j;
            j++;
        }
        System.out.println("sum2()" + sum2);

        // while(true)
        // for(;;)
        // do ~ while(){}  일단 한번은 실행 조건을 판단

        Scanner sc = new Scanner(System.in);

        int inputData=0;
        do {
            System.out.println("숫자 입력해(0~9)");
            inputData = Integer.parseInt(sc.nextLine());
        }while(inputData >= 10);

        System.out.println("당신이 입력한 숫자는 : " + inputData);
        //조건식이 true do문을 계속 실행
        //조건식이 false do문을 계속 실행

		/*
		 메뉴
		 1. 짜장
		 2. 짬뽕

		 */
    }

}
