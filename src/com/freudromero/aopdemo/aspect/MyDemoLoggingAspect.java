package com.freudromero.aopdemo.aspect;


import com.freudromero.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    // Add new advice for @AfterReturning on the findAccounts method
    @AfterReturning(
            pointcut = "execution(* com.freudromero.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJointPoint, List<Account> result){
        // Print out which method are we advising on
        String method = theJointPoint.getSignature().toShortString();
        System.out.println("\n===========> Executing @AfterReturning on method: " + method);

        // Print out the results of the method call
        System.out.println("\n===========> Result is: " + result);

        // Let's post-process the data ... let's modify it

        // Convert the account names to uppercase
        convertAccountNamesToUpperCase(result);

        System.out.println("\n===========> Result is: " + result);

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
        System.out.println("\n======>>>> executing @Before advice on addAccount()");

        // Display the method signature
        MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();

        System.out.println("Method: " + methodSig);

        // Display method arguments

        // Get the args
        Object[] args = theJoinPoint.getArgs();

        // Loop through args
        for(Object tempArg : args) {
            System.out.println(tempArg);

            if(tempArg instanceof Account) {

                // Downcast and print Account Specific stuffs
                Account theAccount = (Account) tempArg;

                System.out.println("account name: " + theAccount.getName());
                System.out.println("account level: " + theAccount.getLevel());
            }
        }
    }
}
