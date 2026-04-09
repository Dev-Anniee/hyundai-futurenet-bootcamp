package kr.or.kosa.common;

public class Person { //extends Object
		//요구사항 정의 하는 습관, 사람은 무엇을 가지고 있다
		public String name; // instance variable (생성되는 객체마다 생성 (다른 값을 가질 수 있다))
		public int age;//초기화하지 않아도 default
		public boolean power; // default : false
	
	
	//함수의 이름은 어떤 규칙이 있나요?
	//보통은 길게 쓴다
	// 사원에 테이블 모든 데이터를 가져오는 함수 이름 -> select_all_emp , public void getAllEmpList()
	// 사원번호를 기준으로 한건의 데이터를 가져오는 함수 이름 public void getEmpListByEmpno
	//public void insertEmpData()
	//public void deleteEmpDataByEmpno();
	//public void updateEmp();
	
	
	public void personPrint() {
		System.out.printf("이름은 %s 나이는 %d 파워는 %s입니다\n",name,age,power);
	}
}

/*
 * 1. instance variable은 초기화하지 않아도 된다 > why > default 값 초기화라는 것은 변수가 처음으로 값을 할당받는 것
 * 2. instance variable은 초기화해도 되나요> Yes >> public int age =1; 필요에 따라서는 기본값(초기값을) 설정 가능 
 * -> 생성자의 본질이 흐려짐 : 비추
 * 생성자는 제어도 가능 -> 웬만해서는 생성자를 활용하자 
 * 3. 포인트 : 거의 초기화 하지 않는다 >>public int age =1;
 * 생성자를 통해 >> 값을 받아서 초기화 >> 클래스를 줄테니 생성자를 이용해서 만들고 싶은 걸 만들어라 :강제성+ 확장성
 * 설계자의 의도 >> 생성되는 객체마다 다른 값을 가졌으면 좋겠다 
 */