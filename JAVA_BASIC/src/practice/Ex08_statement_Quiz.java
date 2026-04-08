package practice;
import java.util.Scanner;

public class Ex08_statement_Quiz {
    public static void main(String[] args) {
        //힌트 ^^ 탈출 힌트
				/*
				boolean exit = false;
				for (char upper = 'A'; upper <= 'Z'; upper++) {
				    for (char lower = 'a'; lower <= 'z'; lower++) {
				        System.out.println(upper + "-" + lower);
				        if (lower == 'g') {
				            exit = true;
				            break;
				        }
				    }
				    if (exit) {
				        break;
				    }
				}
				*/
        //시작
        //while(true){

        //}


        //메뉴를 보여주고 사용자에 선택 ....
        //원하시는 메뉴를 선택하지 않으면 강제로 다시 메뉴를 보여 주세요

        //1.예금
        //2.출금
        //3.잔고
        //4.종료  조건 판단 while 탈출 (어떤 조건이 일치하면 강제 break)


        //잔액 변수를 생성 (balance)
        //예금 처리  + 누적
        //출금 처리  - 누적
        //잔고      balance  출력
        //종료      프로그램 끝 (함수의 종료  , 프로그램의 강제 종료) :
        //논리적 종료 ( return (x) , exit(x) 안되요)

        //while(true) {
			/*
			    1. 예금 | 2. 출금  | 3. 잔고 | 4. 종료

			    하나의 번호를 선택하세요
			    입력값 받기

			    입력받은 값을 판단 (switch)
			    1  예금처리
			    예금액을 입력받아서 balance 누적

			    2  출금처리
			    예금액에서 출금급액을 마이너스 누적

			    3  잔고 보여주세요

			    4  종료합니다
			    논리값을 만들어서
			    논리값이 = false 가지게

			    if(auto == false){
			    	break;
			    }
			 */
        //}

        int deposit=0;
        boolean auto = true;
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("***********");
            System.out.println("1.예금 \n");
            System.out.println("2.출금 \n");
            System.out.println("3.잔고 \n");
            System.out.println("4.종료 \n");
            System.out.println("***********");
            System.out.println("번호를 입력하세요 : ");
            int num = Integer.parseInt(sc.nextLine());

            switch(num){
                case 1:
                    System.out.println("예금처리 : ");
                    deposit += Integer.parseInt(sc.nextLine());
                    break;
                case 2:
                    System.out.println("출금처리 : ");
                    deposit -= Integer.parseInt(sc.nextLine());
                    break;
                case 3:
                    System.out.println("잔고 : \n" +deposit);
                    break;
                case 4:
                    System.out.println("종료합니다.\n");
                    auto = false;
                    break;
                default:
                    System.out.println("올바른 메뉴를 선택하세요");
            }
            if(!auto) {
                break;
            }
        }
    }
}
