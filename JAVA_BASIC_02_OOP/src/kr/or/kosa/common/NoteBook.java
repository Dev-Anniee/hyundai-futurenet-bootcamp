package kr.or.kosa.common;

//노트북은 색상을 가지고 있다 private 
//노트북은 생산년도를 가지고 있다 private
//노트북은 마우스를 가지고 있다

//클라이언트 -> 노트북은 필요에 따라서 마우스를 가질 수 있다
//마우스 (부품 : 마우스는 좌표값을 가지고 있다) 별도의 클래스

public class NoteBook {
	private String color;
	private int  year;
	public Mouse mouse; //new Mouse(); 올인원 마우스
	//NoteBook (마우스 장착된 노트북입니다.)
	//NoteBook 생성되면 Mouse 같이 생성 >NoteBook 폐기하면 Mouse 같이 폐기 (객체 생명주기가 같다)
 	//this (x)
	//생성자 (x)
	
	//노트북은 필요에 따라서 마우스를 가질 수 있다 
	//불편해 마우스.... (기능으로 표현) 
	
	public String getColor() {
		return color + "색상"; //간접 할당
	}


	public void setColor(String color) {
		this.color = color;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int data) {
		if(data < 1999) {
			year = 2000;
		}else {
			year = data;
		}
	}

	//마우스를 다른 함수에도 사용하겠다 -> 위에서 변수 선언
	public void handleMouse(Mouse mouse) { // 필요하다면 Mouse의 주소값을 받겠다
		//mouse =m;
		mouse.setX(4);
		mouse.setX(20);
		mouse.getReadMousePoint();
	}
}
