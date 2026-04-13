/*현업 : 기업 환경
한 개의 클래스만 설계 개발 (x)

설계도 한 장으로 업무 표현 불가능
설계도 여러장으로 나누어서 개발 (기준,논리적)

쇼핑몰
결과관리, 배송관리, 재고관리, 회원관리, 상품관리, 판매처관리, 카드관리 > 업무별 별도의 설계도

연관 (관계)
DB 관계 (1:1, 1:N, N:M) >>70% 1:N 
1:N 사원과 부서 관계가 대표적

 DB 1:N 관계는 객체 지향에서는 연관 관계이다!
 
 여러개의 설계도가 있을 때 관계를 정의하는 기준
 1. 상속 : is a ~은(는) ~이다
 2. 포함 : has a ~은(는) ~가지고 있다 (부품 정보로 다른 객체를 가지고 있는 것)
 
 원은 도형이다 is a 
 경찰은 권총이다  has a
 
원은 도형이다
원 extends 도형

삼각형은 도형이다
삼각형 extends 도형

도형은 원과 삼각형의 공통사항이다 
일반화 , 추상화 되었있다 (도형)

원과 점의 관계
원은 점이다 x
원은 점을 가지고 있다 o
원과 점과의 관계는 부품 관계 (has~a)

원은 도형이다
삼각형은 도형이다
사각형은 도형이다

도형(분모) 공통자원 : 추상화, 일반화 > 그리다 , 색상
원은 구체화 (특수화) : 특징 >> 반지름, 한 점
삼각형 구체화 (특수화) : 3개의 점

점(좌표값) x,y
원은 점을 가지고 있다
사각형은 점을 가지고 있다

class Shape {그리다, 색상} 부모
class Point {좌표값} 부품
*/


class Shape{
	String color = "gold";
	void draw() {
		System.out.println("도형을 그리다");
	}
}

class Point{
	int x;
	int y;
	Point(){
//		this.x = 1;
//		this.y = 2;
		this(1,2);
	}

	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
}

//원을 만드세요 (정의는 원은 한 점과 반지름을 가지고 있다)
//1. 원은 도형이다
//2. 원은 점을 가지고 있다
//3. 원만의 구체화, 특수화된 내용은 반지름

class Circle extends Shape{
	Point point; //포함 ( 부품)
	int r ; // 구체화, 특수화
	
	public Circle() {
//		this.point = new Point(10,15);
//		this.r = 10;
		this(new Point(10,15),10);
		//점의 위치와 반지름 직접 제어 
	}
	//overloading 걸어서 반지름을 입력 받으세요
	Circle(Point point, int r){
		this.point = point;
		this.r = r;
	}
}

//삼각형 클래스를 만드세요
// 심각형은 3개의 점과 색상과 그리고 기능을 가지고 있다 
/*
 * hint) Shape, Point 제공 받아서 사용 
 * hint) (x,y), (x,y) (x,y) 
 * Default 삼각형을 만들 수 있고 3개의 좌표값을 입력 받아 그릴 수도 있다
 */

class Triangle extends Shape {
	Point x;
	Point y;
	Point z;
	
	/*
	 int x;
	 int y;
	 int z; 
	 
	 int[] ... 
	 */
	
	public Triangle() {
		//this.x = new Point(10,20);
		//this.y = new Point(30,40);
		//this.z = new Point(50,60);
		this(new Point(10,20),new Point(30,40),new Point(50,60));
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Triangle(Point x , Point y , Point z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	//구체화 ,특수화
	//추가적인 기능
	void trianglePoint() {
		System.out.printf("x :(%d,%d)\t",x.x , x.y);
		System.out.printf("y :(%d,%d)\t",y.x , y.y);
		System.out.printf("z :(%d,%d)\t",z.x , z.y);
		System.out.println();
	}
	
}


class Triangle2 extends Shape{
	Point[] pointarray;
	
	public Triangle2(){
		this(new Point[] {new Point(10,20),new Point(30,40),new Point(50,60)});
	}
	
	public Triangle2(Point[] pointarray){
		this.pointarray = pointarray;
	}
	
	//구체화 ,특수화
		//추가적인 기능
		void trianglePoint() {
			for(Point point : this.pointarray) {
				System.out.printf("(%d,%d)\t",point.x , point.y);
			}
			/*
			System.out.printf("x :(%d,%d)\t",x.x , x.y);
			System.out.printf("y :(%d,%d)\t",y.x , y.y);
			System.out.printf("z :(%d,%d)\t",z.x , z.y);
			System.out.println();
			*/
		}
}

public class Ex02_inheritance_Composition {
	public static void main(String[] args) {
		/*
		Circle circle = new Circle();
		System.out.println(circle.r);
		System.out.println(circle.color);
		System.out.println(circle.point.x);
		System.out.println(circle.point.y);
		circle.draw();
		*/
		
		Circle circle2 = new Circle(new Point(10, 20), 100);
		System.out.println(circle2.r);
		System.out.println(circle2.color);
		System.out.println(circle2.point.x);
		System.out.println(circle2.point.y);
		circle2.draw();
		
		//기본 삼각형
		Triangle2 triangle2 = new Triangle2();
		triangle2.trianglePoint();
		
		
		Point[] parray = new Point[] {new Point(11,22),new Point(33,44),new Point(55,66)};
		
		Triangle2 triangle3 = new Triangle2(parray);
		triangle3.trianglePoint();
	}
}
