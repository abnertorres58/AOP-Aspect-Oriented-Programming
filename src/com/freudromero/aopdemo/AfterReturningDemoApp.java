package com.freudromero.aopdemo;

import com.freudromero.aopdemo.dao.AccountDAO;
import com.freudromero.aopdemo.dao.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterReturningDemoApp {
    public static void main(String[] args) {

        // Read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        // Get the bean from spring container
        AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);

        // Call the method to find the accounts
        List<Account> theAccounts = theAccountDAO.findAccounts();

        // Display the accounts
        System.out.println("\n\nMain Program: AfterReturningDemoApp");
        System.out.println("---------");
        System.out.println(theAccounts);
        System.out.println("\n");


        // Close the context
        context.close();
    }
}
