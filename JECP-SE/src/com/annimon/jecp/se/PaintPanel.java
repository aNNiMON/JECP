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

package com.annimon.jecp.se;

import com.annimon.jecp.ApplicationListener;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author aNNiMON
 */
class PaintPanel extends JPanel {
    
    private static final InputListenerSE INPUT_LISTENER = new InputListenerSE();
    
    private final ApplicationListener listener;
    private final DrawingThread thread;
    private final BufferedImage image;
    private final JecpGraphics graphics;
    
    public PaintPanel(ApplicationListener listener, int width, int height) {
        this.listener = listener;
        setFocusable(true);
        addMouseListener(INPUT_LISTENER);
        addMouseMotionListener(INPUT_LISTENER);
        addKeyListener(INPUT_LISTENER);
        setPreferredSize(new Dimension(width, height));
        
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        graphics = new JecpGraphics(image.getGraphics());
        
        thread = new DrawingThread();
        thread.keepRunning = true;
        thread.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        listener.onPaint(graphics);
        g.drawImage(image, 0, 0, null);
    }
    
    private class DrawingThread extends Thread {

        private boolean keepRunning = true;

        @Override
        public void run() {
            while (keepRunning) {
                listener.onUpdate();
                repaint();
            }
        }
    }
}