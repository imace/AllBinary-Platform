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

import javax.microedition.lcdui.Graphics;

import allbinary.graphics.color.BasicColorFactory;
import allbinary.graphics.displayable.DisplayInfoSingleton;
import allbinary.graphics.draw.CanvasDrawLineString;
import allbinary.graphics.paint.InitUpdatePaintable;


/**
 *
 * @author user
 */
public class PreGameSelectorPaintable extends InitUpdatePaintable
{
    private final String title;
    private CanvasDrawLineString canvasDrawLineString;
    private final PreGameScrollSelectionForm preGameScrollSelectionForm;

    public PreGameSelectorPaintable(
            String title, PreGameScrollSelectionForm preGameScrollSelectionForm)
    {
        this.title = title;

        DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();

        this.canvasDrawLineString = new CanvasDrawLineString(
                displayInfo.getLastHalfWidth(), 0);

        this.preGameScrollSelectionForm = preGameScrollSelectionForm;
    }

    public void update()
    {
        DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
        
        this.canvasDrawLineString = new CanvasDrawLineString(
                displayInfo.getLastHalfWidth(), 0);
    }
    
    public void paint(Graphics graphics)
    {
        graphics.setColor(BasicColorFactory.getInstance().WHITE.intValue());
        this.canvasDrawLineString.paint(graphics, title, 0);
        this.getPreGameSelectionForm().paint(graphics);
    }

    /**
     * @return the mapSelectionForm
     */
    public PreGameScrollSelectionForm getPreGameSelectionForm()
    {
        return preGameScrollSelectionForm;
    }
}
