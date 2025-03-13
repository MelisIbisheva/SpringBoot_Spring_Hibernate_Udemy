package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LuvAopExpressions {

    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    public void forDaoPackage(){}

    //pointcut for getter method
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
    public void getter(){}

    //pointcut for setter method
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
    public void setter(){}

    //create pointcut included package...excluded getter/setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter(){}
}
