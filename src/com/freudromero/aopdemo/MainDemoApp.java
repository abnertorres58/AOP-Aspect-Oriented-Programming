package com.freudromero.aopdemo;

import com.freudromero.aopdemo.dao.AccountDAO;
import com.freudromero.aopdemo.dao.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {
    public static void main(String[] args) {

        // Read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        // Get the bean from spring container
        AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);

        // Get membership bean from spring container
        MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);

        // Call the business method
        Account myAccount = new Account();
        theAccountDAO.addAccount(myAccount, true);

        // Call the membership business method
        theMembershipDAO.addSillyMember();

        // Close the context
        context.close();
    }
}
