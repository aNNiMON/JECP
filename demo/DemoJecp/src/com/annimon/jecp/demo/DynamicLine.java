package com.annimon.jecp.demo;

import com.annimon.jecp.Graphics;
import com.annimon.jecp.JecpRandom;

/**
 *
 * @author aNNiMON
 */
public class DynamicLine {
    
    private final int color;
    private int x1, y1, x2, y2;
    private float dirX1, dirY1, dirX2, dirY2;
    
    public DynamicLine(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = JecpRandom.randomColor(50, 255);
        dirX1 = JecpRandom.rand(-5f, 5f);
        dirY1 = JecpRandom.rand(-5f, 5f);
        dirX2 = JecpRandom.rand(-5f, 5f);
        dirY2 = JecpRandom.rand(-5f, 5f);
    }
    
    public void onPaint(Graphics g) {
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }
    
    public void onUpdate(int width, int height) {
        if (!checkBounds(0, x1, width)) dirX1 = -dirX1;
        if (!checkBounds(0, y1, height)) dirY1 = -dirY1;
        if (!checkBounds(0, x2, width)) dirX2 = -dirX2;
        if (!checkBounds(0, y2, height)) dirY2 = -dirY2;
        
        x1 += dirX1;
        y1 += dirY1;
        x2 += dirX2;
        y2 += dirY2;
    }
    
    private boolean checkBounds(int min, double value, int max) {
        return ( (min < value) && (value < max) );
    }
}
