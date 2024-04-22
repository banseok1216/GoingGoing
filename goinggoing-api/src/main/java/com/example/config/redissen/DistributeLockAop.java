//package com.example.goinggoing.config.redissen;
//
//import com.example.config.redis.config.CustomSpringELParser;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//
//@Aspect
//@Component
//@RequiredArgsConstructor
//@Slf4j
//@Order(value = 1)
//public class DistributeLockAop {
//    private static final String REDISSON_KEY_PREFIX = "RLOCK_";
//
//    private final RedissonClient redissonClient;
//    private final AopForTransaction aopForTransaction;
//
//
//    @Around("@annotation(com.example.goinggoing.config.redissen.DistributeLock)")
//    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        DistributeLock distributeLock = method.getAnnotation(DistributeLock.class);
//
//        String key = REDISSON_KEY_PREFIX + CustomSpringELParser.getDynamicValue(signature.getParameterNames(), joinPoint.getArgs(), distributeLock.key());
//
//        RLock rLock = redissonClient.getLock(key);
//
//        try {
//            boolean available = rLock.tryLock(distributeLock.waitTime(), distributeLock.leaseTime(), distributeLock.timeUnit());    // (4)
//            if (!available) {
//                return false;
//            }
//
//            log.info("get lock success {}" , key);
//            return aopForTransaction.proceed(joinPoint);
//        } catch (Exception e) {
//            Thread.currentThread().interrupt();
//            throw new InterruptedException();
//        } finally {
//            rLock.unlock();
//        }
//    }
//}
//
