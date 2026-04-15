import java.util.Scanner;

public class Ex08_String_Quiz {

	public static void main(String[] args) {
		String ssn = "";
		boolean pass;

		do {
			Scanner scanner = new Scanner(System.in);
			System.out.println("주민번호 입력 : ");
			ssn = scanner.nextLine();
			pass = checkLength(ssn) && checkNum(ssn);
			if (pass)
				checkGender(ssn);

		} while (!pass);
	}

	public static boolean checkLength(String s) {
		return s.length() == 14;
	}

	public static boolean checkNum(String s) {
		String[] tmp = s.split("-");
		int num = tmp[1].charAt(0) - '0';
		return (1 <= num && num <= 4);
	}

	public static void checkGender(String s) {
		String[] tmp = s.split("-");
		int num = tmp[1].charAt(0) - '0';
		if (num % 2 == 1)
			System.out.println("남자");
		else if (num % 2 == 0)
			System.out.println("여자");
	}
}
