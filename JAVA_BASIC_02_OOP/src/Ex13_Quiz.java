//개발자적인 마인드
// 반복적인 코드 : 할당 >> 생성자를 호출하는 this
//생성자 - 멤버필드의 초기화 , 코드의 강제성 -> 여러개의 옵션을 강제할 수 있다

public class Ex13_Quiz {
	public static void main(String[] args) {
		//할당은 마지막에 한 번만
		class Car6{
			String carColor;
			int door;
			
			public Car6() { //기본 사양
				carColor = "blue";
				door = 4;
			}
			//옵션 반영 - 비지니스 로직
			public Car6(int door) { 
				carColor = "blue";
				this.door = door;
			}
			public Car6(String carColor) {
				this.carColor = carColor;
				this.door = 4;
			}
			public Car6(int door,String carColor) {
				this.carColor = carColor;
				this.door = door;
			}
		}
	}
}
