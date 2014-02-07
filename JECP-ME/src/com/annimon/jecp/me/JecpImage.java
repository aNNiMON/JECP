/*
 * Copyright 2014 aNNiMON.
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

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Image;

/**
 *
 * @author aNNiMON
 */
public class JecpImage extends com.annimon.jecp.JecpImage {
    
    Image image;
    
    public JecpImage(String res) throws IOException {
        super(res);
        init(getClass().getResourceAsStream(res));
    }
    
    public JecpImage(InputStream is) throws IOException {
        super(is);
        init(is);
    }
    
    private void init(InputStream is) throws IOException {
        image = Image.createImage(is);
        if (is != null) is.close();
    }
    
    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }
}
