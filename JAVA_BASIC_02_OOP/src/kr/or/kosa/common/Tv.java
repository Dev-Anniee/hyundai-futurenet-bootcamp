package kr.or.kosa.common;

public class Tv {
    public int channel; //요구사항 대로 , default = 0
    public String brand; //default = null

    // private -> 1. 생성자, 2. Getter, Setter 고려
    // 생성자, 함수 고려 -> 강제성 여부

    public void channelUp() {
        channel++;
    }

    public void channelDown() {
        channel--;
    }

    public void infoPrint() {
        System.out.printf("브랜드 이름 : [%s], 채널 [%d]\n",brand,channel);
    }

}
