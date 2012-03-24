/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package allbinary.input.motion.gesture.observer;

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.input.CompleteMotionGestureInputEventHandler;
import allbinary.game.input.motion.action.GameKeyCompleteMotionGestureInputEvent;
import allbinary.game.input.motion.action.GameKeyFromMotionGestureEventListener;
import allbinary.input.motion.gesture.MotionGestureToMotionGestureActionAssociation;
import allbinary.input.motion.gesture.configuration.MotionGestureConfiguration;
import allbinary.input.motion.gesture.configuration.MotionGestureConfigurationFactory;

public class ResolveCompleteMotionGestureListener implements CompleteMotionGestureListenerInterface {

    public ResolveCompleteMotionGestureListener() {
        
        LogUtil.put(LogFactory.getInstance("MotionGesture to CompleteMotionGesture Reciever", this, CommonStrings.getInstance().CONSTRUCTOR));

        CompleteMotionGestureInputEventHandler.getInstance().addListener(
                //SingleKeyPress
                new GameKeyFromMotionGestureEventListener());
    }

    public void onMotionGestureCompleted(BasicArrayList list)
    throws Exception{
        
        //LogUtil.put(LogFactory.getInstance("Gesture Completed: " + list.toString(), this, "mouseGestureCompleted"));
       //PreLogUtil.put("Gesture Completed: " + list.toString(), this, "mouseGestureCompleted");
        
        MotionGestureConfiguration configuration = 
            MotionGestureConfigurationFactory.getInstance();
        
        BasicArrayList commandActionsList = configuration.getAssociateCommandActionsList();
        
        int size = commandActionsList.size();
        //LogUtil.put(LogFactory.getInstance("commandActionsList.size(): " + size, this, "mouseGestureCompleted"));
        
        for(int index = size - 1; index >= 0; index--) 
        {
            MotionGestureToMotionGestureActionAssociation association = 
                (MotionGestureToMotionGestureActionAssociation) 
                commandActionsList.objectArray[index];
            
            if (association.isMotionGestureArrayEquals(list) == true) {

                GameKeyCompleteMotionGestureInputEvent completeMotionGestureInputEvent = 
                    (GameKeyCompleteMotionGestureInputEvent) association.getCommandAction();
                
                //LogUtil.put(LogFactory.getInstance("Gesture Completed: " +  completeMotionGestureInputEvent.getMotionGestureInput().getName(),
                  //      this, "mouseGestureCompleted"));
                CompleteMotionGestureInputEventHandler.getInstance().fireEvent(
                        completeMotionGestureInputEvent);

                break;
            }
        }
    }
    
}
