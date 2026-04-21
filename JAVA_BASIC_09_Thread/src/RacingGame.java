import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RacingGame extends Thread {

    private String name;
    private int distance;
    public static List<String> list = Collections.synchronizedList(new ArrayList<>());
    //synchronized(list) {  동기화 처리
    // list.add(value);
    //}
    public RacingGame(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (distance >= 30) {
                    System.out.println(name + " 통과");
                    list.add(name);
                    break;
                }

                Thread.sleep(300);
                distance += (int)(Math.random() * 3) + 1;

                int boxNum = distance / 5;
                String mark = "";

                for (int i = 0; i < boxNum; i++) {
                    mark += "■";
                }

                System.out.println(name + " " + mark);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public static void main(String[] args) {
        RacingGame r1 = new RacingGame("1번말");
        RacingGame r2 = new RacingGame("2번말");
        RacingGame r3 = new RacingGame("3번말");
        RacingGame r4 = new RacingGame("4번말");
        RacingGame r5 = new RacingGame("5번말");
        RacingGame r6 = new RacingGame("6번말");

        r1.start();
        r2.start();
        r3.start();
        r4.start();
        r5.start();
        r6.start();

        try {
            r1.join();
            r2.join();
            r3.join();
            r4.join();
            r5.join();
            r6.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("최종순위 / " + (i + 1) + "등 : " + list.get(i));
        }
    }
}