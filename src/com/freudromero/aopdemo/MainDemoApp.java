package com.freudromero.aopdemo;

import com.freudromero.aopdemo.dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {
    public static void main(String[] args) {

        // Read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        // Get the bean from spring container
        AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);

        // Call the business method
        theAccountDAO.addAccount();

        // Do it again!
        System.out.println("\nLet's call it again\n");

        // Call the business method again
        theAccountDAO.addAccount();

        // Close the context
        context.close();
    }
}
