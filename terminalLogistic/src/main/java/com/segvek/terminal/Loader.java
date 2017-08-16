
package com.segvek.terminal;

import javax.swing.Timer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Loader {
    private static final ApplicationContext CONTEXT;
    
    static {
        CONTEXT = new ClassPathXmlApplicationContext("spring.xml");
        new Timer(1000,e->System.gc()).start();
    }
    
    public static ApplicationContext getContext(){
        return CONTEXT;
    }
}
