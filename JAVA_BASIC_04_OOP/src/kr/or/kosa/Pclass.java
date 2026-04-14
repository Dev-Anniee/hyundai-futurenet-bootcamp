package kr.or.kosa;

public class Pclass {
	//접근제한자
	private int p;
	int d;
	protected int pr;
	public int pu;
	
	//객체 입장 ... default
	protected void m() {
		//너 m 함수를 사용하려면 반드시 상속에서 재정의 해야해
	}
}
