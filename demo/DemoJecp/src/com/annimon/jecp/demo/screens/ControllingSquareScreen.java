
package com.annimon.jecp.demo.screens;

import com.annimon.jecp.JecpGraphics;
import com.annimon.jecp.Image;
import com.annimon.jecp.JecpRandom;
import com.annimon.jecp.Keys;
import java.io.IOException;

/**
 * Controlling square by keyboard and touch/mouse.
 * @author aNNiMON
 */
public class ControllingSquareScreen extends Screen {
    
    private int posX, posY, objWidth, objHeight, squareColor;
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private Image image;

    public ControllingSquareScreen(int width, int height) {
        super(width, height);
        try {
            image = Image.createImage("res/jecp_logo.png");
            objWidth = image.getWidth();
            objHeight = image.getHeight();
        } catch (IOException ex) {
            image = null;
            final int min = Math.min(width, height);
            objWidth = JecpRandom.rand(min / 20, min / 5);
            objHeight = objWidth;
            squareColor = JecpRandom.randomColor(100, 250);
        }
        posX = width / 2 - objWidth / 2;
        posY = height / 2 - objHeight / 2;
    }
    
    public void onPaint(JecpGraphics g) {
        super.onPaint(g);
        if (image != null) {
            g.drawImage(image, posX, posY);
        } else {
            g.setColor(squareColor);
            g.fillRect(posX, posY, objWidth, objWidth);
        }
    }

    public void onUpdate() {
        if (upPressed) {
            posY--;
            if (posY < 0) posY = 0;
        } else if (downPressed) {
            posY++;
            if (posY > (height - objHeight)) posY = height - objHeight;
        }
        if (leftPressed) {
            posX--;
            if (posX < 0) posX = 0;
        } else if (rightPressed) {
            posX++;
            if (posX > (width - objWidth)) posX = width - objWidth;
        }
        sleep(2);
    }
    
    public void onPointerPressed(int x, int y) {
        super.onPointerPressed(x, y);
        posX = x;
        posY = y;
    }

    public void onPointerDragged(int x, int y) {
        super.onPointerDragged(x, y);
        posX = x;
        posY = y;
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
