package com.artcode.myproject.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Aspect
public class Transaction {

    @Around(value = "@annotation(com.artcode.myproject.aspect.Transactional) && @args(entityManager,..)", argNames = "joinPoint,entityManager")
    public Object wrapTransaction(ProceedingJoinPoint joinPoint, EntityManager entityManager) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Object obj = joinPoint.proceed();
            transaction.commit();
            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.clear();
        }
        return null;
    }
}
