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

package com.annimon.jecp.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import com.annimon.jecp.ConsoleApplicationListener;

/**
 * Main entry for console applications.
 * 
 * @author aNNiMON
 */
public abstract class ConsoleApplication extends Activity
        implements ConsoleApplicationListener.Console {
    
    private static final String STATE_TEXT = "text";
    
    private volatile boolean mIsPauseThread;
    private InputTextDialog mInputTextDialog;
    private EditText mInputEditText;
    private TextView mTextView;

    /**
     * Initialize console application.
     *
     * @param listener listens console application-based events.
     */
    public void init(final ConsoleApplicationListener listener) {
        new Thread(new Runnable() {

            public void run() {
                listener.onStartApp(ConsoleApplication.this);
            }
        }).start();
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTextView = new TextView(this);
        setContentView(mTextView);
        
        mIsPauseThread = false;
        mInputTextDialog = new InputTextDialog(this);
        
        if (savedInstanceState != null) {
            mTextView.setText(savedInstanceState.getString(STATE_TEXT));
        }
        
        onCreate();
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mTextView != null) {
            outState.putString(mTextView.getText().toString(), STATE_TEXT);
        }
    }
    
    protected abstract void onCreate();
    

    @Override
    public void print(final String text) {
        runOnUiThread(new Runnable() {
            public void run() {
                mTextView.append(text);
            }
        });
    }

    @Override
    public void println(String text) {
        print(text + "\n");
    }

    @Override
    public String read() {
        runOnUiThread(new Runnable() {
            public void run() {
                mInputTextDialog.show();
            }
        });
        mIsPauseThread = true;
        while (mIsPauseThread) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        return mInputEditText.getText().toString();
    }

    @Override
    public String readln() {
        return read();
    }
    
    private final class InputTextDialog extends AlertDialog {
        
        public InputTextDialog(Context context) {
            super(context);
            mInputEditText = new EditText(context);
            setCancelable(false);
            setView(mInputEditText);
            setTitle("Enter text:");
            setButton(AlertDialog.BUTTON_POSITIVE, getString(android.R.string.ok), new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mIsPauseThread = false;
                }
            });
        }
    }
}
