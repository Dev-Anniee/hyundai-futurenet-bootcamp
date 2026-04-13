/*
 오늘의 포인트
 상속관계에서 override (재정의) : 상속관계에서 자식이 부모의 함수를 재정의(내용을 바꾼다)
 
[상속관계]에서 [자식 클래스]가 [부모 클래스] 메서드들의 재정의[내용]할 수 있다

문법은
부모함수와 이름과 동일
파라미터 동일, 리턴 타입 동일
결국 실행 {블록 안에 코드만 변경}
*/

class Test{
	@Override
	public String toString() {
		return "재정의를 통해서 얻고자... member field 값을 출력하는 용도";
	}
	
	public String PrintParameter() {
		return "";
	}
}

class Point2{
	int x =4;
	int y =5;
	String getPosition() {
		return this.x+" / "+this.y;
	}
}

class Point3D extends Point2{
	int z = 6;
	
	//기능 (3개의 좌표값을 출력하는 기능)
//	String getPosition3D(){
//		return this.x + "/" + y+ "/" + this.z;
//	}
	
	// 부모의 함수를 당신이 재정의 기법 (내용)
	//getPosition()  좌표값을 하나의 함수로 통일
	
	@Override
	//툴과 컴파일러가 문법적으로 문제가 있는지 검증 
	String getPosition() {
		return this.x+" / "+this.y+" / "+this.z;
	}
}

public class Ex03_Inherit_override {
	public static void main(String[] args) {
		
		Point3D point3d = new Point3D();
		//System.out.println(point3d.getPosition3D());
		System.out.println(point3d.getPosition());
		//재정의는 강력해서 부모 함수를 무시한다! -> 상속 관계에서 super()를 사용해야 한다 : 부모 함수를 보기 위함
		// 접근은 자식의 재정의된 함수만 접근이 가능
	}
}
