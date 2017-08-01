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

class LeftZona implements ScrollListener{
    private int weidth, heigth;
    private int heigthLine;
    private int indent;
    private int bias=0;
    
    private int estacad[];
    
    
    private Point begin;
    private Point end;
    

    private BufferedImage image;
    
    public LeftZona(Point begin, Point end,int heigthLine, int indent,int estacad[]) {
        this.begin = begin;
        this.end = end;
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
        int ht=end.y-begin.y;
        h=h<ht?ht:h;
        weidth=end.x-begin.x;
        heigth=h;
        image = new BufferedImage(weidth, h, BufferedImage.TYPE_INT_ARGB);
    }
    
    public Image getImage(){
        Graphics2D g = (Graphics2D)image.getGraphics();
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, weidth-1, heigth-1);
        g.setColor(new Color(75,75,75));
        g.fillRect(0, 0, weidth-1, heigth-1);
        g.setColor(new Color(150,150,150));
        for(int i=0,y=0; i<estacad.length; i++){
            y+=indent+heigthLine;
            g.drawLine(0, y, weidth, y);
            g.drawString("естакада:"+i, 20, y-5);
            for(int j=0; j<estacad[i]; j++){
                y+=heigthLine;
                g.drawLine(20, y, weidth, y);
                g.drawString("место:"+j, 40, y-5);
            }
        }
        return image.getSubimage(0,bias, end.x-begin.x, end.y-begin.y);
    }
   
    public void verticalScroll(int count) {
        bias=count;
    }
    public void horisontalScroll(int count) {
        throw new UnsupportedOperationException("This element does not support horizontal scroll."); 
    }
    
    void resize(Point begin, Point end) {
        this.begin = begin;
        this.end = end;
        createImage();
    }

}
