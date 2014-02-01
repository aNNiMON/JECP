package com.annimon.jecp.me;

import com.annimon.jecp.ApplicationListener;
import com.annimon.jecp.Jecp;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;


/**
 * 
 * @author aNNiMON
 */
public class JecpCanvas extends Canvas {
    
    private final ApplicationListener listener;
    private final DrawingThread thread;
    private final Image image;
    private final JecpGraphics graphics;

    public JecpCanvas(ApplicationListener listener) {
        this.listener = listener;
        
        setFullScreenMode(true);
        int width = getWidth();
        int height = getHeight();
        image = Image.createImage(width, height);
        Graphics g = image.getGraphics();
        graphics = new JecpGraphics(g);
        
        listener.onStartApp(width, height);
        
        thread = new DrawingThread();
        thread.keepRunning = true;
        thread.start();
    }

    protected void paint(Graphics g) {
        listener.onPaint(graphics);
        g.drawImage(image, 0, 0, Graphics.TOP | Graphics.LEFT);
    }
    
    protected void keyPressed(int keyCode) {
        if (Jecp.inputListener != null) {
            Jecp.inputListener.onKeyPressed(keyCode);
        }
    }
    
    protected void keyReleased(int keyCode) {
        if (Jecp.inputListener != null) {
            Jecp.inputListener.onKeyReleased(keyCode);
        }
    }
    
    protected void pointerDragged(int x, int y) {
        if (Jecp.inputListener != null) {
            Jecp.inputListener.onPointerDragged(x, y);
        }
    }
    
    protected void pointerPressed(int x, int y) {
        if (Jecp.inputListener != null) {
            Jecp.inputListener.onPointerPressed(x, y);
        }
    }
    
    protected void pointerReleased(int x, int y) {
        if (Jecp.inputListener != null) {
            Jecp.inputListener.onPointerReleased(x, y);
        }
    }
    
    private class DrawingThread extends Thread {

        private boolean keepRunning = true;

        public void run() {
            while (keepRunning) {
                listener.onUpdate();
                repaint();
                try {
                    Thread.sleep(5L);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
