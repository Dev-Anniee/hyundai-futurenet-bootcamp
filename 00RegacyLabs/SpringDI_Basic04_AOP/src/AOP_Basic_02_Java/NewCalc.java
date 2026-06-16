package AOP_Basic_02_Java;

//AOP 주업무를 만드는 개발자의 보조업무 있는지 없는지 모른다
//AOP filter (한글처리)

public class NewCalc implements  Calc {

	@Override
	public int ADD(int x, int y) {
		
		//보조업무(공통관심) : 횡단관심
		
		//주업무
		int sum = x + y;
		
		//보조업무(공통관심) : 횡단관심
		
		return sum;
	}

	@Override
	public int MUL(int x, int y) {
	    
		//보조업무(공통관심) : 횡단관심
		
		//주업무
		int mul = x * y;
		
		//보조업무(공통관심) : 횡단관심
		
		return mul;
	}

	@Override
	public int SUB(int x, int y) {
		
		//보조업무(공통관심) : 횡단관심
		
		//주업무
		int sub = x - y;
				
		//보조업무(공통관심) : 횡단관심
				
		return sub;
	}

}
