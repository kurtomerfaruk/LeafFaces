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
