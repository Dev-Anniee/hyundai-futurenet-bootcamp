import kr.or.kosa.common.Tv;

public class Ex03_MethodCall {
	public static void main(String[] args) {
		//티비 한 대를 만들자
		//Tv 설계도를 메모리에 올려서 사용하라
		
		Tv lgTv = new Tv();
		lgTv.brand= "LG";
		lgTv.infoPrint();
		
		lgTv.channelUp();
		lgTv.channelUp();
		lgTv.channelUp();
		lgTv.channelUp();
		lgTv.channelUp();
		lgTv.channelUp();
		
		lgTv.infoPrint();
		
		lgTv.channelDown();
		lgTv.channelDown();
		lgTv.channelDown();
		lgTv.channelDown();

		lgTv.infoPrint();

		Tv sTv = new Tv();
		sTv.brand = "ss";
		sTv.channelUp();
		sTv.infoPrint();
		
	}
}
