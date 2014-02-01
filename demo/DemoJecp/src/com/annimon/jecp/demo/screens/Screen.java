
package com.annimon.jecp.demo.screens;

import com.annimon.jecp.InputListener;
import com.annimon.jecp.Jecp;
import com.annimon.jecp.JecpGraphics;
import com.annimon.jecp.Keys;

/**
 *
 * @author aNNiMON
 */
public abstract class Screen implements InputListener {
    
    public interface OnNextScreenListener {
        void onNextScreen();
    }
    
    protected final int width, height;
    private final int nextScreenButtonSize;
    private OnNextScreenListener nextScreenListener;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        nextScreenButtonSize = Math.min(width, height) / 20;
        Jecp.inputListener = Screen.this;
    }
    
    public void setOnNextScreenListener(OnNextScreenListener listener) {
        this.nextScreenListener = listener;
    }
    
    public void onPaint(JecpGraphics g) {
        // Clear screen
        g.setColor(0xFF000000);
        g.fillRect(0, 0, width, height);
        // Next screen button
        g.setColor(0xFFFF0000);
        g.fillRect(width - nextScreenButtonSize, height - nextScreenButtonSize,
                nextScreenButtonSize, nextScreenButtonSize);
        g.setColor(0xFFFFFFFF);
        g.drawLine(width - nextScreenButtonSize, height - nextScreenButtonSize / 2,
                width, height - nextScreenButtonSize / 2);
        g.drawLine(width - nextScreenButtonSize / 4, height - nextScreenButtonSize,
                width, height - nextScreenButtonSize / 2);
        g.drawLine(width - nextScreenButtonSize / 4, height,
                width, height - nextScreenButtonSize / 2);
    }
    
    public abstract void onUpdate();

    public void onKeyPressed(int key) {
        if (nextScreenListener != null) {
            if (key == Keys.KEY_POUND || key == Keys.VK_ENTER) {
                nextScreenListener.onNextScreen();
            }
        }
    }
    
    public void onPointerPressed(int x, int y) {
        if (nextScreenListener != null) {
            final int btnX = width - nextScreenButtonSize;
            final int btnY = height - nextScreenButtonSize;
            if ( (x > btnX) && (y > btnY) ) {
                nextScreenListener.onNextScreen();
            }
        }
    }

    public void onKeyReleased(int key) { }

    public void onPointerReleased(int x, int y) { }

    public void onPointerDragged(int x, int y) { }
    
    protected void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
