package kr.or.kosa;

/*
  요구사항 : 클래스를 설계 하는 근거 (명확)
  설계도 (속성(정보) + 기능)  > 속성 (고유정보(제조사,색상)  , 상태정보(속도))  + 기능 (단일기능)
  
  하나의 함수는 하나의 기능만을 갖는다 원칙
*/
public class Car { 
   private String company; //고유정보
   private String carcolor;
   private int speed; //상태정보
   
   
   public void run() {  //method (기능) 함수 ....
	   ++speed;
   }
   public void stop() {
	   speed = 0;
   }
}
