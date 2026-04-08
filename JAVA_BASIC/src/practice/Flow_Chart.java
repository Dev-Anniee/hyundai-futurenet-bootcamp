package practice;
import java.util.Scanner;

public class Flow_Chart {
    public static void main(String[] args) {
        int unit = 10000;
        int num = 0;
        int sw = 0;

        Scanner sc = new Scanner(System.in);
        int money = Integer.parseInt(sc.nextLine());

        while (unit >= 1) {
            num = (money / unit);
            System.out.println(unit + " " + num + "개");
            money -= num * unit;

            if (sw == 0) {
                unit /= 2;
                sw = 1;
            } else {
                unit /= 5;
                sw = 0;
            }
        }
    }
}
