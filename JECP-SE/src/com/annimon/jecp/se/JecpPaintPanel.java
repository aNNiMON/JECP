package com.annimon.jecp.se;

import com.annimon.jecp.ApplicationListener;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author aNNiMON
 */
public class JecpPaintPanel extends JPanel {
    
    private static final InputListenerSE INPUT_LISTENER = new InputListenerSE();
    
    private final ApplicationListener listener;
    private final DrawingThread thread;
    private final BufferedImage image;
    private final JecpGraphics graphics;
    
    public JecpPaintPanel(ApplicationListener listener, int width, int height) {
        this.listener = listener;
        setFocusable(true);
        addMouseListener(INPUT_LISTENER);
        addMouseMotionListener(INPUT_LISTENER);
        addKeyListener(INPUT_LISTENER);
        setPreferredSize(new Dimension(width, height));
        
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        graphics = new JecpGraphics(image.getGraphics());
        
        thread = new DrawingThread();
        thread.keepRunning = true;
        thread.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        listener.onPaint(graphics);
        g.drawImage(image, 0, 0, null);
    }
    
    private class DrawingThread extends Thread {

        private boolean keepRunning = true;

        @Override
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