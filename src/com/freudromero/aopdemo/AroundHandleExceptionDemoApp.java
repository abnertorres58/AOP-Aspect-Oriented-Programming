package com.freudromero.aopdemo;

import com.freudromero.aopdemo.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class AroundHandleExceptionDemoApp {

    private static Logger myLogger = Logger.getLogger(AroundHandleExceptionDemoApp.class.getName());
    public static void main(String[] args) {

        // Read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        // Get the bean from spring container
        TrafficFortuneService theFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

        myLogger.info("\nMain program: AroundDemoApp");
        myLogger.info("calling getFortune");

        boolean tripWire = true;
        String data = theFortuneService.getFortune(tripWire);
        myLogger.info("\nMy fortune is: " + data);
        myLogger.info("Finished");

        // Close the context
        context.close();
    }
}
