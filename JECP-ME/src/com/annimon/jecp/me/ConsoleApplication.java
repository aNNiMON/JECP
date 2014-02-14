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

import com.annimon.jecp.ConsoleApplicationListener;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;

/**
 * Main entry for console applications.
 *
 * @author aNNiMON
 */
public abstract class ConsoleApplication extends MIDlet
        implements ConsoleApplicationListener.Console, ItemCommandListener {
    
    private static final Command CMDTEXT_OK = new Command("OK", Command.OK, 1);
    private static Display display;
    
    private final ConsoleApplicationListener listener;
    private final Form form;
    
    /**
     * Standart constructor for console apps.
     *
     * @param listener listens application-based events.
     */
    public ConsoleApplication(ConsoleApplicationListener listener) {
        this("JECP Console", listener);
    }
    
    /**
     * Constructor for console apps with custom title.
     *
     * @param title form title.
     * @param listener listens application-based events.
     */
    public ConsoleApplication(String title, ConsoleApplicationListener listener) {
        this.listener = listener;
        form = new Form(title);
    }
    
    protected final void startApp() {
        display = Display.getDisplay(this);
        display.setCurrent(form);
        listener.onStartApp(this);
    }

    protected final void pauseApp() {
    }

    protected final void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }
    
    private TextField initTextFied() {
        TextField tf = new TextField("Enter text:", "", 8192, TextField.ANY);
        tf.addCommand(CMDTEXT_OK);
        tf.setDefaultCommand(CMDTEXT_OK);
        tf.setItemCommandListener(this);
        return tf;
    }
    
    public void commandAction(Command cmd, Item item) {
        if (cmd == CMDTEXT_OK) {
            synchronized (this) {
                notify();
            }
        }
    }
    

    public void print(String text) {
        form.append(text);
    }

    public void println(String text) {
        print(text + "\r\n");
    }

    public String read() {
        TextField tf = initTextFied();
        form.append(tf);
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        return tf.getString();
    }

    public String readln() {
        return read();
    }
}
