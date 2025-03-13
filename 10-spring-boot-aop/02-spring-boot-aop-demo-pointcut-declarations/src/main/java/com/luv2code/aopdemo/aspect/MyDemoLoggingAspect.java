package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    //This is where we add all of our related advices for logging
    // let's start with an @Before advice

    //Add a new advice for @AfterReturning on the findAccounts Method

    @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint proceedingJoinPoint) throws  Throwable{

        //print out which method we are advising on
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>>> Executing @Around on method: " + method);

        //get begin timestamp
        //long begin = System.currentTimeMillis();
        long begin = System.nanoTime();

        //now let's execute the method
        Object result = null;
        try {
          result = proceedingJoinPoint.proceed();
        }catch (Exception exc){
            //log the exception
            System.out.println(exc.getMessage());
            // give user a custom message
            //result = "Major accident! But no worries, your private AOP helicopter is on the way.";

            //rethrow exception
            throw exc;
        }

        //get end timestamp
        //long end =  System.currentTimeMillis();
        long end =  System.nanoTime();

        //compute duration and display it
        long duration = begin - end;
        System.out.println("\n=====>>>> Duration: " + duration + " nanoseconds");
        return result;
    }



    @After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint){

        //print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>>> Executing @After (Finally) on method: " + method);
    }

    @AfterThrowing(
            pointcut ="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing ="theExc")
    public void afterThrowingFindAccount(JoinPoint theJoinPoint, Throwable theExc){

        //print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>>> Executing @AfterThrowing on method: " + method);

        //log the exception
        System.out.println("\n=====>>>> The exception is: " + theExc);
    }

    @AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result){

        //print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>>> Executing @AfterReturning on method: " + method);

        // print out the result of the method call
        System.out.println("\n=====>>>> result is: " + result);

        //let's post-process the data...let's modify it

        //convert the account names to uppercase
        convertAccountNamesToUpperCase(result);
        System.out.println("\n=====>>>> result is: " + result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        //loop through accounts
        for (Account tempAccount : result) {

            //get uppercase version of name
            String theUpperName = tempAccount.getName().toUpperCase();

            //update the name on the account
            tempAccount.setName(theUpperName);
        }
    }


    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint){
        System.out.println("\n=====>>>> Executing @Before advice on method");

        //display a method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        //display method arguments

        //get args
        Object [] args = theJoinPoint.getArgs();

        //loop thru args
        for(Object tempArg : args){
            System.out.println(tempArg);
            if(tempArg instanceof Account){
                //downcast and print Account specific stuff
                Account theAccount = (Account) tempArg;
                System.out.println("Account name: " + theAccount.getName());
                System.out.println("Account level: " + theAccount.getLevel());
            }
        }



    }

}
