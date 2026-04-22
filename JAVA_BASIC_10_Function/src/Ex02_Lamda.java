/*
람다 표현식
람다식 함수를 하나의 식으로 표현한 것

자바에서 람다식을 사용하기 위해서 >>함수형 인터페이스 생성
>>자바에서는 함수 하나만을 사용할 수 없다

print() 사용
class Test {   

   public void print(){
   
   }
   
   public static void sprint(){
   
   }
}

Test.sprint();
Test t = new Test();
t.print();

>> javascript 처럼 함수만 단독으로 사용 ....

함수형 인터페이스 > 인터페이스가 [ 단 하나의 추상 메서드 ] 만 선언된 인터페이스 

1. javascript 처럼 함수만 단독
2. 추상클래스나 인터페이스를 만들어서 이름 없는 익명 클래스 :    강제 구현

interface MyFunc{
    int max(int a , int b);
}

MyFunc f = new MyFunc(){
	int max(int a , int b) {return a > b ? a : b; } //논리 람다식 ...바꾸자
}

f.max();  //함수를 사용가능   class 없이도 
int value = f.max(3,5);


정리)
함수형 인터페이스 (인터페이스 설계 함수 하나만을 가진다)
@FunctionalInterface
interface MyLamdaFunction{
	int max(int a, int b);
}
이미 @FunctionalInterface 붙은 인터페이스 자바 제공하고 있다 

+ 

람다식 장점
1. 코드 간결
2. 가독성 높다
3. 병렬프로그래밍 가능

람다식 단점
람다를 사용하면 무명함수는 재사용 불가능
디버깅 어렵다
재귀적 구현 어렵다

람다는 시대의 흐름이다 
*/

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
interface MyLamdaFunction{
	int max(int a, int b);
}

public class Ex02_Lamda {

	public static void main(String[] args) {
		
		System.out.println(new MyLamdaFunction() {
			@Override
			public int max(int a, int b) {
				// TODO Auto-generated method stub
				return a > b ? a : b;
			}
		}.max(3, 5));
		
		//람다식
		MyLamdaFunction lamdaF = (int a , int b) -> a > b ? a:b;
		System.out.println(lamdaF.max(3,10));
		
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("C");
		list.add("react");
		
		for(String str : list) {
			System.out.println(str);
		}
		
		System.out.println("**************");

		list.stream().forEach((String str) -> {System.out.println(str);}); 
		list.stream().forEach(str -> {System.out.println(str);});
		list.stream().forEach(System.out::println);
		//:: 메서드 참조 : 이미 있는 메서드를 데이터 전달 ...
	}

}
