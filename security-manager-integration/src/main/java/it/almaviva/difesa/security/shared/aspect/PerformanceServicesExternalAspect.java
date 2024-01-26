package it.almaviva.difesa.security.shared.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Optional;

@Aspect
@Slf4j
public class PerformanceServicesExternalAspect {

    @Around( "publicServicesExternalMethods()" )
    public Object logServicesPerformance ( ProceedingJoinPoint joinPoint ) throws Throwable {

        Object result = joinPoint.proceed ();

        if(log.isDebugEnabled()) {
            long startTime = Calendar.getInstance().getTimeInMillis();
            long endTime = Calendar.getInstance().getTimeInMillis();

            long time = endTime - startTime;

            String info = String.format("Performance Service External Indicator => SERVICE EXTERNAL EXECUTION TIME : " +
                    "%1$s = %2$s " +
                    "(milliseconds)", joinPoint.getSignature().toShortString(), time);
            log.debug(info);
        }
        return result;
    }

    /***
     * Logging before execution of one of the list of public methods
     * @param joinPoint
     */
    @Before( "publicServicesExternalMethods()" )
    public void logBeforeServices ( JoinPoint joinPoint ) {

        if (log.isDebugEnabled()) {
            String info = String.format("Performance Service External Indicator => SERVICE EXTERNAL NAME START : %1$s",
                    joinPoint.getSignature().toShortString());
            log.debug(info);
        }
    }


    /***
     * Logging after execution of one of the list of public methods
     * @param joinPoint
     */
    @After( "publicServicesExternalMethods()" )
    public void logAfterServices ( JoinPoint joinPoint ) {

        if (log.isDebugEnabled()) {
            String info = String.format("Performance Service External Indicator => SERVICE EXTERNAL NAME END : %1$s",
                    joinPoint.getSignature().toShortString());
            log.debug(info);
        }
    }


    /**
     * List of all public methods of all controllers
     */
    @Pointcut(
            "execution(public * it.almaviva.difesa.security.service.impl..*.*(..))"
    )
    public void publicServicesExternalMethods () {

    }

    /**
     * Return request current thread bound or null if none bound.
     * @return Current request or null
     */
    private HttpServletRequest currentRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return Optional.ofNullable(servletRequestAttributes).map(ServletRequestAttributes::getRequest).orElse(null);
    }

}
