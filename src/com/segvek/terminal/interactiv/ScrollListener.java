package com.segvek.terminal.interactiv;

public interface ScrollListener{
    /**
     * метод оповещает что скрол сдвинут на указаное колличество пикселей 
     * по масштабу клиента
     */
    public void verticalScroll(int count);
    
    public void horisontalScroll(int count);
    
}
