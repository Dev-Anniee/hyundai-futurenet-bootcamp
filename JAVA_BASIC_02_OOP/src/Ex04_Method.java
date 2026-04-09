import kr.or.kosa.common.Mouse;
import kr.or.kosa.common.NoteBook;

public class Ex04_Method {
	//NoteBook 구매 (메모리에 올리겠다)
	//Mouse 구매 (메모리에 올리겠다)
	
	public static void main(String[] args) {
		NoteBook noteBook = new NoteBook();
		noteBook.setColor("red");
		noteBook.setYear(2025);
		String color = noteBook.getColor();
		System.out.println(color);
		
		//노트북 사용에 있어서 마우스 불편
		Mouse mouse = new Mouse();
		//노트북 마우스 연결
		noteBook.handleMouse(mouse); // LG, HP 연결, interface, 구현, 타입 부모 타입 다형성
	}

}
