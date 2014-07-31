package com.annimon.jecp.demo;

import com.annimon.jecp.ApplicationListener;
import com.annimon.jecp.Graphics;
import com.annimon.jecp.Prefs;
import com.annimon.jecp.demo.screens.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author aNNiMON
 */
public class Main implements ApplicationListener, Screen.OnNextScreenListener {
    
    private static final int SCREEN_FIRST = 1, SCREEN_LAST = 5;
    
    private int width, height;
    
    private int screenIndex;
    private Screen currentScreen;
    
    public void onStartApp(int width, int height) {
        this.width = width;
        this.height = height;
        
        // Load preferences.
        try {
            DataInputStream prefs = Prefs.loadAsStream();
            screenIndex = prefs.readInt();
        } catch (IOException ex) {
            // If unable to load preferences, init by defaults.
            screenIndex = SCREEN_FIRST;
        }
        initScreen();
    }

    public void onPauseApp() { }

    public void onDestroyApp() {
        // Store preferences.
        Prefs.saveAsStream(new Prefs.StoreListener() {
            public void save(DataOutputStream dos) throws IOException {
                dos.writeInt(screenIndex);
            }
        });
    }

    public void onPaint(Graphics g) {
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
                currentScreen = new LinesScreen(width, height);
                break;
            case 2:
                currentScreen = new CellsScreen(width, height);
                break;
            case 3:
                currentScreen = new ControllingSquareScreen(width, height);
                break;
            case 4:
                currentScreen = new VisualMEScreen(width, height);
                break;
            case 5:
                currentScreen = new PolygonScreen(width, height);
                break;
        }
        currentScreen.setOnNextScreenListener(this);
    }
}
