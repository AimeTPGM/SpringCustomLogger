package com.aimetpgm.util.logger;

import com.sun.deploy.util.StringUtils;
import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Aspect
@Component
public class CustomLoggerAspect {

    final Logger LOGGER = LoggerFactory.getLogger(CustomLoggerAspect.class);

    @Before("@annotation(CustomLogger)")
    public void logMethodCall(JoinPoint joinPoint) throws Throwable {
        StringBuilder logMsg = new StringBuilder("executing ");
        logMsg.append(joinPoint.getSignature().getName().concat("() "));
        Object[] args = joinPoint.getArgs();
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        String[] params = codeSignature.getParameterNames();
        Map<String, Object> paramsAndValue = new HashMap();

        if (null != args && args.length > 0) {
            logMsg.append("with args: [");
            IntStream
                    .range(0, params.length)
                    .forEach(i -> paramsAndValue.put(params[i], args[i] == null ? "null": args[i]));
            Stream paramsAndValueStream = Arrays.asList(params).stream().map(param ->
                    param.concat(": ").concat(paramsAndValue.get(param).toString()));
            List argsList = Arrays.asList(paramsAndValueStream.toArray());
            logMsg.append(StringUtils.join(argsList, ", "));
            logMsg.append("]");

        } else {
            logMsg.append(" with no args" );
        }
        LOGGER.info(logMsg.toString());
    }

    @Around("@annotation(CustomLogger)")
    public Object logMethodProcesing(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.currentTimeMillis();
        return joinPoint.proceed();
    }

    @AfterReturning(value = "@annotation(CustomLogger)", returning = "returnValue")
    public void logMethodFinish(JoinPoint joinPoint, Object[] returnValue) {
        LOGGER.info("smth2");
    }

    @After(value = "@annotation(CustomLogger)")
    public void logMethodAfter(JoinPoint joinPoint) {
        LOGGER.info("smth3");
    }

    @AfterThrowing(pointcut = "@annotation(CustomLogger)")
    public void logMethodThrowing(JoinPoint joinPoint) {
        LOGGER.info("smth4");
    }
}
