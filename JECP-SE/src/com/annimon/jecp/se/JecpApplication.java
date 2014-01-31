package com.annimon.jecp.se;

import com.annimon.jecp.ApplicationListener;
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
        
        listener.onStartApp(width, height);
        panel = new JecpPaintPanel(listener, width, height);
        add(panel);
        pack();
        
        setVisible(true);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        listener.onDestroyApp();
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
