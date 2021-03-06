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
package allbinary.graphics.paint;

import javax.microedition.lcdui.Graphics;

import allbinary.graphics.Anchor;
import allbinary.graphics.color.BasicColor;
import allbinary.graphics.font.MyFont;

public class SimpleTextPaintable extends Paintable
{
    private String text;
    private BasicColor basicColor;
    
    public SimpleTextPaintable(String text, BasicColor basicColor)
    {
        this.setText(text);

        this.basicColor = basicColor;
    }
    
    private int anchor = Anchor.TOP_LEFT;
    
    private final int HEIGHT = MyFont.getInstance().DEFAULT_CHAR_HEIGHT;
    
    public void paint(Graphics graphics)
    {
        int width = graphics.getClipWidth();
        
        int topScoresWidth = (graphics.getFont().stringWidth(this.text) >> 1);

        graphics.setColor(this.getBasicColor().intValue());
        
        graphics.drawString(this.text, (width >> 1) - topScoresWidth, HEIGHT * 3, anchor);    
    }

    public void setBasicColor(BasicColor basicColor)
    {
        this.basicColor = basicColor;
    }

    private BasicColor getBasicColor()
    {
        return basicColor;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return text;
    }    
}
