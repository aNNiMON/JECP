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
public abstract class JecpApplication extends JFrame implements ApplicationListener, WindowListener {
    
    private final JecpPaintPanel panel;

    public JecpApplication(int width, int height) {
        addWindowListener(JecpApplication.this);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        onStartApp(width, height);
        panel = new JecpPaintPanel(this, width, height);
        add(panel);
        pack();
        
        setVisible(true);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        onDestroyApp();
    }
    
    @Override
    public void windowDeactivated(WindowEvent e) {
        onPauseApp();
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
