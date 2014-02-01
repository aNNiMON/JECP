
package com.annimon.jecp.demo.screens;

import com.annimon.jecp.JecpGraphics;
import com.annimon.jecp.JecpRandom;
import com.annimon.jecp.Keys;

/**
 * Graphics demo with processing keys.
 * @author aNNiMON
 */
public class CellsScreen extends Screen {
    
    private static final int HIGHLIGHT_CELLS_COUNT = 5;
    private int cellsCount;
    
    private final HighlightCell[] cells;

    public CellsScreen(int width, int height) {
        super(width, height);
        cellsCount = 20;
        cells = new HighlightCell[HIGHLIGHT_CELLS_COUNT];
        for (int i = 0; i < HIGHLIGHT_CELLS_COUNT; i++) {
            cells[i] = new HighlightCell();
            cells[i].reinit();
        }
        Keys.numericdAsDpad = true;
    }
    
    public void onPaint(JecpGraphics g) {
        super.onPaint(g);
        final int areaSize = Math.min(width, height) / cellsCount;
        final int colorStep = 255 / (cellsCount + 1);
        for (int i = 0; i < cellsCount; i++) {
            for (int j = 0; j < cellsCount; j++) {
                int color = colorStep * Math.min(i, j);
                color = 0xFF000000 | (color << 16) | (color << 8) | color;
                g.setColor(color);
                g.drawRect(i * areaSize, j * areaSize, areaSize, areaSize);
            }
        }
        for (int i = 0; i < HIGHLIGHT_CELLS_COUNT; i++) {
            cells[i].draw(g, areaSize);
        }
    }

    public void onUpdate() {
        for (int i = 0; i < HIGHLIGHT_CELLS_COUNT; i++) {
            cells[i].reinit();
        }
        sleep(1000);
    }
    
    public void onKeyPressed(int key) {
        int dpad = Keys.convertToDpad(key);
        if (dpad == Keys.DPAD_LEFT) {
            cellsCount--;
            if (cellsCount < 3) {
                cellsCount = 3;
            }
        } else if (dpad == Keys.DPAD_RIGHT) {
            cellsCount++;
            if (cellsCount > 30) {
                cellsCount = 30;
            }
        }
        super.onKeyPressed(key);
    }
    
    private class HighlightCell {
        
        private int cellX, cellY, cellColor;
        
        private void draw(JecpGraphics g, int areaSize) {
            g.setColor(cellColor);
            g.fillRect(cellX * areaSize + 1, cellY * areaSize + 1, areaSize - 1, areaSize - 1);
        }
        
        private void reinit() {
            cellX = JecpRandom.rand(cellsCount);
            cellY = JecpRandom.rand(cellsCount);
            cellColor = JecpRandom.randomColor(0, 255);
        }
    }
    
}
