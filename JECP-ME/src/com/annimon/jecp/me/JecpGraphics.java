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

    public void drawRect(int x, int y, int width, int height) {
        g.drawRect(x, y, width, height);
    }
    
}
