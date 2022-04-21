package com.freudromero.aopdemo;

import com.freudromero.aopdemo.dao.AccountDAO;
import com.freudromero.aopdemo.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AroundDemoApp {
    public static void main(String[] args) {

        // Read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        // Get the bean from spring container
        TrafficFortuneService theFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

        System.out.println("\nMain program: AroundDemoApp");
        System.out.println("calling getFortune");
        String data = theFortuneService.getFortune();
        System.out.println("\nMy fortune is: " + data);
        System.out.println("Finished");

        // Close the context
        context.close();
    }
}
