/*
자바에서는 클래스 안에 클래스가 올 수 있다
ex) awt, swing, 안드로이드 앱

사용이유 - 코드를 간소화 
class Outer{ (자원에 대한 접근을 편하도록ㄹ )
	class InnerClass{
	}
}
*/

class OuterClass{
	public int pdata =10;
	private int data =30;
	class InnerClass{
		void msg() {// 객체간 데이터를 주고 받는 것보다 안쪽에서 다루는 게 편하다
			System.out.println("outer class data :"+ data);
			System.out.println("outer class pdata :"+ pdata);
		}
	}
}

public class Ex10_innerClass {
	public static void main(String[] args) {
		OuterClass outobj = new OuterClass();
		System.out.println("public field : "+outobj.pdata);
		OuterClass.InnerClass innerObj =  outobj.new InnerClass();
		innerObj.msg(); // Outerclass 자원에 대한 접근이 자유롭다
	}
}
