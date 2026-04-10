package kr.or.kosa.common;

/*우리 회사는 비행기를 주문제작 판매하는 회사입니다
우리 회산느 비행기를 생산하는 설계도를 만들려고 합니다

요구사항 
1. 비행기를 생산하고 비행기의 이름과 번호를 부여해야 한다
2. 비행기가 생산되면 비행기의 이름과 번호가 맞게 부여되었는지 확인하는 작업 (기능) 출력
3. 공장장은 현재까지 만들어진 비행기의 총 누적대수를 확인할 수 있다 (기능) 출력

단 제약사항
생성자 사용 금지
this 사용 금지 
>> 변수 함수 접근 제한자

객체 한개 비행기 한대
AirPlane air707 = new AirPlane()
air707.자원 = "대한항공"
air707.자원 = "707"*/

//1차 완성본
//public class Airplane {
//	private String name;
//	private int num;
//	private static int totalCount;
//	//객체간 공유자원 (static)
//
//	public void makeAirplane(int airplane_num, String airplane_name) {
//		num = airplane_num;
//		name = airplane_name;
//		totalCount++;
//		airplaneInfo();
//	}
//
//	private void airplaneInfo() { //직접적으로 보여주지 않겠다라는 의미의 private
//		System.out.printf("이름 : %s, 번호 : %d\n",name,num);
//	}
//
//
//	//가끔 와서 쓸 기능
//	public void airplaneTotalCount() {
//		System.out.printf("비행기 총 제작 개수 : %d\n", totalCount);
//	}
//
//
//	//조금 더 배워서 2차 완성본 (this, 생성자x)
//}

public class Airplane {
	private String name;
	private int num;
	private static int totalCount;
	//객체간 공유자원 (static)

	public Airplane(int num, String name) {
		this.num = num;
		this.name = name;
		totalCount++;
		airplaneInfo();
	}

	private void airplaneInfo() { //직접적으로 보여주지 않겠다라는 의미의 private
		System.out.printf("이름 : %s, 번호 : %d",name,num);
		System.out.println();
	}


	//가끔 와서 쓸 기능
	public void airplaneTotalCount() {
		System.out.printf("비행기 총 제작 개수 : %d", totalCount);
		System.out.println();
	}
}
