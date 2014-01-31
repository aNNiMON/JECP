package com.annimon.jecp;

/**
 *
 * @author aNNiMON
 */
public interface JecpGraphics {
    
    public void drawLine(int x1, int y1, int x2, int y2);
    
    public void drawRect(int x, int y, int width, int height);
    
    public void fillRect(int x, int y, int width, int height);
    
    public void setColor(int color);
}
