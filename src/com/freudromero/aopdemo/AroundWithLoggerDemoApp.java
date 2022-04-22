package com.freudromero.aopdemo;

import com.freudromero.aopdemo.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class AroundWithLoggerDemoApp {

    private static Logger myLogger = Logger.getLogger(AroundWithLoggerDemoApp.class.getName());
    public static void main(String[] args) {

        // Read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        // Get the bean from spring container
        TrafficFortuneService theFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

        myLogger.info("\nMain program: AroundDemoApp");
        myLogger.info("calling getFortune");
        String data = theFortuneService.getFortune();
        myLogger.info("\nMy fortune is: " + data);
        myLogger.info("Finished");

        // Close the context
        context.close();
    }
}
