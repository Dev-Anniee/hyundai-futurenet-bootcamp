
public class Ex06_String_Object {
	public static void main(String[] args) {
		String str=""; //초기화 null, ""
		String[] strarr = {"aaa","bbb","ccc"};
		
		for(String s : strarr) {
			System.out.println(s);
		}
		//사용방법 : int , double 사용법과 동일
		
		String st="홍길동";
		System.out.println(st.length());
		//String > char[] 데이터 관리 >> [홍][길][동]
		System.out.println(st);
		System.out.println(st.toString()); //String 클래스 toString() 재정의
		
		//정식 표기 
		String sdata = new String("김유신");
		System.out.println(sdata);
		
		String name ="가나다라마";
		//내부적으로 데이터 관리 char[] > [가][나][다][라][마]
		
		String str1 ="AAA";
		String str2 ="AAA";
		System.out.println(str1);
		System.out.println(str2.toString());
		
		System.out.println(str1 == str2);
		//== str1 주소값을 비교
		//같은 주소라서 ....
		
		//Today Point
		//문자열의 비교는
		System.out.println(str1.equals(str2));
		//주소를 찿아가서 그 안에 값을 비교
		
		//why
		String str3 = new String("BBB");
		String str4 = new String("BBB");
		//새로운 메모리 할당 
		System.out.println(str3 == str4); //false 주소가 달라요
		System.out.println(str3.equals(str4)); //주소를 가지고 값을 ...
		
		 String s ="A";
		 s+="B";
		 s+="C";
		 System.out.println(s);
		 s ="A";
		 System.out.println(s);
		 //그래서 String  문자열 누적에 사용하면 바보
		//StringBuilder (성능) :동기화를 보장하지 않아요, 싱글쓰레드 보장
		//StringBuffer 사용하세요 ^^ (차이점 : 동기화)
	}
}
