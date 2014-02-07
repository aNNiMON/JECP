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

package com.annimon.jecp.me;

import com.annimon.jecp.ApplicationListener;
import com.annimon.jecp.JecpImage;
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
        JecpImage.imageInitializer = new JecpImageInitializer();
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
