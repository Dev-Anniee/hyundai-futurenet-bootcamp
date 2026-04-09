import kr.or.kosa.common.Airplane;

public class Ex06_static {
	public static void main(String[] args) {
		Airplane airplane1 = new Airplane();
		airplane1.makeAirplane(707, "대한");
		Airplane airplane2 = new Airplane();
		airplane2.makeAirplane(708, "대한");
		Airplane airplane3 = new Airplane();
		airplane3.makeAirplane(907, "아시아나");
		
		airplane1.airplaneTotalCount();
		airplane2.airplaneTotalCount();
		airplane3.airplaneTotalCount();
		
		//AirPlane.  클래스 이름 접근 불가 (private)
		//airPlane.  객체로 접근 불가 (private)
	}
}
