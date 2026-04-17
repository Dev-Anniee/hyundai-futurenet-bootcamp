import java.util.Stack;

import kr.or.kosa.Mystack;

public class Ex05_Stack_Queue {
	public static void main(String[] args) {
		//JAVA API 제공( Stack , Queue 를 구현한)
		
		Stack<String> stack = new Stack<String>();
		stack.push("A");
		stack.push("B");
		stack.push("C");  //LIFO
		
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		
		System.out.println(stack.empty()); //true
		//System.out.println(stack.pop()); //Exception in thread "main" java.util.EmptyStackException

		//구조
		//javaScript 배열 (stack)
		// const [] 
		
		Mystack mystack = new Mystack(5);
		mystack.push("A");
		mystack.push("B");
		mystack.push("C");
		mystack.push("D");
		System.out.println(mystack.pop());
		System.out.println(mystack.pop());
		System.out.println(mystack.pop());
		System.out.println(mystack.empty());
		System.out.println(mystack.pop());
		System.out.println(mystack.pop());
		System.out.println(mystack.empty());
	}
}
