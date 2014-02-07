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
 *
 * @author aNNiMON
 */
public abstract class JecpImage {
    
    public JecpImage(String res) throws IOException { }
    
    public JecpImage(InputStream is) throws IOException { }
    
    public abstract int getWidth();
    
    public abstract int getHeight();
}
