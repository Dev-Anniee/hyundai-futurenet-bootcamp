/*
추상클래스
1. 미완성 클래스 (설계도)
1.1 완성된 코드 + 미완성 코드
1.2 설계도 의미 > 미완성 코드 (실행 블럭이 없는 함수) > {x} >public void run(); > 추상함수

설계도의 기본은 믿지 않음이다
1.3 당신이 반드시 미완성 코드 구현해라 (강제적)
1.4 당신이 필요에 따라 구현부를 완성해줘 -> 강제성은 부여 (추상 함수)


추상클래스는 스스로 객체 생성이 불가 !!! > 미완성  > 반드시 상속을 통해 사용 가능 > 추상 클래스
*/

abstract class Abclass{
    int pos;
    void run() {
        pos++;
    }
    abstract void stop(); //실행 블럭이 없다는 의미는 반드시 재정의 하라
}

class Child extends Abclass{
    @Override
    void stop() {
        //구현은 마음대로
        this.pos = 0;
        System.out.println("stop :"+this.pos);
    }
}

//추상클래스보다 인터페이스를 더 많이 사용한다
public class Ex01_Abstract_Class {
    public static void main(String[] args) {
        Child child = new Child();
        child.run();
        child.run();
        System.out.println(child.pos);
        child.stop();

        Abclass abclass = child; //****중요***** 전자제품( buy(Product) , Product[] cart
        abclass.run();
        abclass.stop(); //부모 타입으로 접근하더라도 재정의 되어있다면 재정의 함수로 간다

    }
}
