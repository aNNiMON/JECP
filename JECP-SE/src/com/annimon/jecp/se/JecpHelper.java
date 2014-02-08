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

import com.annimon.jecp.HelperInterface;
import com.annimon.jecp.JecpImage;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author aNNiMON
 */
class JecpHelper implements HelperInterface {
    
    private final JecpApplication app;

    JecpHelper(JecpApplication app) {
        this.app = app;
    }

    @Override
    public JecpImage init(String res) throws IOException {
        return new ImageSE(res);
    }

    @Override
    public JecpImage init(InputStream is) throws IOException {
        return new ImageSE(is);
    }

    @Override
    public void exitApp() {
        app.dispatchEvent(new WindowEvent(app, WindowEvent.WINDOW_CLOSING));
    }
    
}