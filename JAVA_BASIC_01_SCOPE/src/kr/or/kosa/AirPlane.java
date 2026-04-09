package kr.or.kosa;

public class AirPlane {
    public int data; //0
    int data2; //같은 패키지 안에서는 문제 없다, 다른 패키지 안에서는 사용 못한다, 왜 썼는지 의도 고민할 필요
    private int data3; // 문법적 오류 없다, 의미 내부에서 함수 사용하는 용도 , 내부 함수가 사용될 때만 활용 가능

    public void print() {
        data2 = 100;
    }
}

