package kr.or.kosa;

public class MyStackGeneric<T> {
	    private Object[] arr;
	    private int top;
	    private int capacity;

	    // 생성자
	    public MyStackGeneric(int capacity) {
	        this.capacity = capacity;
	        this.arr = new Object[capacity];
	        this.top = -1;
	    }

	    // push (데이터 추가)
	    public void push(T data) {
	        if (isFull()) {
	            throw new RuntimeException("Stack Overflow");
	        }
	        arr[++top] = data;
	    }

	    // pop (데이터 제거)
	    public T pop() {
	        if (isEmpty()) {
	            throw new RuntimeException("Stack Underflow");
	        }
	        return (T)arr[top--];
	    }

	    // peek (맨 위 값 확인)
	    public T peek() {
	        if (isEmpty()) {
	            throw new RuntimeException("Stack is Empty");
	        }
	        return (T) arr[top];
	    }

	    // 비어있는지 확인
	    public boolean isEmpty() {
	        return top == -1;
	    }

	    // 꽉 찼는지 확인
	    public boolean isFull() {
	        return top == capacity - 1;
	    }

	    // 크기
	    public int size() {
	        return top + 1;
	    }
}
