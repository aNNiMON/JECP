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


/**
 * 
 * @author aNNiMON
 */
public class PaintCanvas extends Canvas {
    
    private final ApplicationListener listener;
    private final DrawingThread thread;
    private final Image image;
    private final JecpGraphics graphics;

    public PaintCanvas(ApplicationListener listener) {
        this.listener = listener;
        
        setFullScreenMode(true);
        int width = getWidth();
        int height = getHeight();
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
        g.drawImage(image, 0, 0, Graphics.TOP | Graphics.LEFT);
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
            Jecp.inputListener.onPointerDragged(x, y);
        }
    }
    
    protected void pointerPressed(int x, int y) {
        if (Jecp.inputListener != null) {
            Jecp.inputListener.onPointerPressed(x, y);
        }
    }
    
    protected void pointerReleased(int x, int y) {
        if (Jecp.inputListener != null) {
            Jecp.inputListener.onPointerReleased(x, y);
        }
    }
    
    private int convertDpadKeys(int keyCode) {
        int ga = getGameAction(keyCode);
        if (ga == UP && keyCode != KEY_NUM2) keyCode = Keys.DPAD_UP;
        else if (ga == DOWN && keyCode != KEY_NUM8) keyCode = Keys.DPAD_DOWN;
        else if (ga == LEFT && keyCode != KEY_NUM4) keyCode = Keys.DPAD_LEFT;
        else if (ga == RIGHT && keyCode != KEY_NUM6) keyCode = Keys.DPAD_RIGHT;
        else if (ga == FIRE && keyCode != KEY_NUM5) keyCode = Keys.DPAD_FIRE;
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
