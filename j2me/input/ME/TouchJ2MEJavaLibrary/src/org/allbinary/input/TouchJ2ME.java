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
package org.allbinary.input;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.input.motion.AllMotionRecognizer;
import allbinary.input.motion.gesture.observer.BasicMotionGesturesHandler;
import allbinary.input.motion.gesture.observer.GameMotionGestureListener;
import allbinary.input.motion.gesture.observer.MotionGestureReceiveInterfaceFactory;

/**
 *
 * @author user
 */
public class TouchJ2ME
{
    private AllMotionRecognizer motionRecognizer = new AllMotionRecognizer();

    public TouchJ2ME()
    {
        BasicMotionGesturesHandler motionGesturesHandler =
            motionRecognizer.getMotionGestureRecognizer().getMotionGesturesHandler();

        motionGesturesHandler.addListener(
            new GameMotionGestureListener(
            MotionGestureReceiveInterfaceFactory.getInstance()));
    }

    public void pointerDragged(int x, int y)
    {
        try
        {
            motionRecognizer.processDraggedMotionEvent(x, y, 0);
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "pointerDragged", e));
        }
    }

    public void pointerPressed(int x, int y)
    {
        try
        {
            motionRecognizer.processStartMotionEvent(x, y, 0);
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "pointerPressed", e));
        }
    }

    public void pointerReleased(int x, int y)
    {
        try
        {
            motionRecognizer.processEndMotionEvent(x, y, 0);
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "pointerReleased", e));
        }
    }
}
