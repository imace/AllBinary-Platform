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
package allbinary.game.displayable.canvas;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.communication.log.PreLogUtil;
import allbinary.game.input.Input;
import allbinary.game.input.InputFactory;
import allbinary.game.input.InputProcessor;
import allbinary.game.input.event.DownGameKeyEventHandler;
import allbinary.game.input.event.GameKeyEvent;
import allbinary.game.input.event.GameKeyEventFactory;

public class FormInputProcessor
extends InputProcessor
{
    private final AllBinaryGameCanvas allBinaryGameCanvas;
    
    private final GameKeyEventFactory gameKeyEventFactory = GameKeyEventFactory.getInstance();
    
    public FormInputProcessor(AllBinaryGameCanvas allBinaryGameCanvas)
    {
        this.allBinaryGameCanvas = allBinaryGameCanvas;
    }
    
    private final InputFactory inputFactory = InputFactory.getInstance();
    
    public void keyPressed(int keyCode)
    {
        try
        {
            // LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START_LABEL
            // + keyCode, this, "keyPressed"));
            PreLogUtil.put(CommonStrings.getInstance().START_LABEL + keyCode, this, "keyPressed");

            Input input = inputFactory.getInstance(keyCode);

            GameKeyEvent gameKeyEvent = gameKeyEventFactory.getInstance(this.allBinaryGameCanvas, input);

            DownGameKeyEventHandler.getInstance().fireEvent(gameKeyEvent);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Key Event Error", this, this.allBinaryGameCanvas.ADD_KEY_EVENT, e));
        }
    }
}