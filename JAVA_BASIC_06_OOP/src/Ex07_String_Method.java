import java.util.Iterator;

public class Ex07_String_Method {
	public static void main(String[] args) {
		
		//1. 파일명과 확장자를 분리해서 출력 
		String filename2 = "home.jpg"; //(indexOf, substring,length)
		int index = filename2.indexOf('.');
		String s1 = filename2.substring(0,index);
		String s2 = filename2.substring(index+1);
		System.out.println("파일명 :" +s1);
		System.out.println("확장자 :" +s2);
		
		//2. ,기준으로 데이터 추출
		String str4 = "슈퍼맨,팬티,노랑색,우하하,우하하"; //(split)
		String[] results = str4.split(",");
		for (int i = 0; i < results.length; i++) {
			System.out.print(results[i]+" ");
		}System.out.println();
		
		//3. 배열의 총합을 구하세요
		int sum =0;
		String[] numarr = {"1","2","3","4"};
		for(int i=0; i<numarr.length; i++) {
			sum+=Integer.parseInt(numarr[i]);
		}
		System.out.println(sum);
		
		//4. 주민번호의 합을 구하세요
		String jumin = "123456-1234567";
		int result =0;
		String[] s = jumin.split("-");
		for(int i=0; i<s.length; i++) {
			for(int j=0; j<s[i].length(); j++) {
				result+=s[i].charAt(j)-'0';
			}
		}System.out.println(result);

		//2번째 방법
		result=0;
		int index1 = jumin.indexOf('-');
		String s3 = jumin.substring(0,index1);
		String s4 = jumin.substring(index1+1);
		
		for(int i=0; i<s3.length(); i++) {
			result +=Integer.parseInt(s3.substring(i,i+1));
		}
		
		for(int i=0; i<s4.length(); i++) {
			result +=Integer.parseInt(s4.substring(i,i+1));
		}System.out.println(result);
	}
}
