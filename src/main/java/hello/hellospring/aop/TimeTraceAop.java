package hello.hellospring.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
@Component
@Aspect
public class TimeTraceAop {

    //패키지 하위에 AOP를 다 적용해라는 코드. AOP는 공통관심사항의 로직을 만들어서 각각의 것에 적용하는 그런 기술이다. 편리함.
    @Around("execution(* hello.hellospring..*(..))")

    //이 함수는 메뉴얼을 보고 작성하면 된다고 함.
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs + "ms");
        }
    }
}