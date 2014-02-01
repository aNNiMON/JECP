
package com.annimon.jecp.demo.screens;

import com.annimon.jecp.JecpGraphics;
import com.annimon.jecp.JecpRandom;
import com.annimon.jecp.Keys;

/**
 * Controlling square by keyboard and touch/mouse.
 * @author aNNiMON
 */
public class ControllingSquareScreen extends Screen {
    
    private int squareX, squareY, squareSize, squareColor;
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    public ControllingSquareScreen(int width, int height) {
        super(width, height);
        final int min = Math.min(width, height);
        squareSize = JecpRandom.rand(min / 20, min / 5);
        squareX = width / 2 - squareSize / 2;
        squareY = height / 2 - squareSize / 2;
        squareColor = JecpRandom.randomColor(100, 250);
    }
    
    public void onPaint(JecpGraphics g) {
        super.onPaint(g);
        g.setColor(squareColor);
        g.fillRect(squareX, squareY, squareSize, squareSize);
    }

    public void onUpdate() {
        if (upPressed) {
            squareY--;
            if (squareY < 0) squareY = 0;
        } else if (downPressed) {
            squareY++;
            if (squareY > (height - squareSize)) squareY = height - squareSize;
        }
        if (leftPressed) {
            squareX--;
            if (squareX < 0) squareX = 0;
        } else if (rightPressed) {
            squareX++;
            if (squareX > (width - squareSize)) squareX = width - squareSize;
        }
        sleep(2);
    }
    
    public void onPointerPressed(int x, int y) {
        super.onPointerPressed(x, y);
        squareX = x;
        squareY = y;
    }

    public void onPointerDragged(int x, int y) {
        super.onPointerDragged(x, y);
        squareX = x;
        squareY = y;
    }

    public void onKeyPressed(int key) {
        super.onKeyPressed(key);
        final int dpad = Keys.convertToDpad(key);
        updateKeys(dpad, true);
    }

    public void onKeyReleased(int key) {
        super.onKeyReleased(key);
        final int dpad = Keys.convertToDpad(key);
        updateKeys(dpad, false);
    }
    
    private void updateKeys(int dpad, boolean value) {
        switch (dpad) {
            case Keys.DPAD_LEFT:
                leftPressed = value;
                break;
            case Keys.DPAD_RIGHT:
                rightPressed = value;
                break;
            case Keys.DPAD_UP:
                upPressed = value;
                break;
            case Keys.DPAD_DOWN:
                downPressed = value;
                break;
        }
    }
}
