package kr.or.kosa; //package 구조를 기반으로 설계

//객체지향언어 > 설계도를 만들수 있다 > 재사용

//설계도 == 클래스 == 데이터 큰타입(여러개의 작은 타입)

// int number  = 100;
// Emp emp;
// emp = new Emp();

//설계도 (가장 기본적이 구조)  : 속성(상태정보) + 기능(행위정보) >> 클래스 >> 데이터 타입(큰)
public class Emp extends Object {
   
   private int empno;  //캡슐화 , 은닉화 (간접할당 .. 직접할당 하지 않을 거야)
   private String ename;
   private int sal;
   
   
   public Emp() {  //constructor (생성자 함수)
	   //문제가 없는 이유는  member field (instance variable) default 값을 가지고 있어서 
   }
   
   public Emp(int empno, int sal, String ename) { //강제성 (생성되는 객체마다 다른 초기값))
		//super();
		this.empno = empno;
		this.sal = sal;
		this.ename = ename;
   }

   // getter ,setter (함수 : 특수한 목적 : 간접할당)
   public int getEmpno() {
	return empno;
   }

   public void setEmpno(int empno) {
	this.empno = empno;
   }

   public int getSal() {
	return sal;
   }

   public void setSal(int sal) {
	this.sal = sal;
   }

   public String getEname() {
	return ename;
   }

   public void setEname(String ename) {
	this.ename = ename;
   }
   //
   
   //상속관계에서 부모가 가지고 있는 함수를 재정의 (내용만 바꾼다)  >> 만나는 ... annotation 컴파일러 
  // @Override
 //  public String toString() {
//	return "Emp [empno=" + empno + ", sal=" + sal + ", ename=" + ename + "]";
//   }
   
   
   
}
