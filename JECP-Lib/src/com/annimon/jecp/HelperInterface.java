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
package com.annimon.jecp;

import java.io.IOException;
import java.io.InputStream;

/**
 * Utility interface to hold platforms together.
 *
 * @author aNNiMON
 */
public interface HelperInterface {

    /**
     * Returns new image for fair uses.
     *
     * @param res a path to load image from inside a jar file.
     * @return image to use.
     * @throws IOException if image can't be loaded.
     */
    public Image init(String res) throws IOException;

    /**
     * Returns new image for fair uses.
     *
     * @param is a stream to load image from.
     * @return image to use.
     * @throws IOException if image can't be loaded.
     */
    public Image init(InputStream is) throws IOException;

    /**
     * Exits the app.
     */
    public void exitApp();
}
