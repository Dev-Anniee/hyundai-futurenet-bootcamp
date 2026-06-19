package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

	@Pointcut("@annotation(aop.ServiceLog)")
	public void serviceLogPointcut() {
	}

	@Before("serviceLogPointcut()")
	public void beforeServiceMethod(JoinPoint joinPoint) {
		logger.info("[AOP Before] {} argsCount={}",
				joinPoint.getSignature().toShortString(),
				joinPoint.getArgs().length);
	}
}
