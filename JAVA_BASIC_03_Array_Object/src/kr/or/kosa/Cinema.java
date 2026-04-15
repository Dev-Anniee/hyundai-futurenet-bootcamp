package kr.or.kosa;

import java.util.Random;
import java.util.Scanner;

/* 요구사항 정의
1. 영화관 예매 사이트를 만들려고 한다
2. 함수, 클래스
3. 우리는 하나의 영화관 만을 가지고 있다... 좌석 가지고 있다
생성자로 빼야할 건 빼야한다
기능 1. 예매하기 2. 조회하기 (조건 : 예매번호 알고있다) 3. 전체 좌석을 조회할 수 있다 4. 예매 취소 가능 + 초기화 5. 종료
 한 번에 여러개의 좌석 예매 가능
 캡슐화, 변수명과 함수명이 잘 구현되어있는가, 중복
*/



class Cinema_Contents {

    //모든 것은 안에 구현
    //프로그램 시작 메뉴 보이고 시작
    static String[][] seat;
    static String[][] ticket_num;

    public Cinema_Contents() {
        seat = new String[10][10];
        ticket_num = new String[10][10];
        for (int i = 0; i < seat.length; i++) {
            for (int j = 0; j < seat[i].length; j++) {
                seat[i][j] = "__";
                ticket_num[i][j] = "";
            }
        }
    }

    public void View_All_seat() {
        for (int i = 0; i < seat.length; i++) {
            for (int j = 0; j < seat[i].length; j++) {
                System.out.printf("[%s] ", seat[i][j]);
            }
            System.out.println();
        }
    }

    public boolean Reserve_seat(int i, int j) {
        if (seat[i][j].equals("__")) {
            seat[i][j] = "XX";
            System.out.println("예매 완료 되었습니다");
            return true;
        } else {
            System.out.println("이미 예약된 좌석입니다 ");
            return false;
        }
    }

    public void View_One_seat_By_TicketNum(String str) {
        for (int i = 0; i < ticket_num.length; i++) {
            for (int j = 0; j < ticket_num[i].length; j++) {
                if (str.equals(ticket_num[i][j]))
                    System.out.printf("%s로 예매된 당신의 좌석은 [%d][%d] 입니다\n", str, i, j);
            }
        }
    }

    public void Cancel_seat(String t_num) {
        boolean found = false;
        for (int i = 0; i < ticket_num.length; i++) {
            for (int j = 0; j < ticket_num[i].length; j++) {
                if (ticket_num[i][j].equals(t_num)){
                    found = true;
                    seat[i][j] = "__";
                    ticket_num[i][j] = "";
                    }
                }
            }
            if(found)
                System.out.println("예매가 취소되었습니다");
            else {
                System.out.println("예매 정보가 없습니다");
        }
    }

    public void Menu() {
        System.out.println("***************");
        System.out.println("******메뉴******");
        System.out.println("1. 예매하기");
        System.out.println("2. 예매번호로 조회하기");
        System.out.println("3. 전체 좌석 조회하기");
        System.out.println("4. 취소하기");
        System.out.println("5. 종료하기");
        System.out.println("***************");
    }

    public void process() {
        Scanner sc = new Scanner(System.in);
        int num = 5;
        do {
            Menu();
            num = Integer.parseInt(sc.nextLine());
            switch (num) {
                case 1:
                    System.out.print("몇 자리를 예매하시겠습니까>");
                    int r = Integer.parseInt(sc.nextLine());
                    View_All_seat();

                    Random random = new Random();
                    String sharedTicketNum = "";
                    for (int q = 0; q < 8; q++) {
                        sharedTicketNum += Integer.toString(random.nextInt(8) + 1);
                    }
                    for(int k=0; k<r; k++) {
                        System.out.print("행을 입력하세요 : ");
                        int i = Integer.parseInt(sc.nextLine());
                        System.out.print("열을 입력하세요 : ");
                        int j = Integer.parseInt(sc.nextLine());

                        if (Reserve_seat(i, j)) {


                            ticket_num[i][j] = sharedTicketNum;
                        } else {
                            System.out.println("다시 선택해주세요");
                            k--;
                        }
                    }System.out.println("당신의 티켓 번호 : "+ sharedTicketNum);
                    break;
                case 2:
                    System.out.print("예매번호를 입력하세요 : ");
                    String tn = sc.nextLine();
                    View_One_seat_By_TicketNum(tn);
                    break;
                case 3:
                    View_All_seat();
                    break;
                case 4:
                    System.out.print("예매번호를 입력하세요 : ");
                    tn = sc.nextLine();
                    Cancel_seat(tn);
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    break;
                default:
                    System.out.println("잘못된 번호입니다 다시 입력해주세요");
                    break;
            }
        } while (num != 5);
        }
}

public class Cinema{
    public static void main(String[] args) {
        Cinema_Contents cc = new Cinema_Contents();
        cc.process();
        }
}



