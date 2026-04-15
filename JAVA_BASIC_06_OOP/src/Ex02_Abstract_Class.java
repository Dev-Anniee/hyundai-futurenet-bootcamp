/*
게임
유닛 unit

unit : 공통기능 (이동좌표, 이동, 멈춘다) >> 추상화, 일반화
unit : 이동 방법은 다르다 (이동 방법은 동일 움직이는 방식이 다르다)
*/

abstract class Unit{
    int x,y;
    void stop() {
        System.out.println("Unit stop");
    }
    //이동 방법은 다르다 (함수는 동일한 이름) > Unit 자원 강제 구현
    abstract void move(int x, int y);
}

class Tank extends Unit{
    @Override
    void move(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println("Tank 소리내며 이동: "+this.x +"," + this.y);
    }
    //구체화, 특수화
    void changeMode() {
        System.out.println("탱크모드 변환");
    }
}

class Marine extends Unit{

    @Override
    void move(int x, int y) {
        // TODO Auto-generated method stub
        this.x = x;
        this.y = y;
        System.out.println("Marine은 걸어서 이동: "+this.x +"," + this.y);
    }
    void stimPack() {
        System.out.println("스팀팩기능");
    }
}

class DropShip extends Unit{

    @Override
    void move(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println("DropShip은 하늘로 이동: "+this.x +"," + this.y);
    }
    //특수화, 구체화
    void load() {
        System.out.println("Unit load");
    }
    void unload() {
        System.out.println("Unit unload");
    }

}

public class Ex02_Abstract_class {
    public static void main(String[] args) {
        Tank tank = new Tank();
        tank.move(10, 20);
        tank.stop();
        tank.changeMode();

        Marine marine = new Marine();
        marine.move(10,20);
        marine.stop();
        marine.stimPack();

        //다형성 타입의 검증
        // 상속 관계에서 부모타입, 부모를 구현한 객체 타입
        //JAVA : instanceof (객체의 타입을 비교하는 연산자) true,false
        // JAVA SCRIPT :typeof(), instanceof

        //객체             //타입
        if(tank instanceof Unit) {
            System.out.println(true);
        }else System.out.println(false);

        if(tank instanceof Tank) {
            System.out.println(true);
        }else System.out.println(false);

        //1. 탱크 3대 만들고 같은 좌표 (600,800)로 이동 시키기
        Tank[] tanks = {new Tank(), new Tank(),new Tank()};
        for(Tank t :tanks) {
            t.move(600,800);
        }
        //2. Tank 1대, Marine 1대, DropShip 1대 생성하고 같은 좌표로 이동 (666,888)
        Unit[] units = {new Tank(), new Marine(), new DropShip()};
        for(Unit unit : units) {
            unit.move(666, 888);
        }
    }
}
