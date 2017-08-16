/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segvek.terminal.gui.tab.interactiv;

import com.segvek.terminal.model.Estakada;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

class LeftZona implements ScrollListener{
    private int weidth, heigth;
    private int heigthLine;
    private int indent;
    private int bias=0;
    
    private List<Estakada> estakads;
    
    
    private Point begin;
    private Point end;
    

    private BufferedImage image;
    
    public LeftZona(Point begin, Point end,int heigthLine, int indent,List<Estakada> estakads) {
        this.begin = begin;
        this.end = end;
        this.estakads=estakads;
        this.heigthLine=heigthLine;
        this.indent=indent;
        createImage();
    }
    private void createImage(){
        if(image!=null){
            image.getGraphics().dispose();
//            System.gc();
        }
        int h=0;
        for(int i=0; i<estakads.size(); i++){
            h+=indent;
            h+=(estakads.get(i).getDrainLocations().size()+1)*heigthLine;
        }
        h+=3*indent;
        int ht=end.y-begin.y;
        h=h<ht?ht:h;
        weidth=end.x-begin.x;
        heigth=h;
        image = new BufferedImage(weidth, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D)image.getGraphics();
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, weidth-1, heigth-1);
        g.setColor(new Color(75,75,75));
        g.fillRect(0, 0, weidth-1, heigth-1);
        g.setColor(new Color(150,150,150));
        for(int i=0,y=0; i<estakads.size(); i++){
            y+=indent+heigthLine;
            g.drawLine(0, y, weidth, y);
            g.drawString(estakads.get(i).getTypeEstakada().getName()+" естакада:"+estakads.get(i).getName(), 20, y-5);
            for(int j=0; j<estakads.get(i).getDrainLocations().size(); j++){
                y+=heigthLine;
                g.drawLine(20, y, weidth, y);
                g.drawString("место:"+estakads.get(i).getDrainLocations().get(j).getNumber(), 40, y-5);
            }
        }
    }
    
    public Image getImage(){
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
