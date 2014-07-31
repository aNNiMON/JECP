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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Load and save preferences.
 * @author aNNiMON
 */
public class Prefs {
    
    public static byte[] load() {
        return Jecp.helper.loadPrefs();
    }
    
    public static DataInputStream loadAsStream() throws IOException {
        byte[] data = load();
        if (data == null) {
            throw new IOException("Unable to load preferences");
        }
        return new DataInputStream(new ByteArrayInputStream(load()));
    }
    
    public static void save(byte[] data) {
        Jecp.helper.savePrefs(data);
    }
    
    public static void saveAsStream(StoreListener listener) {
        if (listener == null) {
            throw new NullPointerException("StoreStreamListener must be non null");
        } 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        try {
            listener.save(dos);
            dos.close();
        } catch (IOException ex) { }
        save(baos.toByteArray());
    }
    
    public interface StoreListener {
        void save(DataOutputStream dos) throws IOException;
    }
}
