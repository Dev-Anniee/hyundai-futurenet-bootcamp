package kr.or.kosa.common;

//마우스는 한 점을 가지고 있다 , 좌표값
//x,y

public class Mouse {
	private int x,y;

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	//요구사항 ... x,y 각각 확인하길 원한다 getX(), getY()
	public void getReadMousePoint() {
		System.out.println("x : "+x+" y : "+y);
	}
}
