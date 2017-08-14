
package com.segvek.terminal;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Loader {
    private static ApplicationContext context;
    
    static {
        context = new ClassPathXmlApplicationContext("spring.xml");
    }
    
    public static ApplicationContext getContext(){
        return context;
    }
}
