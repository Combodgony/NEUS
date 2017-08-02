package com.segvek.terminal.interactiv;

import com.segvek.terminal.interactiv.model.Admission;
import com.segvek.terminal.interactiv.model.DependencyAdmission;
import com.segvek.terminal.interactiv.model.Estakada;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JPanel;
import javax.swing.Timer;


public class InteractivGrafic extends JPanel implements MouseListener,ComponentListener,ActionListener{
    private int heigthContent=0; //calc variable
    private int weidthContent=0; //calc variable

    private Color backgroundColor = new Color(50,50,50);
    
    
    private int heightTimeZone = 50;
    private int weidthMinut=2;
    
    private int weigthLeftZona=200;
    private int weigthScroll=15;
    
    
    private int heigthLine=20;
    private int indent=10;
    
    
    private ArrayList<Estakada> estakads;
    private ArrayList<Admission> admissions;
    ArrayList<DependencyAdmission> das = new ArrayList<>();
    
    private Date start=new Date(117,7,3,5,0);
    private Date end=  new Date(117,7,3,20,30);
    
    boolean verticalScrollVisible,horizontalScrollVisible;
    private Scroll verticalScroll, horizontalScroll;
    private LeftZona lz;
    private TimeZona timeZona;
    private Content content;

    public InteractivGrafic() {
        Timer tim = new Timer(60000, this);
        tim.start();
    }
  
    
    
    
    public void init(){ 
        calc();
        Point beginLeftZona = new Point(0, heightTimeZone);
        Point endLeftZona = new Point(weigthLeftZona, getHeight()-weigthScroll);
        lz = new LeftZona(beginLeftZona,endLeftZona,heigthLine,indent,estakads);
        
        Point beginVerticalScroll = new Point(getWidth()-weigthScroll,heightTimeZone);
        Point endVertacalScroll = new Point(getWidth(),getHeight()-weigthScroll);
        verticalScroll = new Scroll(this,beginVerticalScroll,endVertacalScroll,heigthContent, Scroll.ORIENTATION_VERTICAL);
        
        Point beginHorizontalScroll = new Point(weigthLeftZona,getHeight()-weigthScroll);
        Point endHorizontalScroll = new Point(getWidth()-(verticalScrollVisible?weigthScroll:0),getHeight());
        horizontalScroll = new Scroll(this,beginHorizontalScroll,endHorizontalScroll,weidthContent, Scroll.ORIENTATION_HORIZONTAL);
        
        Point beginContent = new Point(weigthLeftZona,heightTimeZone);
        Point endContent = new Point(getWidth()-(verticalScrollVisible?weigthScroll:0)
                ,getHeight()-(horizontalScrollVisible?weigthScroll:0));
        content = new Content(this,beginContent, endContent, heigthLine, indent, estakads,weidthMinut,start,end,admissions,60,das);
        
        Point beginTimeZona = new Point(weigthLeftZona,0);
        Point endTimeZona = new Point(getWidth()-(verticalScrollVisible?weigthScroll:0),heightTimeZone);
        timeZona = new TimeZona(beginTimeZona,endTimeZona,weidthMinut,start,end,20);
        
        
        verticalScroll.addListener(lz);
        verticalScroll.addListener(content);
        horizontalScroll.addListener(content);
        horizontalScroll.addListener(timeZona);
        addMouseMotionListener(verticalScroll);
        addMouseMotionListener(horizontalScroll);
        addMouseMotionListener(content);
        addMouseListener(verticalScroll);
        addMouseListener(horizontalScroll);
        addMouseListener(content);
        
        addComponentListener(this);
        addMouseListener(this);
    }
    
    private void calc(){
        heigthContent=0;
        for(int i=0; i<estakads.size(); i++){
            heigthContent+=indent;
            heigthContent+=(estakads.get(i).getDrainLocations().size()+1)*heigthLine;
        }
        heigthContent+=3*indent;
        int minut = (int) ((end.getTime()-start.getTime())/60000);
        weidthContent = minut*weidthMinut;
        
        verticalScrollVisible=heigthContent>getHeight()-heightTimeZone-(horizontalScrollVisible?weigthScroll:0);
        horizontalScrollVisible=weidthContent>getWidth()-weigthLeftZona-(verticalScrollVisible?weigthScroll:0);
    }
    
    

    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        g = (Graphics2D)g;
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(),getHeight());
        g.drawImage(lz.getImage(),0, heightTimeZone,this);    
        g.drawImage(timeZona.getImage(), weigthLeftZona, 0,this);
        if(verticalScrollVisible){
            g.drawImage(verticalScroll.getImage(), getWidth()-weigthScroll, heightTimeZone,this);
        }
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
        
        Point beginTimeZona = new Point(weigthLeftZona,0);
        Point endTimeZona = new Point(getWidth()-(verticalScrollVisible?weigthScroll:0),heightTimeZone);
        timeZona.resize(beginTimeZona, endTimeZona);
    } 

    void setEstakads(ArrayList<Estakada> estakads) {
        this.estakads=estakads;
    }

    void setAdmissions(ArrayList<Admission> admissions) {
        this.admissions=admissions;
    }

    public Content getContent() {
        return content;
    }

    void setDependencyAdmissions(ArrayList<DependencyAdmission> das) {
        this.das=das;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

}

