package com.annimon.jecp.se;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author aNNiMON
 */
public class JecpGraphics implements com.annimon.jecp.JecpGraphics {
    
    private final Graphics g;
    
    protected JecpGraphics(Graphics g) {
        this.g = g;
    }
    
    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        g.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void drawRect(int x, int y, int width, int height) {
        g.drawRect(x, y, width, height);
    }
    
    @Override
    public void fillRect(int x, int y, int width, int height) {
        g.fillRect(x, y, width, height);
    }

    @Override
    public void setColor(int color) {
        g.setColor(new Color(color));
    }
}