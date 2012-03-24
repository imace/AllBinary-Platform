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

import allbinary.graphics.Anchor;
import allbinary.graphics.displayable.DisplayInfoSingleton;
import allbinary.graphics.font.MyFont;
import allbinary.graphics.paint.Paintable;

public class AboutPaintable extends Paintable
{
    private final String ABOUT = "About";
    
    private final String[] info;

    private final String[] developers;

    private final Paintable[] paintableArray =
    {
        this
    };
    
    public static AboutPaintable getInstance(String[] info, String[] developers)
    {
        return new AboutPaintable(info, developers);
    }

    private AboutPaintable(String[] info, String[] developers)
    {
        this.info = info;
        this.developers = developers;
    }

    public Paintable[] getPaintableArrayInstance()
    {
        return paintableArray;
    }

    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(Graphics graphics)
    {
        final int halfWidth = DisplayInfoSingleton.getInstance().getLastHalfWidth();
        final int charHeight = MyFont.getInstance().DEFAULT_CHAR_HEIGHT;
        
        int beginWidth = (graphics.getFont().stringWidth(this.ABOUT) >> 1);
        
        graphics.drawString(this.ABOUT, halfWidth - beginWidth, 2 * charHeight, anchor);
        
        int infoSize = this.info.length;
        for(int index = 0; index < infoSize; index++)
        {
            beginWidth = (graphics.getFont().stringWidth(this.info[index]) >> 1);
        
            graphics.drawString(this.info[index], halfWidth - beginWidth,
                    (4 + index) * charHeight, anchor);
        }
        
        int size = this.developers.length;
        for(int index = 0; index < size; index++)
        {
            beginWidth = (graphics.getFont().stringWidth(this.developers[index]) >> 1);

            graphics.drawString(this.developers[index], halfWidth - beginWidth,
                    (5 + infoSize + index) * charHeight, anchor);
        }
    }
}
