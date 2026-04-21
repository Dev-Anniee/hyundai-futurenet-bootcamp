

import javax.swing.JOptionPane;

class GameState {
    static volatile boolean gameOver = false;
    static volatile boolean timeOver = false;
    static volatile boolean clear = false;
    static volatile int correctCount = 0;
}

class TimerThread extends Thread {
    private final int limitSeconds;

    public TimerThread(int limitSeconds) {
        this.limitSeconds = limitSeconds;
    }

    @Override
    public void run() {
        for (int i = limitSeconds; i > 0; i--) {
            if (GameState.gameOver) {
                return;
            }

            System.out.println("남은시간 : " + i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        GameState.timeOver = true;
        GameState.gameOver = true;
        System.out.println("시간 종료!");
        System.exit(0); 
    }
}

class GugudanThread extends Thread {
    private final int dan;
    private final int startNumber;
    private final int endNumber;

    public GugudanThread(int dan, int startNumber, int endNumber) {
        this.dan = dan;
        this.startNumber = startNumber;
        this.endNumber = endNumber;
    }

    @Override
    public void run() {
        for (int i = startNumber; i <= endNumber; i++) {
            if (GameState.gameOver) {
                return;
            }

            String input = JOptionPane.showInputDialog(dan + " x " + i + " = ?");

            if (GameState.timeOver) {
                System.out.println("시간이 초과되어 종료합니다.");
                return;
            }

            if (input == null) {
                GameState.gameOver = true;
                System.out.println("사용자가 게임을 취소했습니다.");
                return;
            }

            input = input.trim();

            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(null, "값을 입력하세요.");
                i--;
                continue;
            }

            try {
                int userAnswer = Integer.parseInt(input);
                int correctAnswer = dan * i;

                if (userAnswer == correctAnswer) {
                    GameState.correctCount++;
                    System.out.println("정답 : " + dan + " x " + i + " = " + correctAnswer);
                } else {
                    System.out.println("오답 : " + dan + " x " + i + " = " + correctAnswer);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "숫자만 입력하세요.");
                i--;
            }
        }

        GameState.clear = true;
        GameState.gameOver = true;
        System.out.println(dan + "단을 모두 완료했습니다.");
    }
}

public class Ex05_GugudanGame {
    private static final int DAN = 2;
    private static final int START = 1;
    private static final int END = 9;
    private static final int LIMIT_SECONDS = 10;

    public static void main(String[] args) {
        Thread timerThread = new TimerThread(LIMIT_SECONDS);
        Thread gugudanThread = new GugudanThread(DAN, START, END);

        timerThread.start();
        gugudanThread.start();

        try {
            timerThread.join();
            gugudanThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        printResult();
    }

    private static void printResult() {
        System.out.println("===== 게임 종료 =====");
        System.out.println("맞춘 개수 : " + GameState.correctCount);

        if (GameState.clear) {
            System.out.println("축하합니다. " + DAN + "단을 모두 맞추었습니다.");
        } else if (GameState.timeOver) {
            System.out.println("시간이 종료되어 게임이 끝났습니다.");
        } else {
            System.out.println("게임이 종료되었습니다.");
        }

        System.out.println("Main End");
    }
}