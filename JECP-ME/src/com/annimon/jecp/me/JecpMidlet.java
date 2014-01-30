package com.annimon.jecp.me;

import com.annimon.jecp.ApplicationListener;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author aNNiMON
 */
public abstract class JecpMidlet extends MIDlet implements ApplicationListener {

    protected final void startApp() {
        onCreate();
    }

    protected final void pauseApp() {
        onPause();
    }

    protected final void destroyApp(boolean unconditional) {
        onExit();
    }
}
