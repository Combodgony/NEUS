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
import java.awt.image.BufferedImage;
import java.util.Date;

class Content implements ScrollListener{
    private int weidth, heigth;
    private int heigthLine;
    private int indent;
    private int biasY=0;
    private int biasX=0;
    
    private int estacad[];
    
    
    private Point beginPoint,endPoint; 
    private Date begin, end;
    private int weidthMinut;
    int frequencyTime=60;
    private BufferedImage image;
    
    public Content(Point beginPoint, Point endPoint,int heigthLine, int indent,int estacad[]
                                                ,int weidthMinut,Date begin, Date end) {
        this.begin=begin;
        this.end=end;
        this.weidthMinut=weidthMinut;
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
        this.estacad=estacad;
        this.heigthLine=heigthLine;
        this.indent=indent;
        createImage();
    }
    public Content(Point beginPoint, Point endPoint,int heigthLine, int indent,int estacad[]
                                                ,int weidthMinut,Date begin, Date end,int frequencyTime) {
        this.frequencyTime=frequencyTime;
        this.begin=begin;
        this.end=end;
        this.weidthMinut=weidthMinut;
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
        this.estacad=estacad;
        this.heigthLine=heigthLine;
        this.indent=indent;
        createImage();
    }
    private void createImage(){
        int h=0;
        for(int i=0; i<estacad.length; i++){
            h+=indent;
            h+=(estacad[i]+1)*heigthLine;
        }
        int ht=endPoint.y-beginPoint.y;
        h=h<ht?ht:h;
        heigth=h;
        
        int min = (int) ((end.getTime()-begin.getTime())/60000);
        int w = min*weidthMinut;
        int wt=endPoint.x-beginPoint.x;
        w=w<wt?wt:w;   
        weidth=w;
        image = new BufferedImage(weidth, heigth, BufferedImage.TYPE_INT_ARGB);
    }
    
    public Image getImage(){
        Graphics2D g = (Graphics2D)image.getGraphics();

        g.setColor(new Color(85,85,85));
        for(int i=0,y=0; i<estacad.length; i++){
            y+=indent+heigthLine;
            g.drawLine(0, y, weidth, y);
            for(int j=0; j<estacad[i]; j++){
                y+=heigthLine;
                g.drawLine(0, y, weidth, y);
            }
        }
        int min = (int) ((end.getTime()-begin.getTime())/60000);
        for(int x=0,m=0; m<min; m+=frequencyTime, x=m*weidthMinut){
            g.drawLine(x, 0, x, heigth);
        }
        return image.getSubimage(biasX,biasY, endPoint.x-beginPoint.x, endPoint.y-beginPoint.y);
    }
   
    public void verticalScroll(int count) {
        biasY=count;
    }
    public void horisontalScroll(int count) {
        biasX=count;
    }
    
    void resize(Point begin, Point end) {
        this.beginPoint = begin;
        this.endPoint = end;
        createImage();
    }

}
