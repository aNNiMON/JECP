package com.annimon.jecp.me;

import com.annimon.jecp.ApplicationListener;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author aNNiMON
 */
public abstract class JecpApplication extends MIDlet implements ApplicationListener {
    
    private MIDlet midlet;
    private Display display;

    protected final void startApp() {
        midlet = this;
        display = Display.getDisplay(this);
        display.setCurrent(new JecpCanvas(this));
    }

    protected final void pauseApp() {
        onPauseApp();
    }

    protected final void destroyApp(boolean unconditional) {
        onDestroyApp();
        notifyDestroyed();
    }
}