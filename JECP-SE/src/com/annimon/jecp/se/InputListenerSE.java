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
