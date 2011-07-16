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
package allbinary.game.paint;

import javax.microedition.lcdui.Graphics;

import abcs.logic.basic.string.StringUtil;
import allbinary.AppletUtil;
import allbinary.graphics.Anchor;
import allbinary.graphics.displayable.DisplayInfoSingleton;
import allbinary.graphics.font.MyFont;
import allbinary.graphics.paint.Paintable;
import allbinary.input.motion.button.TouchScreenFactory;
import allbinary.time.TimeDelayHelper;

/**
 * 
 * @author Berthelot, Travis
 * @version 1.0
 */
public class PressStartMenuPaintable extends Paintable
{
    private String startString = StringUtil.getInstance().EMPTY_STRING;
    private final int line = (4 * MyFont.getInstance().DEFAULT_CHAR_HEIGHT)
            + (MyFont.getInstance().DEFAULT_CHAR_HEIGHT >> 1);

    private TimeDelayHelper timeDelayHelper = new TimeDelayHelper(1100);
    private boolean flash;

    private final String PRESS_START = "Press Screen To Start";
    private final String KEY_START = "Press or Click F2 To Begin";
    private final String MENU_START = "Press Start From The Menu To Begin";

    public PressStartMenuPaintable()
    {
        if (TouchScreenFactory.getInstance().isTouch())
        {
            startString = PRESS_START;
        }
        else if (AppletUtil.isAppletLoader(this))
        {
            startString = KEY_START;
        }
        else
        {
            startString = MENU_START;
        }
    }

    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(Graphics graphics)
    {
        if (timeDelayHelper.isTime())
        {
            if (this.isFlash())
            {
                this.setFlash(false);
            }
            else
            {
                this.setFlash(true);
            }
        }

        if (this.isFlash())
        {
            DisplayInfoSingleton displayInfo = DisplayInfoSingleton
                    .getInstance();

            int beginWidth = (graphics.getFont().stringWidth(startString) >> 1);
            
            graphics.drawString(startString, displayInfo.getLastHalfWidth()
                    - beginWidth, displayInfo.getLastHeight() - line, anchor);
        }
    }

    private void setFlash(boolean flash)
    {
        this.flash = flash;
    }

    private boolean isFlash()
    {
        return flash;
    }

}
