package com.segvek.terminal.gui.tab.interactiv;

import com.segvek.terminal.gui.MainFrame;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.DependencyAdmission;
import com.segvek.terminal.model.Estakada;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;

class Content implements ScrollListener, MouseListener,MouseMotionListener, KeyListener{
    private JPanel ig; //необходимо для оптимизации работы
    private int weidth, heigth;
    private int heigthLine;
    private int indent;
    private int biasY=0;
    private int biasX=0;
    private Color backgroundColor = new Color(50,50,50);
    private boolean editable;
    private boolean showInfoBlock=false;
    Point pointInfoBlock;//сомнительная переменная 
    private boolean edited=false;
    private List<InteractivGraficListener> listeners;
    
    
    private List<Estakada> estakads;
    private List<Admission> admissions;
    private Map<Admission,Point> posAdmission; //point - upper-left point 
    private List<DependencyAdmission> das = new ArrayList<>();
    private Admission activAdmission=null;
    
    
    private Point beginPoint,endPoint; 
    private Date begin, end;
    private double weidthMinut;
    int frequencyTime=60;
    private BufferedImage image;
    
    private boolean jump = false;
    public Content( JPanel ig, Point beginPoint,               Point endPoint,  int heigthLine, int indent
                    ,List<Estakada> estakads,   double weidthMinut,  Date begin,     Date end
                    ,List<Admission> admissions,int frequencyTime,List<DependencyAdmission> das,List<InteractivGraficListener> listeners) {
        this.das=das;
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
        this.listeners=listeners;
        init();
        clacPositionAdmission();
    }
   
    private void init(){
        if(image!=null){
            image.getGraphics().dispose();
        }
        int h=0;
        for(int i=0; i<estakads.size(); i++){
            h+=indent;
            h+=(estakads.get(i).getDrainLocations().size()+1)*heigthLine;
        }
        h+=3*indent;
        int ht=endPoint.y-beginPoint.y;
        h=h<ht?ht:h;
        heigth=h;
        int min = (int) ((end.getTime()-begin.getTime())/60000);
        double w = min*weidthMinut;
        int wt=endPoint.x-beginPoint.x;
        w=w<wt?wt:w;   
        weidth=(int)w;
        image = new BufferedImage(endPoint.x-beginPoint.x,heigth, BufferedImage.TYPE_INT_ARGB);
    }
    private void clacPositionAdmission(){
        if(posAdmission==null)
            posAdmission = new HashMap<>();
        else{
            posAdmission.clear();
        }
        for(int i=0,y=indent+heigthLine; i<estakads.size(); i++,y+=indent+heigthLine){
            for(int j=0; j<estakads.get(i).getDrainLocations().size(); j++){ 
                for(Admission a:admissions){
                    if(estakads.get(i).getDrainLocations().get(j).getId().equals(a.getDrainLocation().getId())){
                        int addMinBegin=0;
                        if(a.isPlan()){
                            addMinBegin =(int)((a.getBegin().getTime()-begin.getTime())/60000);
                        }else
                            addMinBegin =(int)((a.getFactBegin().getTime()-begin.getTime())/60000);
                        Point t = new Point((int)(addMinBegin*weidthMinut), y+1);
                        posAdmission.put(a, t);
                    }
                }
                y+=heigthLine;
            }
        }
    }
    
