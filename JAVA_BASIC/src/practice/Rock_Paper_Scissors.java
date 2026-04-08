package practice;
import java.util.Scanner;

/*컴퓨터가 자동으로 나온 가위 , 바위 , 보 에 대해서 사용자가 값을 입력 해서 처리 하세요
    point) 가위바위보 판단의 논리를 최소화 하세요

            ( 예를 들면 : 가위=> 1 , 바위 => 2 , 보 => 3)

    double random = Math.random();
    System.out.println(random); // 0<= random < 1
    random = (3*random+1); //1 <= random < 4;
            System.out.println((int)random);

    아래 구문 또는 여러분 조가 만든 메뉴구성을 통해 가위 바위 보 게임을 완성하세요
    ex)
    가위 , 바위 , 보 게임을 시작합니다
           >user님 입력하세요
           > 1
                   > 당신은 컴퓨터에게 지셨습니다
           > 다시 하시겠습니까
    샘플 위 처럼 메뉴를 구성해서 하셔도 되고 아니면 컴퓨터에게 이길때까지 계속 진행하다고 이기면 게임
    종료 하셔도 됩니다*/

//내가 짠 코드
public class Rock_Paper_Scissors {
    public static void main(String[] args) {
/*
        //( 예를 들면 : 가위=> 1 , 바위 => 2 , 보 => 3)
        double random = Math.random();
        //System.out.println(random); // 0<= random < 1
        random = (3*random+1); //1 <= random < 4;
        System.out.println((int)random);

        Scanner sc = new Scanner(System.in);
        int userChoice = Integer.parseInt(sc.nextLine());

        if (userChoice == random)
            System.out.println("동점");

        if (userChoice <=2) {
            if (random < userChoice )
                System.out.println("패");
            else if (userChoice +2 == random )
                System.out.println("패");
            else
                System.out.println("승");
        }
        else{
            if (userChoice - 2 == random) {
                System.out.println("승");
            } else {
                System.out.println("패");
            }
        }
*/

        //정답에 가까운 코드
        // 가위1, 바위2, 보3
        int com = (int) (Math.random() * 3 + 1);
        Scanner sc = new Scanner(System.in);
        boolean stop = false;
        while (true) {
            System.out.println("가위(1) , 바위(2) , 보(3) 게임을 시작합니다.\nuser님 입력하세요.");
            System.out.printf("확인용: 컴퓨터의 값은 %d\n", com);
            int player = Integer.parseInt(sc.nextLine());
            switch ((player - com + 3) % 3) {
                case 1:
                    System.out.println("이겼습니다.");
                    stop = true;
                    break;
                case 2:
                    System.out.println("졌습니다. 다시 하겠습니까? (Y/N)");
                    break;
                case 0:
                    System.out.println("비겼습니다. 다시 하겠습니까? (Y/N)");
            }
            if (!stop) {
                String input = sc.nextLine();
                if (input.equals("Y"))
                    stop = false;
                else if (input.equals("N"))
                    break;
            } else
                break;
        }
    }
}



