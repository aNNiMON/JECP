package com.annimon.jecp;

/**
 *
 * @author aNNiMON
 */
public interface ApplicationListener {
    
    public void onStartApp(int width, int height);
    
    public void onPauseApp();
    
    public void onDestroyApp();
    
    public void onPaint(JecpGraphics g);
    
    public void onUpdate();
}
