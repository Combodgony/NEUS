/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segvek.terminal.interactiv;

import com.segvek.terminal.interactiv.model.Estakada;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;

class Content implements ScrollListener{
    private int weidth, heigth;
    private int heigthLine;
    private int indent;
    private int biasY=0;
    private int biasX=0;
    
    private ArrayList<Estakada> estakads;
    
    
    private Point beginPoint,endPoint; 
    private Date begin, end;
    private int weidthMinut;
    int frequencyTime=60;
    private BufferedImage image;
    
    public Content(Point beginPoint, Point endPoint,int heigthLine, int indent,ArrayList<Estakada> estakads,int weidthMinut,Date begin, Date end) {
        this.begin=begin;
        this.end=end;
        this.weidthMinut=weidthMinut;
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
        this.estakads=estakads;
        this.heigthLine=heigthLine;
        this.indent=indent;
        createImage();
    }
    public Content(Point beginPoint, Point endPoint,int heigthLine, int indent,ArrayList<Estakada> estakads,int weidthMinut,Date begin, Date end,int frequencyTime) {
        this.frequencyTime=frequencyTime;
        this.begin=begin;
        this.end=end;
        this.weidthMinut=weidthMinut;
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
        this.estakads=estakads;
        this.heigthLine=heigthLine;
        this.indent=indent;
        createImage();
    }
   
    private void createImage(){
        int h=0;
        for(int i=0; i<estakads.size(); i++){
            h+=indent;
            h+=(estakads.get(i).getDrainLocations().size()+1)*heigthLine;
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
        for(int i=0,y=0; i<estakads.size(); i++){
            y+=indent+heigthLine;
            g.drawLine(0, y, weidth, y);
            for(int j=0; j<estakads.get(i).getDrainLocations().size(); j++){
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
    public void resize(Point begin, Point end) {
        this.beginPoint = begin;
        this.endPoint = end;
        createImage();
    }

}
