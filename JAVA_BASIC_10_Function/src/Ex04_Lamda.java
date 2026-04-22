import java.lang.reflect.Parameter;

@FunctionalInterface
interface Myfun1{
	void method(int x);
}

@FunctionalInterface
interface Myfun2{
	int method(int x, int y);
}

public class Ex04_Lamda {
	public static void main(String[] args) {
		
		//익명클래스 강제구현
		Myfun1 my = new Myfun1() {
			@Override
			public void method(int x) {
				System.out.println("param x :" + x);
				
			}
		};
		my.method(100);
		//////////////////////////////////////////////////
		//람다식으로  구현부는 마음대로 
		
		Myfun1 myfun1 = x->System.out.println("param x:" +x);
		myfun1.method(500);
		
		//Myfun2 my3 = (x,y) -> {int result=x+y; return result; };
		Myfun2 myfun2 = (x,y)-> x+y;
		System.out.println(myfun2.method(100, 200));
	}
}
