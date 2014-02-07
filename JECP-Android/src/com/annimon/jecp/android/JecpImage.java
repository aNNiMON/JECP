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

package com.annimon.jecp.android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author aNNiMON
 */
public class JecpImage extends com.annimon.jecp.JecpImage {
    
    Bitmap mImage;

    public JecpImage(String res) throws IOException {
        super(res);
        init(JecpApplication.sAssetManager.open(res));
    }
    
    public JecpImage(InputStream is) throws IOException {
        super(is);
        init(is);
    }
    
    private void init(InputStream is) throws IOException {
        mImage = BitmapFactory.decodeStream(is);
        if (is != null) is.close();
    }
    
    @Override
    public int getWidth() {
        return mImage.getWidth();
    }

    @Override
    public int getHeight() {
        return mImage.getHeight();
    }
}
