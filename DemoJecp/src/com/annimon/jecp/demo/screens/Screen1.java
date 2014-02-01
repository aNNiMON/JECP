
package com.annimon.jecp.demo.screens;

import com.annimon.jecp.JecpGraphics;
import com.annimon.jecp.JecpRandom;
import com.annimon.jecp.demo.DynamicLine;

/**
 * Lines animation demo.
 * @author aNNiMON
 */
public class Screen1 extends Screen {
    
    private static final int LINES_COUNT = 5;
    
    private final DynamicLine[] lines;

    public Screen1(int width, int height) {
        super(width, height);
        lines = new DynamicLine[LINES_COUNT];
        for (int i = 0; i < LINES_COUNT; i++) {
            lines[i] = new DynamicLine(
                    JecpRandom.rand(width), JecpRandom.rand(height),
                    JecpRandom.rand(width), JecpRandom.rand(height));
        }
    }
    
    public void onPaint(JecpGraphics g) {
        super.onPaint(g);
        for (int i = 0; i < LINES_COUNT; i++) {
            lines[i].onPaint(g);
        }
    }

    public void onUpdate() {
        for (int i = 0; i < lines.length; i++) {
            lines[i].onUpdate(width, height);
        }
        sleep(5);
    }
    
}
