package kr.or.kosa;

/*
JAVA API Stack 제공 (LIFO)
내가 직접 stack 설계

1. 데이터 저장 구조 (Array(Object) 저장 공간)
2. 저장공간 데이터 넣는 작업 push, pop, empty, full

정적 배열 (Cart와 동일한 형태) >Product[] Cart >>정적 배열 >index 또는 position 가지고 위치 확인
*/
public class Mystack {
	private Object[] stackarr; //데이터를 담을 저장소
	private int top; // 배열의 index 값 사용
	
	public Mystack(int maxsize) {
		this.stackarr = new Object[maxsize];
		top=-1;
	}
	
	public boolean empty() {
		return top==-1;
	}
	
	public boolean full() {
		return top==stackarr.length-1;
	}
	
	public void push(Object o) {
		if(!full())
			stackarr[++top]=o;
		else 
			System.out.println("데이터가 꽉 찾습니다");
	}

	public Object pop() {
		Object object = null;
		if(!empty()) {
			object = stackarr[top--];
		}
		else {
			System.out.println("데이터가 비었습니다");
		}
		return object;
	}
}
