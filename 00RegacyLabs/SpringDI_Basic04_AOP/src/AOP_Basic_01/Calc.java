package AOP_Basic_01;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

/*
간단한 사칙 연산기
-주업무  : 주관심 : 사칙연산(ADD , MUL) 주요기능
---요구사항 : 연산에 걸리 시간 알고 싶어요  (공통관심) > 보조관심
-보조업무

*/
public class Calc {
	
	public int Add(int x , int y) {
		//요구사항
		
		//공통업무(보조관심)
		Log log = LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("[타이머 시작]");
		
		//주업무
		int result = x + y;
		
		//공통관신(보조관심)
		sw.stop();
		log.info("타이머종료");
		log.info("Time log method :  ADD");
		log.info("Time log method :" + sw.getTotalTimeMillis());
		return result;
	}
	
	public int Mul(int x , int y) {
				//요구사항
		
				//공통업무(보조관심)
				Log log = LogFactory.getLog(this.getClass());
				StopWatch sw = new StopWatch();
				sw.start();
				log.info("[타이머 시작]");
				
				//주업무
				int result = x * y;
				
				//공통관신(보조관심)
				sw.stop();
				log.info("타이머종료");
				log.info("Time log method :  MUL");
				log.info("Time log method :" + sw.getTotalTimeMillis());
				return result;
	}
}
