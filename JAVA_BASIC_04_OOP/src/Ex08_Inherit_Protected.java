import kr.or.kosa.Bird;

class Bi extends Bird{
	@Override
	protected void moveFast() {
		// TODO Auto-generated method stub
		super.moveFast();
	}
	//moveFast를 사용하려면 재정의 필수 
}

class Ostrich extends Bird{
	//특징 - 구체화, 특수화
	void run() {
		System.out.println("run...");
	}

	@Override
	protected void moveFast() {
		run();
	}
}

public class Ex08_Inherit_Protected {
	public static void main(String[] args) {
		Bi bi = new Bi();
		bi.fly();
		bi.moveFast();
		
		Ostrich ostrich = new Ostrich(); //프로젝트시 적극 활용 
		ostrich.run();
		ostrich.moveFast();
	}
}
