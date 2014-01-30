package com.annimon.jecp.me;

import com.annimon.jecp.ApplicationListener;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;


/**
 * 
 * @author aNNiMON
 */
public class JecpCanvas extends Canvas {
    
    private final ApplicationListener listener;
    private final Image image;

    public JecpCanvas(ApplicationListener listener) {
        this.listener = listener;
        
        setFullScreenMode(true);
        int width = getWidth();
        int height = getHeight();
        image = Image.createImage(width, height);
        Graphics g = image.getGraphics();
        listener.onPaint(new JecpGraphics(g));
    }

    protected void paint(Graphics g) {
        g.drawImage(image, 0, 0, Graphics.TOP | Graphics.LEFT);
    }

    
    protected void keyPressed(int keyCode) {
    }

    
    protected void keyReleased(int keyCode) {
    }

    
    protected void keyRepeated(int keyCode) {
    }

    
    protected void pointerDragged(int x, int y) {
    }

    
    protected void pointerPressed(int x, int y) {
    }

    
    protected void pointerReleased(int x, int y) {
    }
}
