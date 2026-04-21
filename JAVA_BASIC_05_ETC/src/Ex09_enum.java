import java.util.Calendar;

import kr.or.kosa.utils.Week;

public class Ex09_enum {
	public static void main(String[] args) {
			Week today = null;
			Calendar calendar = Calendar.getInstance();
			
			int week = calendar.get(Calendar.DAY_OF_WEEK);
			System.out.println(week);
			
			switch (week) {
			case 1: today = Week.SUNDAY; break;
			case 2: today = Week.MONDAY; break;
			case 3: today = Week.TUESDAY; break;
			case 4: today = Week.WEDNESDAY; break;
			case 5: today = Week.THURSDAY; break;
			case 6: today = Week.FRIDAY; break;
			case 7: today = Week.SATURDAY; break;
			}
			
			if(today==Week.SUNDAY)
				System.out.println("잠자기");
			else {
				System.out.println("자바 공부");
			}
	}
}
