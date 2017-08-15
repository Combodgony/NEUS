
package com.segvek.terminal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Loader {
    private static ApplicationContext context;
    
    static {
        context = new ClassPathXmlApplicationContext("spring.xml");
        Timer tim = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               System.gc();
            }
        });
        tim.start();
    }
    
    public static ApplicationContext getContext(){
        return context;
    }
}
