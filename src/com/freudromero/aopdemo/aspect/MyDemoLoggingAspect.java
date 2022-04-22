package com.freudromero.aopdemo.aspect;


import com.freudromero.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    private Logger myLogger = Logger.getLogger(getClass().getName());

    @Around("execution(* com.freudromero.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{

        // Print out which method are we advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        myLogger.info("\n===========> Executing @Around on method: " + method);

        // Get begin timestamp
        long begin = System.currentTimeMillis();

        // Now, let's execute the method
        Object result = null;

        try {
            result = theProceedingJoinPoint.proceed();
        } catch (Exception e) {
           // Log the exception
            myLogger.warning(e.getMessage());

           // Rethrow the exception
           throw e;
        }

        // Get the end timestamp
        long end = System.currentTimeMillis();

        // Compute duration and display it
        long duration = end - begin;
        myLogger.info("\n=============> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }

    @After("execution(* com.freudromero.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJointPoint) {

        // Print out which method are we advising on
        String method = theJointPoint.getSignature().toShortString();
        myLogger.info("\n===========> Executing @After (finally) on method: " + method);
    }

    @AfterThrowing(
            pointcut = "execution(* com.freudromero.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc")
    public void afterThrowingFindAccountsAdvice(JoinPoint theJointPoint, Throwable theExc) {

        // Print out which method are we advising on
        String method = theJointPoint.getSignature().toShortString();
        myLogger.info("\n===========> Executing @AfterTrowing on method: " + method);

        // Log the exception
        myLogger.info("\n===========> The exception is: " + theExc);

    }

    @AfterReturning(
            pointcut = "execution(* com.freudromero.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJointPoint, List<Account> result){
        // Print out which method are we advising on
        String method = theJointPoint.getSignature().toShortString();
        myLogger.info("\n===========> Executing @AfterReturning on method: " + method);

        // Print out the results of the method call
        myLogger.info("\n===========> Result is: " + result);

        // Let's post-process the data ... let's modify it

        // Convert the account names to uppercase
        convertAccountNamesToUpperCase(result);

        myLogger.info("\n===========> Result is: " + result);

    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        // Loop through accounts
        for(Account tempAccount : result) {
            // Get uppercase version of name
            // Update the name on the account
            tempAccount.setName(tempAccount.getName().toUpperCase());
        }
    }


    @Before("com.freudromero.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public  void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        myLogger.info("\n======>>>> executing @Before advice on addAccount()");

        // Display the method signature
        MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();

        myLogger.info("Method: " + methodSig);

        // Display method arguments

        // Get the args
        Object[] args = theJoinPoint.getArgs();

        // Loop through args
        for(Object tempArg : args) {
            myLogger.info(tempArg.toString());

            if(tempArg instanceof Account) {

                // Downcast and print Account Specific stuffs
                Account theAccount = (Account) tempArg;

                myLogger.info("account name: " + theAccount.getName());
                myLogger.info("account level: " + theAccount.getLevel());
            }
        }
    }
}
