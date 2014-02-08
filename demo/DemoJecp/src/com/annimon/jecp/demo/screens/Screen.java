
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
    
    private static final String NEXT_SCREEN_BUTTON_TEXT = "->";
    
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
        final int x = width - (nextScreenButtonSize + g.getTextWidth(NEXT_SCREEN_BUTTON_TEXT)) / 2;
        final int y = height - (nextScreenButtonSize + g.getTextHeight()) / 2;
        g.setColor(0xFFFFFFFF);
        g.drawString(NEXT_SCREEN_BUTTON_TEXT, x, y);
    }
    
    public abstract void onUpdate();

    public void onKeyPressed(int key) {
        if (nextScreenListener != null) {
            if (key == Keys.KEY_POUND || key == Keys.VK_ENTER) {
                nextScreenListener.onNextScreen();
            } else if (key == Keys.KEY_BACK ||
                    key == Keys.ANDRO_BACK ||
                    key == Keys.VK_ESCAPE) {
                Jecp.exitApp();
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
