package com.annimon.jecp.demo;

import com.annimon.jecp.ApplicationListener;
import com.annimon.jecp.JecpGraphics;
import com.annimon.jecp.demo.screens.*;

/**
 * @author aNNiMON
 */
public class Main implements ApplicationListener, Screen.OnNextScreenListener {
    
    private static final int SCREEN_FIRST = 1, SCREEN_LAST = 2;
    
    private int width, height;
    
    private int screenIndex;
    private Screen currentScreen;
    
    public void onStartApp(int width, int height) {
        this.width = width;
        this.height = height;
        
        screenIndex = SCREEN_FIRST;
        initScreen();
    }

    public void onPauseApp() { }

    public void onDestroyApp() { }

    public void onPaint(JecpGraphics g) {
        currentScreen.onPaint(g);
    }

    public void onUpdate() {
        currentScreen.onUpdate();
    }

    public void onNextScreen() {
        screenIndex++;
        if (screenIndex > SCREEN_LAST) {
            screenIndex = SCREEN_FIRST;
        }
        initScreen();
    }
    
    private void initScreen() {
        switch (screenIndex) {
            case 1:
                currentScreen = new Screen1(width, height);
                break;
            case 2:
                currentScreen = new Screen2(width, height);
                break;
        }
        currentScreen.setOnNextScreenListener(this);
    }
}
