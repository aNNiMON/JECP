
package com.annimon.jecp;

/**
 *
 * @author aNNiMON
 */
public interface InputListener {
    
    public void onKeyPressed(int key);
    
    public void onKeyReleased(int key);    
    
    public void onPointerPressed(int x, int y);
    
    public void onPointerReleased(int x, int y);
    
    public void onPointerDragged(int x, int y);
}
