/*
 * Copyright 2014 Victor Melnik <annimon119@gmail.com>, and
 * individual contributors as indicated by the @authors tag.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.annimon.jecp.me;

import com.annimon.jecp.ApplicationListener;
import com.annimon.jecp.Jecp;
import com.annimon.jecp.Keys;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;


/**
 * 
 * @author aNNiMON
 */
public class PaintCanvas extends Canvas {
    
    private final ApplicationListener listener;
    private final boolean isLandscape;
    private final DrawingThread thread;
    private final Image image;
    private final JecpGraphics graphics;
    
    private int width, height;

    public PaintCanvas(ApplicationListener listener, boolean isLandscape) {
        this.listener = listener;
        this.isLandscape = isLandscape;
        setFullScreenMode(true);
        width = getWidth();
        height = getHeight();
        if (isLandscape) {
            width = height;
            height = getWidth();
        }
        image = Image.createImage(width, height);
        Graphics g = image.getGraphics();
        graphics = new JecpGraphics(g);
        
        listener.onStartApp(width, height);
        
        thread = new DrawingThread();
        thread.keepRunning = true;
        thread.start();
    }

    protected void paint(Graphics g) {
        listener.onPaint(graphics);
        if (isLandscape) {
            g.drawRegion(image, 0, 0, width, height, Sprite.TRANS_ROT90,
                    0, 0, Graphics.TOP | Graphics.LEFT);
        } else {
            g.drawImage(image, 0, 0, Graphics.TOP | Graphics.LEFT);
        }
    }
    
    protected void keyPressed(int keyCode) {
        if (Jecp.inputListener != null) {
            keyCode = convertDpadKeys(keyCode);
            Jecp.inputListener.onKeyPressed(keyCode);
        }
    }

    protected void keyReleased(int keyCode) {
        if (Jecp.inputListener != null) {
            keyCode = convertDpadKeys(keyCode);
            Jecp.inputListener.onKeyReleased(keyCode);
        }
    }
    
    protected void pointerDragged(int x, int y) {
        if (Jecp.inputListener != null) {
            if (isLandscape) {
                Jecp.inputListener.onPointerDragged(y, height - x);
            } else Jecp.inputListener.onPointerDragged(x, y);
        }
    }
    
    protected void pointerPressed(int x, int y) {
        if (Jecp.inputListener != null) {
            if (isLandscape) {
                Jecp.inputListener.onPointerPressed(y, height - x);
            } else Jecp.inputListener.onPointerPressed(x, y);
        }
    }
    
    protected void pointerReleased(int x, int y) {
        if (Jecp.inputListener != null) {
            if (isLandscape) {
                Jecp.inputListener.onPointerReleased(y, height - x);
            } else Jecp.inputListener.onPointerReleased(x, y);
        }
    }
    
    private int convertDpadKeys(int keyCode) {
        final int ga = getGameAction(keyCode);
        if (ga == UP && keyCode != KEY_NUM2) keyCode = Keys.DPAD_UP;
        else if (ga == DOWN && keyCode != KEY_NUM8) keyCode = Keys.DPAD_DOWN;
        else if (ga == LEFT && keyCode != KEY_NUM4) keyCode = Keys.DPAD_LEFT;
        else if (ga == RIGHT && keyCode != KEY_NUM6) keyCode = Keys.DPAD_RIGHT;
        else if (ga == FIRE && keyCode != KEY_NUM5) keyCode = Keys.DPAD_FIRE;
        else return keyCode;
        if (isLandscape) {
            // Dpad code is in range -501..-504, we need to increase code by 1.
            if ( (Keys.DPAD_DOWN <= keyCode) && (keyCode < Keys.DPAD_LEFT) ) {
                keyCode++;
            } else if (keyCode == Keys.DPAD_LEFT) {
                keyCode = Keys.DPAD_DOWN;
            }
        }
        return keyCode;
    }
    
    private class DrawingThread extends Thread {

        private boolean keepRunning = true;

        public void run() {
            while (keepRunning) {
                listener.onUpdate();
                repaint();
            }
        }
    }
}
