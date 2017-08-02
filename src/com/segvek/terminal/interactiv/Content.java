package com.segvek.terminal.interactiv;

import com.segvek.terminal.interactiv.model.Admission;
import com.segvek.terminal.interactiv.model.Estakada;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JPanel;

class Content implements ScrollListener, MouseListener,MouseMotionListener,KeyListener{
    private JPanel ig; //необходимо для оптимизации работы
    private int weidth, heigth;
    private int heigthLine;
    private int indent;
    private int biasY=0;
    private int biasX=0;
    
    private ArrayList<Estakada> estakads;
    private ArrayList<Admission> admissions;
    private Admission activAdmission=null;
    
    
    private Point beginPoint,endPoint; 
    private Date begin, end;
    private int weidthMinut;
    int frequencyTime=60;
    private BufferedImage image;
    
    private boolean jump = false;
    public Content( JPanel ig, Point beginPoint,               Point endPoint,  int heigthLine, int indent
                    ,ArrayList<Estakada> estakads,   int weidthMinut,  Date begin,     Date end
                    ,ArrayList<Admission> admissions,int frequencyTime) {
        this.ig=ig;
        this.admissions=admissions;
        this.frequencyTime=frequencyTime;
        this.begin=begin;
        this.end=end;
        this.weidthMinut=weidthMinut;
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
        this.estakads=estakads;
        this.heigthLine=heigthLine;
        this.indent=indent;
        init();
    }
   
    private void init(){
        if(image!=null){
            image.getGraphics().dispose();
            System.gc();
        }
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
        int min = (int) ((end.getTime()-begin.getTime())/60000);
        for(int x=0,m=0; m<min; m+=frequencyTime, x=m*weidthMinut){
            g.drawLine(x, 0, x, heigth);
        }
        for(int i=0,y=0; i<estakads.size(); i++){
            y+=indent+heigthLine;
            g.drawLine(0, y, weidth, y);
            for(int j=0; j<estakads.get(i).getDrainLocations().size(); j++){ 
                for(Admission a:admissions){
                    if(estakads.get(i).getDrainLocations().get(j).getId()==a.getDrainLocation().getId()){
                        if(a.getStatus().equals("План"))
                            g.setColor(new Color(26, 150, 239, 140));
                        else
                            g.setColor(new Color(26, 129, 15, 140));
                        int addMinBegin =(int)((a.getBegin().getTime()-begin.getTime())/60000);
                        int colMin = a.getTank().getTypeTank().getTime();
                        g.fillRoundRect(addMinBegin*weidthMinut+1, y+1, colMin*weidthMinut-2, heigthLine-2,10, 10);
                        g.setColor(Color.BLACK);
                        g.drawString(a.getTank().getNumber(), addMinBegin*weidthMinut+5, y+heigthLine-5);
                        
                        if(activAdmission!=null && activAdmission.getId()==a.getId() && a.getStatus().equals("План")){
                            g.setColor(Color.WHITE);
                            g.drawRoundRect(addMinBegin*weidthMinut+2, y+2, colMin*weidthMinut-4, heigthLine-4,10, 10);
                        }
                    }
                }
                g.setColor(new Color(85,85,85));
                y+=heigthLine;
                g.drawLine(0, y, weidth, y);
                
            }
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
        init();
    }

    
    Point pressPoint=null;
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void mouseDragged(MouseEvent e) {
        if(activAdmission!=null && activAdmission.getStatus().equals("План")){
            int sx = e.getX()-pressPoint.x;
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(activAdmission.getBegin());
            c.add(GregorianCalendar.SECOND, (sx*60)/weidthMinut);
            activAdmission.setBegin(c.getTime());
            int pY = e.getY()-beginPoint.y+biasY;

            //jump
            mforjump:
            for(int i=0,y=0; i<estakads.size() && jump; i++){
                y+=indent+heigthLine;
                for(int j=0; j<estakads.get(i).getDrainLocations().size(); j++){
                    int y1=y+heigthLine;
                    if(pY>y && pY<y1){
                        if(activAdmission.getDrainLocation().getId()!=estakads.get(i).getDrainLocations().get(j).getId() &&
                          estakads.get(i).getTypeEstakada().getId()==activAdmission.getDrainLocation().getEstakada().getTypeEstakada().getId()){
                            activAdmission.setDrainLocation(estakads.get(i).getDrainLocations().get(j));
                            break mforjump;
                        }
                    }
                    y+=heigthLine;
                }
            }
            pressPoint=e.getPoint();
            ig.repaint();
            init();
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if(activAdmission!=null){
            pressPoint=e.getPoint();
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        if(e.getX()<beginPoint.x || e.getX()>endPoint.x || e.getY()<beginPoint.y || e.getY()>endPoint.y)
            return;
        int pX = e.getX()-beginPoint.x+biasX;
        int pY = e.getY()-beginPoint.y+biasY;
        
        //определение цистерни
        for(int i=0,y=0; i<estakads.size(); i++){
            y+=indent+heigthLine;
            for(int j=0; j<estakads.get(i).getDrainLocations().size(); j++){
                int y1=y+heigthLine;
                if(pY>y && pY<y1){
                    for(Admission a :admissions){
                        if(a.getDrainLocation().getId()==estakads.get(i).getDrainLocations().get(j).getId()){
                            int addMinBegin =(int)((a.getBegin().getTime()-begin.getTime())/60000);
                            int colMin = a.getTank().getTypeTank().getTime();
                            if(pX>addMinBegin*weidthMinut && pX < (addMinBegin+colMin)*weidthMinut){
                                if(activAdmission==null || activAdmission!=a){
                                    activAdmission=a;
                                    ig.repaint();
                                }
                                return;
                            }
                        }
                    }
                }
                y+=heigthLine;
            }
        }
        if(activAdmission!=null){
            activAdmission=null;
            ig.repaint();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        jump = e.getKeyChar()=='j' || e.getKeyChar()=='о';
    }

    @Override
    public void keyReleased(KeyEvent e) {
        jump = false;
    }
}

