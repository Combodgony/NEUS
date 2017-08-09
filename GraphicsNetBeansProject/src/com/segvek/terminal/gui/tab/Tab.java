package com.segvek.terminal.gui.tab;

import javax.swing.JPanel;


public class Tab extends JPanel{
    public boolean isNeedSave(){
        return false;
    }
    public boolean isCanBeAdd(){
        return false;
    }

    public void save(){
    }

    public String getName(){
        return "";
    }
}
