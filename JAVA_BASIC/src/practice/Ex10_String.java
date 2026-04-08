package practice;

public class Ex10_String {
    public static void main(String[] args) {
        String str1 ="AAA";
        String str2 ="AAA";
        System.out.println(str1==str2); //주소 동일

        String str3 = new String("BBB");
        String str4 = new String("BBB");
        System.out.println(str3.equals(str4));
        // 문자열의 해당 값이 같으면 같은 게 맞지 않아? -> equals 함수의 탄생
        // 약속! 문자열의 비교는 반드시 equals를 사용하자

    }
}
