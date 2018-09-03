/*
 * Copyright 2018 Omer Faruk Kurt.
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

package com.blogspot.ofarukkurt.leaffaces;

import com.blogspot.ofarukkurt.leaffaces.utils.LeafFaces;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

/**
 *
 * @author Omer Faruk Kurt
 * @Created on date 04/08/2018 17:31:46 
 */
public class PostConstructApplicationEventListener implements SystemEventListener{
    
     private static final Logger logger = Logger.getLogger(PostConstructApplicationEventListener.class.getName());

    @Override
    public void processEvent(SystemEvent se) throws AbortProcessingException {
        
    logger.log(Level.INFO, "Running on LeafFaces {0}", LeafFaces.VERSION);
    }

    @Override
    public boolean isListenerForSource(Object o) {
        return true;
    }

}