    public Image getImage(){
        Graphics2D g = (Graphics2D)image.getGraphics(); 
        g.setColor(backgroundColor);
        g.fillRect(0, 0, weidth, heigth);
        
        g.setColor(new Color(85,85,85));
        int min = (int) ((end.getTime()-begin.getTime())/60000);
        //todo оптимизация перебора всего отображаемого поля н
        for(int x=0,m=0; m<min; m+=frequencyTime, x=(int)(m*weidthMinut)){
            if(x>biasX && x<biasX+(endPoint.x-beginPoint.x))
                g.drawLine(x-(int)(biasX%(m*weidthMinut)), 0, x-(int)(biasX%(m*weidthMinut)), heigth);
        }
        for(int i=0,y=0; i<estakads.size(); i++){
            y+=indent+heigthLine;
            g.drawLine(0, y, weidth, y);
            for(int j=0; j<estakads.get(i).getDrainLocations().size(); j++){ 
                g.setColor(new Color(85,85,85));
                y+=heigthLine;
                g.drawLine(0, y, weidth, y);
            }
        }
        drawAdmission();
        drawDependency();
        
        Date now = new Date();
        if(begin.getTime()<now.getTime() && end.getTime()>now.getTime()){
            long minut = (now.getTime()-begin.getTime())/60000;
            g.setColor(Color.red);
            g.drawLine((int)(minut*weidthMinut)-biasX, 0, (int)(minut*weidthMinut)-biasX, heigth);
        }
        if(showInfoBlock){
            showInfoBlock(activAdmission,pointInfoBlock);
        }
        return image.getSubimage(0,biasY, endPoint.x-beginPoint.x, endPoint.y-beginPoint.y);
    }
    private void drawAdmission() {
        Graphics2D g = (Graphics2D)image.getGraphics();
        Date now = new Date();
        GregorianCalendar c = new GregorianCalendar();
        for(Admission a: posAdmission.keySet()){
            Point p = (Point) posAdmission.get(a).clone();
            p.x-=biasX;
            p.y-=biasY;
            int colMin=0;
            
            if(a.isPlan()){
                c.setTime(a.getBegin());
                c.add(GregorianCalendar.MINUTE, a.getTank().getTypeTank().getTime());
                if(c.getTime().getTime()<now.getTime())
                    g.setColor(new Color(181, 47, 47));
                else
                    g.setColor(new Color(77, 139, 209, 200));
                colMin = a.getTank().getTypeTank().getTime();
            }else{
                g.setColor(new Color(26, 129, 15, 200));
                colMin = (int)((a.getFactEnd().getTime()-a.getFactBegin().getTime())/60000);
            }
            g.fillRoundRect(p.x+1, p.y+1, (int)(colMin*weidthMinut)-2, heigthLine-2,10, 10);
            if(weidthMinut>1.5){
                g.setColor(Color.BLACK);
                g.drawString(a.getTank().getNumber(), p.x+5, p.y+heigthLine-5);
            }
            if(activAdmission!=null && activAdmission.getId().equals(a.getId()) /*&& a.getStatus().equals("План")*/){
                g.setColor(Color.WHITE);
                g.drawRoundRect(p.x+2, p.y+2, (int)(colMin*weidthMinut)-4, heigthLine-4,10, 10);
            }
        }
    }
    private void drawDependency(){
        Graphics2D g = (Graphics2D)image.getGraphics();
        
        for(DependencyAdmission d:das){
            Admission a = d.getAdmission();
            int colMin = a.getTank().getTypeTank().getTime();
            Point p = (Point) posAdmission.get(a).clone();
            p.x-=biasX;
            p.y-=biasY;
            Point p2 = (Point) posAdmission.get(d.getIndependet()).clone();
            p2.x-=biasX;
            p2.y-=biasY;
            g.setColor(new Color(118, 15, 128));
            g.drawLine(p.x+(int)(colMin*weidthMinut), p.y+heigthLine/2, p2.x, p2.y+heigthLine/2);
            g.fill(new Ellipse2D.Double(p.x+(colMin*weidthMinut)-4, p.y+heigthLine/2-4, 8, 8));
            g.fill(new Ellipse2D.Double(p2.x-4, p2.y+heigthLine/2-4, 8, 8));
            
        }
    }
    private void showInfoBlock(Admission a,Point p){
        if(a==null || p==null)
            return;
        
        Graphics2D g = (Graphics2D)image.getGraphics();   
        g.setColor(Color.PINK);
        g.fillRoundRect(p.x-beginPoint.x, p.y-beginPoint.y, 100, 50,5,5); 
        g.setColor(Color.BLACK);
	String id = a.getId().toString();
        g.drawString(id, p.x-beginPoint.x+3, p.y-beginPoint.y+20);
        String nom = a.getTank().getNumber();
        g.drawString(nom, p.x-beginPoint.x+30, p.y-beginPoint.y+20);
        String est = a.getDrainLocation().getEstakada().getTypeEstakada().getName();
        g.drawString(est, p.x-beginPoint.x+3, p.y-beginPoint.y+40);
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
    
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void keyTyped(KeyEvent e) {}  
    public void mouseReleased(MouseEvent e) {
        showInfoBlock=false;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(activAdmission!=null){
            for(InteractivGraficListener l:listeners){
                l.selected(activAdmission);
            }
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if(editable && activAdmission!=null && activAdmission.isPlan() && !showInfoBlock){
            int sx = e.getX()-pressPoint.x;
            if(activAdmission.addTime(GregorianCalendar.SECOND, (int)((sx*60)/weidthMinut)))
                pressPoint=e.getPoint();
            int pY = e.getY()-beginPoint.y+biasY;
            edited=true;
            MainFrame.getInstance().initInstrumentPanel();
            //jump
            mforjump:
            for(int i=0,y=0; i<estakads.size() && jump; i++){
                y+=indent+heigthLine;
                for(int j=0; j<estakads.get(i).getDrainLocations().size(); j++){
                    int y1=y+heigthLine;
                    if(pY>y && pY<y1){
                        if(!activAdmission.getDrainLocation().getId().equals(estakads.get(i).getDrainLocations().get(j).getId()) &&
                          estakads.get(i).getTypeEstakada().getId().equals(activAdmission.getDrainLocation().getEstakada().getTypeEstakada().getId())){
                            activAdmission.setDrainLocation(estakads.get(i).getDrainLocations().get(j));
                            break mforjump;
                        }
                    }
                    y+=heigthLine;
                }
            }
            
            clacPositionAdmission();
            ig.repaint();
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON3 && activAdmission!=null){
            showInfoBlock=true;
            pointInfoBlock=e.getPoint();
            ig.repaint();
        }else{
            showInfoBlock=false;
        }
            
        
        if(editable && activAdmission!=null){
            pressPoint=e.getPoint();
        }
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        ig.setFocusable(true);
        ig.requestFocus();
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
                        if(a.getDrainLocation().getId().equals(estakads.get(i).getDrainLocations().get(j).getId())){
                            int addMinBegin;
                            int colMin;
                            if(a.isPlan()){
                                addMinBegin=(int)((a.getBegin().getTime()-begin.getTime())/60000);
                                colMin = a.getTank().getTypeTank().getTime();
                            }else{
                                addMinBegin=(int)((a.getFactBegin().getTime()-begin.getTime())/60000);
                                colMin = (int)((a.getFactEnd().getTime()-begin.getTime())/60000)-addMinBegin;
                            }
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

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    void setEditable(boolean editable) {
        this.editable=editable;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_J)
            jump=true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        jump=false;
    }

    boolean isEdited() {
        return edited;
    }

    void setEdited(boolean edited) {
        this.edited=edited;
    }

    void setDependencyAdmissions(List<DependencyAdmission> das) {
        this.das=das;
        clacPositionAdmission();
    }

    
}

