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

package com.annimon.jecp.se;

import com.annimon.jecp.ApplicationListener;
import com.annimon.jecp.Jecp;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author aNNiMON
 */
public abstract class JecpApplication extends JFrame implements WindowListener {
    
    private final ApplicationListener listener;
    private final JecpPaintPanel panel;

    public JecpApplication(ApplicationListener listener, int width, int height) {
        this.listener = listener;
        addWindowListener(JecpApplication.this);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        Jecp.helper = new JecpHelper(this);
        listener.onStartApp(width, height);
        panel = new JecpPaintPanel(listener, width, height);
        add(panel);
        pack();
        
        setVisible(true);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        listener.onDestroyApp();
        setVisible(false);
        dispose();
    }
    
    @Override
    public void windowDeactivated(WindowEvent e) {
        listener.onPauseApp();
    }
    
    @Override
    public void windowOpened(WindowEvent e) { }

    @Override
    public void windowClosed(WindowEvent e) { }
    
    @Override
    public void windowIconified(WindowEvent e) { }

    @Override
    public void windowDeiconified(WindowEvent e) { }

    @Override
    public void windowActivated(WindowEvent e) { }
    
}
