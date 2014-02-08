
package com.annimon.jecp.demo.screens;

import com.annimon.jecp.Fps;
import com.annimon.jecp.JecpGraphics;
import com.annimon.jecp.JecpRandom;
import com.annimon.jecp.demo.DynamicLine;

/**
 * Lines animation, FPS-class methods and key codes demo.
 * @author aNNiMON
 */
public class LinesScreen extends Screen {
    
    private static final int LINES_COUNT = 5;
    
    private boolean fpsLimiter;
    private long fpsLimiterDelay;
    private int keyCode;
    
    private final DynamicLine[] lines;

    public LinesScreen(int width, int height) {
        super(width, height);
        fpsLimiter = false;
        fpsLimiterDelay = 5;
        keyCode = 0;
        lines = new DynamicLine[LINES_COUNT];
        for (int i = 0; i < LINES_COUNT; i++) {
            lines[i] = new DynamicLine(
                    JecpRandom.rand(width), JecpRandom.rand(height),
                    JecpRandom.rand(width), JecpRandom.rand(height));
        }
    }
    
    public void onPaint(JecpGraphics g) {
        Fps.startMeasuringDelay();
        super.onPaint(g);
        for (int i = 0; i < LINES_COUNT; i++) {
            lines[i].onPaint(g);
        }
        g.setColor(0xFFFFFFFF);
        g.drawString("Fps limiter is " + (fpsLimiter ? "on" : "off") + ". Press any key to change state.", 0, 0);
        g.drawString("Fps: " + Fps.getFps(), 0, g.getTextHeight());
        g.drawString("Key: " + keyCode, 0, 2 * g.getTextHeight());
        
        if (fpsLimiter) fpsLimiterDelay = Fps.getDelay();
        else fpsLimiterDelay = 2;
    }

    public void onUpdate() {
        for (int i = 0; i < lines.length; i++) {
            lines[i].onUpdate(width, height);
        }
        sleep(fpsLimiterDelay);
    }

    public void onKeyPressed(int key) {
        keyCode = key;
        fpsLimiter = !fpsLimiter;
        super.onKeyPressed(key);
    }
}
