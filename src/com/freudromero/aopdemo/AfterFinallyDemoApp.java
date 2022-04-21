package com.freudromero.aopdemo;

import com.freudromero.aopdemo.dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterFinallyDemoApp {
    public static void main(String[] args) {

        // Read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        // Get the bean from spring container
        AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);

        // Call the method to find the accounts
        List<Account> theAccounts = null;

        try {
            // Add a boolean flag to simulate exceptions
            boolean tripWire = false;
            theAccounts = theAccountDAO.findAccounts(tripWire);
        } catch (Exception exc) {
            System.out.println("\n\nMain Program ... caught exception: " + exc);
        }


        // Display the accounts
        System.out.println("\n\nMain Program: AfterThrowingDemoApp");
        System.out.println("---------");
        System.out.println(theAccounts);
        System.out.println("\n");


        // Close the context
        context.close();
    }
}
