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

import com.annimon.jecp.Jecp;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author aNNiMON
 */
class InputListenerSE implements MouseListener, MouseMotionListener, KeyListener {

    @Override
    public void mousePressed(MouseEvent e) {
        if (Jecp.inputListener != null) {
            Jecp.inputListener.onPointerPressed(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (Jecp.inputListener != null) {
            Jecp.inputListener.onPointerReleased(e.getX(), e.getY());
        }
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        if (Jecp.inputListener != null) {
            Jecp.inputListener.onPointerDragged(e.getX(), e.getY());
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (Jecp.inputListener != null) {
            Jecp.inputListener.onKeyPressed(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (Jecp.inputListener != null) {
            Jecp.inputListener.onKeyReleased(e.getKeyCode());
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseMoved(MouseEvent e) { }
    
    @Override
    public void keyTyped(KeyEvent e) { }
}
