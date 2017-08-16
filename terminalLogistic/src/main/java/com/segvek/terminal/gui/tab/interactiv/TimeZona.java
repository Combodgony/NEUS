/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segvek.terminal.gui.tab.interactiv;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

class TimeZona implements ScrollListener{
    private int weidth, heigth;
    private int bias=0;
    private int k=6; //коефициент необходм для регулировки частоты отображаемых линий времени
    private Point beginPoint,endPoint;
    private Date begin ,end;
    private BufferedImage image;
    private double weidthMinut;
    private int frequencyTime=60;
    

    public TimeZona(Point beginPoint, Point endPoint,double weidthMinut,Date begin, Date end,int frequencyTime) {
        this.weidthMinut = weidthMinut;
        this.frequencyTime=frequencyTime;
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
        this.begin=begin;
        this.end=end;
        weidth=endPoint.x-beginPoint.x;
        heigth=endPoint.y-beginPoint.y;
        createImage();
    }
    private void createImage(){
        double min =(end.getTime()-begin.getTime())/60000;
        int w = (int)(min*weidthMinut);
        int wt=endPoint.x-beginPoint.x;
        w=w<wt?wt:w;   
        heigth=endPoint.y-beginPoint.y;
        weidth=w;
        image = new BufferedImage(endPoint.x-beginPoint.x, heigth, BufferedImage.TYPE_INT_ARGB);
        
        if(weidthMinut>1) k=1;
        else if(weidthMinut>0.3) k=3;
        else k=6;
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
        
        g.drawLine(0, 14, weidth, 14);
        
        GregorianCalendar t = new GregorianCalendar();
        //todo нужна оптимизаци, здесь происходит перебор всез значений времени на указом периоде 
        //и отображаються только те которіе попадают в оботражаемій отрезок.
        //необходимо создать формулу подсч'та начала отображение и избавиться от полного перебора 
        for(int x=0,m=0; m<min; m+=frequencyTime*k, x=(int)(m*weidthMinut)){
            if(x>bias && x<bias+(endPoint.x-beginPoint.x)){
                g.drawLine(x-bias%(int)(m*weidthMinut), 14, x-bias%(int)(m*weidthMinut), heigth);
                AffineTransform orig = g.getTransform();
                g.rotate(-Math.PI/2);
                StringBuilder b=new StringBuilder();
                b.append(c.get(GregorianCalendar.HOUR_OF_DAY)).append(":");
                b.append(c.get(GregorianCalendar.MINUTE));
                g.drawString(b.toString(),-heigth+3,x+13-bias%(int)(m*weidthMinut));
                g.setTransform(orig); 
                
                t.setTime(c.getTime());
                t.add(GregorianCalendar.MINUTE, frequencyTime*k);
                if(c.get(GregorianCalendar.DATE)!=t.get(GregorianCalendar.DATE)){
                    StringBuilder sb = new StringBuilder().append(t.get(GregorianCalendar.DAY_OF_MONTH))
                            .append(".").append(t.get(GregorianCalendar.MONTH)+1)
                            .append(".").append(t.get(GregorianCalendar.YEAR));
                    g.drawString(sb.toString(), (int)(x+(frequencyTime*k*weidthMinut)-bias%(int)((m)*weidthMinut)+20), 12);
                }
            }
            
            c.add(GregorianCalendar.MINUTE, frequencyTime*k);
        }
        
        Date now = new Date();
        if(begin.getTime()<now.getTime() && end.getTime()>now.getTime()){
            long minut = (now.getTime()-begin.getTime())/60000;
            g.setColor(Color.red);
            g.drawLine((int)(minut*weidthMinut)-bias, 0, (int)(minut*weidthMinut)-bias, heigth);
        }
        
        
        return image;//.getSubimage(0,0, endPoint.x-beginPoint.x, endPoint.y-beginPoint.y);
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TimeZona other = (TimeZona) obj;
        if (this.weidth != other.weidth) {
            return false;
        }
        if (this.heigth != other.heigth) {
            return false;
        }
        if (this.bias != other.bias) {
            return false;
        }
        if (this.k != other.k) {
            return false;
        }
        if (Double.doubleToLongBits(this.weidthMinut) != Double.doubleToLongBits(other.weidthMinut)) {
            return false;
        }
        if (this.frequencyTime != other.frequencyTime) {
            return false;
        }
        if (!Objects.equals(this.beginPoint, other.beginPoint)) {
            return false;
        }
        if (!Objects.equals(this.endPoint, other.endPoint)) {
            return false;
        }
        if (!Objects.equals(this.begin, other.begin)) {
            return false;
        }
        if (!Objects.equals(this.end, other.end)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        return true;
    }

    
}
