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
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.GregorianCalendar;

class TimeZona implements ScrollListener{
    private int weidth, heigth;
    private int bias=0;
   
    int width,heoght;
    private Point beginPoint,endPoint;
    private Date begin ,end;
    private BufferedImage image;
    private int weidthMinut;
    private int frequencyTime=60;
    
    public TimeZona(Point beginPoint, Point endPoint,int weidthMinut,Date begin, Date end) {
        this.weidthMinut = weidthMinut;
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
        this.begin=begin;
        this.end=end;
        width=endPoint.x-beginPoint.x;
        heigth=endPoint.y-beginPoint.y;
        createImage();
    }
    public TimeZona(Point beginPoint, Point endPoint,int weidthMinut,Date begin, Date end,int frequencyTime) {
        this.weidthMinut = weidthMinut;
        this.frequencyTime=frequencyTime;
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
        this.begin=begin;
        this.end=end;
        width=endPoint.x-beginPoint.x;
        heigth=endPoint.y-beginPoint.y;
        createImage();
    }
    private void createImage(){
        int min = (int) ((end.getTime()-begin.getTime())/60000);
        int w = min*weidthMinut;
        int wt=endPoint.x-beginPoint.x;
        w=w<wt?wt:w;   
        heigth=endPoint.y-beginPoint.y;
        weidth=w;
        image = new BufferedImage(weidth, heigth, BufferedImage.TYPE_INT_ARGB);
    }
    
    public Image getImage(){
        Graphics2D g = (Graphics2D)image.getGraphics();
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, weidth-1, heigth-1);
        g.setColor(new Color(75,75,75));
        g.fillRect(0, 0, weidth-1, heigth-1);
        g.setColor(new Color(150,150,150));
        int min = (int) ((end.getTime()-begin.getTime())/60000);
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(begin);
        
        for(int x=0,m=0; m<min; m+=frequencyTime, x=m*weidthMinut){
            g.drawLine(x, 0, x, heigth);
            
            AffineTransform orig = g.getTransform();
            g.rotate(-Math.PI/2);
            StringBuilder b=new StringBuilder();
            b.append(c.get(GregorianCalendar.HOUR)).append(":");
            b.append(c.get(GregorianCalendar.MINUTE));
            g.drawString(b.toString(),-heigth+3,x+13);
            g.setTransform(orig);
            
            c.add(GregorianCalendar.MINUTE, frequencyTime);
        }
        
            
        
        return image.getSubimage(bias,0, endPoint.x-beginPoint.x, endPoint.y-beginPoint.y);
    }
   
    public void verticalScroll(int count) {
        throw new UnsupportedOperationException("This element does not support vertical scroll.");
    }
    public void horisontalScroll(int count) {
         bias=count;
    }
    void resize(Point begin, Point end) {
        this.beginPoint = begin;
        this.endPoint = end;
        createImage();
    }

}
