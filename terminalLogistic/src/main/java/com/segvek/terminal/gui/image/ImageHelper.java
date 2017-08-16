package com.segvek.terminal.gui.image;

import javax.swing.ImageIcon;

public class ImageHelper {

    private ImageHelper() {
    }

    public static ImageIcon loadImage(String name) {
        return new ImageIcon("resources/"+name);
    }
}
