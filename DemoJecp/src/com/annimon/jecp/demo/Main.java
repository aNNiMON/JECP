package com.annimon.jecp.demo;

import com.annimon.jecp.JecpGraphics;
import com.annimon.jecp.JecpRandom;
import com.annimon.jecp.ApplicationListener;

/**
 * @author aNNiMON
 */
public class Main implements ApplicationListener {
    
    private int width, height;
    private DynamicLine[] lines;

    public void onStartApp(int width, int height) {
        this.width = width;
        this.height = height;
        System.out.println("width: " + width + "  height: " + height);
        
        lines = new DynamicLine[5];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new DynamicLine(
                    JecpRandom.rand(width), JecpRandom.rand(height),
                    JecpRandom.rand(width), JecpRandom.rand(height));
        }
    }

    public void onPauseApp() {
        System.out.println("onPauseApp");
    }

    public void onDestroyApp() {
        System.out.println("onDestroyApp");
    }

    public void onPaint(JecpGraphics g) {
        g.setColor(0xFF000000);
        g.fillRect(0, 0, width, height);
        final int count = 20;
        final int areaSize = width / count;
        final int colorStep = 255 / (count + 1);
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                int color = colorStep * Math.min(i, j);
                color = 0xFF000000 | (color << 16) | (color << 8) | color;
                g.setColor(color);
                g.drawRect(i * areaSize, j * areaSize, areaSize, areaSize);
            }
        }
        for (int i = 0; i < lines.length; i++) {
            lines[i].onPaint(g);
        }
    }

    public void onUpdate() {
        for (int i = 0; i < lines.length; i++) {
            lines[i].onUpdate(width, height);
        }
        try {
            Thread.sleep(20L);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
