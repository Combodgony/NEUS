package com.segvek.terminal.interactiv;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Date;
import javax.swing.JPanel;


public class InteractivGrafic extends JPanel implements MouseMotionListener, MouseListener,ComponentListener{
    private int heigthContent=0; //calc variable
    private int weidthContent=800; //calc variable
    
  
    private int heightTimeZone = 50;
    private int weigthLeftZona=200;
    private int weigthScroll=15;
    
    
    private int heigthLine=20;
    private int indent=10;
    int estacad[] = {4,4,2};
    
    
    private Date start;
    private Date end;
    
    boolean verticalScrollVisible,horizontalScrollVisible;
    private Scroll verticalScroll, horizontalScroll;
    private LeftZona lz;
    Content content;

    public InteractivGrafic() {
        for(int i=0; i<estacad.length; i++){
            heigthContent+=indent;
            heigthContent+=(estacad[i]+2)*heigthLine;
        }
    }
    
    
    
    public void init(){ 
        calc();
        Point beginLeftZona = new Point(0, heightTimeZone);
        Point endLeftZona = new Point(weigthLeftZona, getHeight()-weigthScroll);
        lz = new LeftZona(beginLeftZona,endLeftZona,heigthLine,indent,estacad);
        
        Point beginVerticalScroll = new Point(getWidth()-weigthScroll,heightTimeZone);
        Point endVertacalScroll = new Point(getWidth(),getHeight()-weigthScroll);
        verticalScroll = new Scroll(beginVerticalScroll,endVertacalScroll,heigthContent, Scroll.ORIENTATION_VERTICAL);
        
        Point beginHorizontalScroll = new Point(weigthLeftZona,getHeight()-weigthScroll);
        Point endHorizontalScroll = new Point(getWidth()-(verticalScrollVisible?weigthScroll:0),getHeight());
        horizontalScroll = new Scroll(beginHorizontalScroll,endHorizontalScroll,weidthContent, Scroll.ORIENTATION_HORIZONTAL);
        
        Point beginContent = new Point(weigthLeftZona,heightTimeZone);
        Point endContent = new Point(getWidth()-(verticalScrollVisible?weigthScroll:0)
                ,getHeight()-(horizontalScrollVisible?weigthScroll:0));
        
        content = new Content(beginContent, endContent, heigthLine, indent, estacad);
        
        
        verticalScroll.addListener(lz);
        verticalScroll.addListener(content);
        addMouseMotionListener(verticalScroll);
        addMouseMotionListener(horizontalScroll);
        addMouseListener(verticalScroll);
        addMouseListener(horizontalScroll);
        addComponentListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
    }
    
    private void calc(){
        heigthContent=0;
        for(int i=0; i<estacad.length; i++){
            heigthContent+=indent;
            heigthContent+=(estacad[i]+1)*heigthLine;
        }
        System.out.println("lenghtPanel: "+heigthContent);
        verticalScrollVisible=heigthContent>getHeight()-heightTimeZone-(horizontalScrollVisible?weigthScroll:0);
        horizontalScrollVisible=weidthContent>getWidth()-weigthLeftZona-(verticalScrollVisible?weigthScroll:0);
    }
    
    

    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        g = (Graphics2D)g;
        g.setColor(new Color(50,50,50));
        g.fillRect(0, 0, getWidth(),getHeight());
        
        g.drawImage(lz.getImage(),0, heightTimeZone,this);    
        
        //time zona
        g.setColor(Color.blue);
        g.drawRect(weigthLeftZona, 0, getWidth()-weigthLeftZona-1-(verticalScrollVisible?weigthScroll:0), heightTimeZone);
            
        //verticalScroll
        if(verticalScrollVisible){
            g.drawImage(verticalScroll.getImage(), getWidth()-weigthScroll, heightTimeZone,this);
        }
        //bottom scroll
        if(horizontalScrollVisible){
            g.drawImage(horizontalScroll.getImage(), weigthLeftZona, getHeight()-weigthScroll,this);
        }
        
        g.drawImage(content.getImage(), weigthLeftZona, heightTimeZone,this);
        
    }  

   
    public void componentMoved(ComponentEvent e) {}
    public void componentShown(ComponentEvent e) {}
    public void componentHidden(ComponentEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {
        repaint();
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        repaint();
    } 
    @Override
    public void mousePressed(MouseEvent e) {
        repaint();
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        repaint();
    }
    @Override
    public void componentResized(ComponentEvent e) {
        calc();
        Point beginLeftZona = new Point(0, heightTimeZone);
        Point endLeftZona = new Point(weigthLeftZona, getHeight()-(horizontalScrollVisible?weigthScroll:0));
        lz.resize(beginLeftZona,endLeftZona);
              
        Point beginVerticalScroll = new Point(getWidth()-weigthScroll,heightTimeZone);
        Point endVertacalScroll = new Point(getWidth(),getHeight()-(horizontalScrollVisible?weigthScroll:0));
        verticalScroll.resize(beginVerticalScroll,endVertacalScroll,heigthContent);

                
        Point beginHorizontalScroll = new Point(weigthLeftZona,getHeight()-weigthScroll);
        Point endHorizontalScroll = new Point(getWidth()-(verticalScrollVisible?weigthScroll:0),getHeight());
        horizontalScroll.resize(beginHorizontalScroll,endHorizontalScroll,weidthContent);
        
        Point beginContent = new Point(weigthLeftZona,heightTimeZone);
        Point endContent = new Point(getWidth()-(verticalScrollVisible?weigthScroll:0)
                ,getHeight()-(horizontalScrollVisible?weigthScroll:0));
        content.resize(beginContent,endContent);
    } 
}

