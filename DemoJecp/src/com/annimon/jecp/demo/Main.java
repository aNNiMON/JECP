package com.annimon.jecp.demo;

import com.annimon.jecp.JecpGraphics;
import com.annimon.jecp.JecpRandom;
import com.annimon.jecp.ApplicationListener;

/**
 * @author aNNiMON
 */
public class Main implements ApplicationListener {
    
    private static final int CELLS_COUNT = 30;
    
    private int width, height;
    private DynamicLine[] lines;
    
    private int cellX, cellY, cellColor;
    private long cellUpdateTime;

    public void onStartApp(int width, int height) {
        this.width = width;
        this.height = height;
        System.out.println("width: " + width + "  height: " + height);
        
        initHighlightCell();
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
        final int areaSize = Math.min(width, height) / CELLS_COUNT;
        final int colorStep = 255 / (CELLS_COUNT + 1);
        for (int i = 0; i < CELLS_COUNT; i++) {
            for (int j = 0; j < CELLS_COUNT; j++) {
                int color = colorStep * Math.min(i, j);
                color = 0xFF000000 | (color << 16) | (color << 8) | color;
                g.setColor(color);
                g.drawRect(i * areaSize, j * areaSize, areaSize, areaSize);
            }
        }
        g.setColor(cellColor);
        g.fillRect(cellX * areaSize + 1, cellY * areaSize + 1, areaSize - 1, areaSize - 1);
        for (int i = 0; i < lines.length; i++) {
            lines[i].onPaint(g);
        }
    }

    public void onUpdate() {
        for (int i = 0; i < lines.length; i++) {
            lines[i].onUpdate(width, height);
        }
        if ((System.currentTimeMillis() - cellUpdateTime) > 1000) {
            // Update every second.
            initHighlightCell();
        }
        try {
            Thread.sleep(20L);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    private void initHighlightCell() {
        cellX = JecpRandom.rand(CELLS_COUNT);
        cellY = JecpRandom.rand(CELLS_COUNT);
        cellColor = JecpRandom.randomColor(0, 255);
        cellUpdateTime = System.currentTimeMillis();
    }
}
