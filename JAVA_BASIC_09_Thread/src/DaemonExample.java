class DaemonExample {

    static {
        // 클래스 로딩 시 실행 (main 없이 시작 느낌)
        Thread userThread = new Thread(() -> {
            System.out.println("User Thread 시작");

            Thread daemon = new Thread(() -> {
                while (true) {
                    System.out.println("데몬 실행중...");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {}
                }
            });

            daemon.setDaemon(true);
            daemon.start();

            try {
                Thread.sleep(3000);
            } catch (Exception e) {}

            System.out.println("User Thread 종료");
        });

        userThread.start();
    }
}