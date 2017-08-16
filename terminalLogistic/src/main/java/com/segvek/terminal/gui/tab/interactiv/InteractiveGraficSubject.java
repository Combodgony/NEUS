package com.segvek.terminal.gui.tab.interactiv;

public interface InteractiveGraficSubject {
    void addInteractiveGraficListener(InteractivGraficListener listener) throws InteractiveGraficException;
    void removeInteractiveGraficListener(InteractivGraficListener listener) throws InteractiveGraficException;
    
}
