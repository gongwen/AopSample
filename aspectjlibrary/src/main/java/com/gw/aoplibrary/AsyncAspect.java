package com.gw.aoplibrary;

import com.gw.threadlibrary.BackgroundExecutor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by GongWen on 17/1/17.
 */
@Aspect
public class AsyncAspect {
    private static final String POINTCUT_METHOD =
            "execution(@com.gw.aoplibrary.annotation.AsyncMethod * *(..))";

    @Pointcut(POINTCUT_METHOD)
    public void method() {
    }

    @Around("method()")
    public Object asyncExecute(final ProceedingJoinPoint joinPoint) {
        BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "") {
                                       @Override
                                       public void execute() {
                                           try {
                                               joinPoint.proceed();
                                           } catch (Throwable e) {
                                               Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                                           }
                                       }
                                   }
        );
        return null;
    }
}
