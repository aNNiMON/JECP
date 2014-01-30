package com.annimon.jecp;

/**
 *
 * @author aNNiMON
 */
public interface ApplicationListener {
    
    public void onStartApp();
    
    public void onPauseApp();
    
    public void onDestroyApp();
    
    public void onPaint(JecpGraphics g);
}
