/*
 * Copyright 2012 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */
package com.segvek.terminal.gui.image;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Class to load images out of jar-files
 *
 * @author Michael Hagen
 */
public class ImageHelper {

    private ImageHelper() {
    }

    public static ImageIcon loadImage(String name) {
        return new ImageIcon("resources/"+name);
    }
}
