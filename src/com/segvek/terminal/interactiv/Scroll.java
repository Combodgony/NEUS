/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segvek.terminal.interactiv;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Panas
 */
public class Scroll extends MouseAdapter{
    
    public static final boolean ORIENTATION_VERTICAL=true;
    public static final boolean ORIENTATION_HORIZONTAL=false;
    private boolean vertical=true;
    
    private int bias=0; //calc field
    private double sliderLength=0; //calc field
    
    private JPanel ig;
    
    
    int sizeScroll=15;
    int weidth, height;
    private int lengthPanel=0;
    

    private BufferedImage image;
    
    private Color colorBackground = new Color(75,75,75);
    private Color colorActiv = new Color(150,150,150);
    private Color colorMove = new Color(200,200,200);
    private Color colorPasive = new Color(100,100,100);
    private Color colorScroll = new Color(100,100,100);
    
    private Point begin;
    private Point end;
    Scroll(JPanel ig,Point begin,Point end,int lengthPanel,boolean scrollType){
        this.ig=ig;
        this.begin=begin;
        this.end=end;
        this.lengthPanel=lengthPanel;
        this.vertical=scrollType;
        int lengthScroll;    
        if(vertical){
            lengthScroll=end.y-begin.y;
            weidth=sizeScroll;
            height=lengthScroll;
        }else{
            lengthScroll=end.x-begin.x;
            weidth=lengthScroll;
            height=sizeScroll;
        }
        sliderLength=(int)(Math.pow(lengthScroll, 2)/lengthPanel);
        image=new BufferedImage(weidth, height, BufferedImage.TYPE_INT_ARGB);
    }
    ArrayList<ScrollListener> clients = new ArrayList<>();
    
    public void addListener(ScrollListener listener){
        clients.add(listener);
    }
    public void notifyCustomers(){
        if(vertical){
            int biasClient=(lengthPanel*bias)/(height);
            for(ScrollListener cost:clients){
                cost.verticalScroll(biasClient);
            }            
        }else{
            int biasClient=(lengthPanel*bias)/(weidth);
            for(ScrollListener cost:clients){
                cost.horisontalScroll(biasClient);
            }
        }
    }
    
    public Image getImage(){
        Graphics2D g = (Graphics2D)image.getGraphics();
        g.setColor(colorBackground);
        g.fillRect(0, 0, weidth, height);
        g.setColor(colorScroll);
        if(vertical){
            g.fillRect(1, bias, sizeScroll-3,(int)sliderLength);
        }else{
            g.fillRect(bias, 1, (int)sliderLength,sizeScroll-3);
        }
        return image;
    }
    
    
    private static BufferedImage createTransformed(BufferedImage image, AffineTransform at){
        BufferedImage newImage = new BufferedImage( image.getWidth(), image.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.transform(at);
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }    


    void resize(Point begin,Point end,int lengthPanel) {
        bias=0; 
        notifyCustomers();
        this.lengthPanel=lengthPanel;
        this.end=end;
        this.begin=begin;
        int lengthScroll;    
        if(vertical){
            lengthScroll=end.y-begin.y;
            weidth=sizeScroll;
            height=lengthScroll;
        }else{
            lengthScroll=end.x-begin.x;
            weidth=lengthScroll;
            height=sizeScroll;
        }
        sliderLength=(int)(Math.pow(lengthScroll, 2)/lengthPanel);
        image=new BufferedImage(weidth, height, BufferedImage.TYPE_INT_ARGB);
    }

    
    //===================This block need for moved scroll ==================//
    private Point pressedPoint;
    
    @Override
    public void mouseMoved(MouseEvent e) {
        //проверка надохится ли мишь на позунке вертикального или горизонтального скрола
        if((vertical && e.getX()>begin.x+1 && e.getX()<end.x-2 && e.getY() > begin.y+bias && e.getY()<begin.y+bias+sliderLength) 
            || (!vertical && e.getX()>begin.x+bias && e.getX()<begin.x+sliderLength+bias &&e.getY()>begin.y+1 && e.getY()<end.y-2)){
            if(colorScroll!=colorActiv){
                colorScroll = colorActiv;
                ig.repaint();
            }
        }else{
            if(colorScroll==colorActiv){
                colorScroll=colorPasive;
                ig.repaint();
            }
        }  
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if(vertical && e.getX()>begin.x+1 && e.getX()<end.x-2 && e.getY() > begin.y+bias 
                && e.getY()<begin.y+bias+sliderLength){
            pressedPoint=e.getPoint();
            colorScroll = colorMove;
        }else{
            if(!vertical && e.getX()>begin.x+bias && e.getX()<begin.x+sliderLength+bias 
                    &&e.getY()>begin.y+1 && e.getY()<end.y-2){
                pressedPoint=e.getPoint();
                colorScroll = colorMove;
            }else{
                pressedPoint=null;
                colorScroll=colorPasive;
            }  
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if(vertical && e.getX()>begin.x+1 && e.getX()<end.x-2 && e.getY() > begin.y+bias 
                && e.getY()<begin.y+bias+sliderLength){
            colorScroll = colorActiv;
        }else{
            if(!vertical && e.getX()>begin.x+bias && e.getX()<begin.x+sliderLength+bias 
                    &&e.getY()>begin.y+1 && e.getY()<end.y-2){
                colorScroll = colorActiv;
            }else{
                colorScroll=colorPasive;
            }  
        }
        pressedPoint=null;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if(pressedPoint!=null){
            if(vertical){
                bias+=e.getY()-pressedPoint.y;
                int biasClient=(lengthPanel*bias)/(height);
                if(bias>height-sliderLength || biasClient>lengthPanel-height){
                    bias-=e.getY()-pressedPoint.y;
                    return;
                }
                if(bias<0){
                    bias-=e.getY()-pressedPoint.y;
                    return;
                }
                pressedPoint.y=e.getY();
                ig.repaint();
            }else{
                bias+=e.getX()-pressedPoint.x;
                int biasClient=(lengthPanel*bias)/(weidth);
                if(bias>weidth-sliderLength || biasClient>lengthPanel-weidth){
                    bias-=e.getX()-pressedPoint.x;
                    return;
                }
                if(bias<0){
                    bias-=e.getX()-pressedPoint.x;
                    return;
                }
                pressedPoint.x=e.getX();
                ig.repaint();
            }
            notifyCustomers();
        }
    }    

    void removeAllListener() {
        clients.clear();
    }
}
