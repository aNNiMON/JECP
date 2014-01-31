package com.annimon.jecp.me;

import javax.microedition.lcdui.Graphics;

/**
 *
 * @author aNNiMON
 */
public class JecpGraphics implements com.annimon.jecp.JecpGraphics {
    
    private final Graphics g;
    
    protected JecpGraphics(Graphics g) {
        this.g = g;
    }
    
    public void drawLine(int x1, int y1, int x2, int y2) {
        g.drawLine(x1, y1, x2, y2);
    }

    public void drawRect(int x, int y, int width, int height) {
        g.drawRect(x, y, width, height);
    }
    
    public void fillRect(int x, int y, int width, int height) {
        g.fillRect(x, y, width, height);
    }

    public void setColor(int color) {
        g.setColor(color);
    }
}
