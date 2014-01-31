package com.annimon.jecp.me;

import com.annimon.jecp.ApplicationListener;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author aNNiMON
 */
public abstract class JecpApplication extends MIDlet {
    
    private final ApplicationListener listener;
    private static MIDlet midlet;
    private static Display display;
    
    public JecpApplication(ApplicationListener listener) {
        this.listener = listener;
    }
    
    protected final void startApp() {
        midlet = this;
        display = Display.getDisplay(this);
        display.setCurrent(new JecpCanvas(listener));
    }

    protected final void pauseApp() {
        listener.onPauseApp();
    }

    protected final void destroyApp(boolean unconditional) {
        listener.onDestroyApp();
        notifyDestroyed();
    }

    public static MIDlet getMidlet() {
        return midlet;
    }

    public static Display getDisplay() {
        return display;
    }
}
