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
package allbinary.graphics.canvas.transition.progress;

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.form.item.CustomGaugeItem;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.basic.string.StringUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.canvas.RunnableCanvas;
import allbinary.game.commands.GameCommandsFactory;
import allbinary.graphics.color.BasicColor;
import allbinary.graphics.font.MyFont;
import allbinary.graphics.paint.PaintableInterface;
import allbinary.logic.math.SmallIntegerSingletonFactory;
import allbinary.midlet.AllBinaryMidlet;

public class ProgressCanvas extends RunnableCanvas
    implements PaintableInterface
{
    protected AllBinaryMidlet allbinaryMidlet;

    private float value;
    private final float maxValue = 100;

    private final CustomGaugeItem gauge;

    private final String TEXT = CommonStrings.getInstance().LOADING;
    private String text = TEXT;

    private boolean background = true;
    
    protected ProgressCanvas()
    {
        gauge = null;
    }
    
    protected ProgressCanvas(String title, BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
        this.gauge = new CustomGaugeItem(StringUtil.getInstance().EMPTY_STRING, (int) maxValue, 0, 
                backgroundBasicColor, foregroundBasicColor);
    }

    public void init(AllBinaryMidlet gameMidlet)
    {
        this.allbinaryMidlet = gameMidlet;
    }
    
    public void update(Graphics graphics) throws Exception
    {
        
    }

    /*
    public boolean isInitialized()
    {
        if (this.allbinaryMidlet != null)
            return true;
        else
            return false;
    }
    */

    public void initCommands(CommandListener cmdListener)
    {
        // this.removeAllCommands();
        // this.addCommand(MYCOMMANDS.EXITCOMMAND);
        // this.addCommand(SELECTCOMMAND);
        // this.setCommandListener(cmdListener);
    }

    /*
     * public boolean isDone() { if (this.PROGRESS_SCREEN.getValue() <
     * this.PROGRESS_SCREEN .getMaxValue()) { return false; } else { return
     * true; } }
     */

    protected final float getMaxValue()
    {
        return this.maxValue;
    }

    public void start()
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().START_METHOD_NAME));
        this.setBackground(true);
        this.gauge.setHeight(30);
        this.gauge.setLabel(CommonStrings.getInstance().PLEASE_WAIT);
        this.setText(TEXT);
        this.setValue(0);
        //this.setDisplayed(false);
    }
    
    private final String backgroundLabel = "Background AI Game Loading...";
    
    public void startBackground(boolean background)
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "startBackground"));
        this.setBackground(background);
        this.gauge.setHeight(MyFont.getInstance().DEFAULT_CHAR_HEIGHT + 2);
        this.gauge.setLabel(backgroundLabel);
        this.setText(TEXT);
        this.setValue(0);
    }
    
    public void end()
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().END_METHOD_NAME));
        this.gauge.setValue(this.getMaxValue());
        //getCommandListener()
        this.allbinaryMidlet.commandAction(
                GameCommandsFactory.getInstance().SHOW_GAME_CANVAS, null);
    }

    protected final String ADD_PORTION = "addPortion";
    protected final String ADD_EARLY_PORTION = "addEarlyPortion";
    
    public void addEarlyPortion(int value, String text, int index)
    {
        //LogUtil.put(LogFactory.getInstance(this.text, this, ADD_EARLY_PORTION));
        //PreLogUtil.put(this.text, this, ADD_PORTION);
        
        this.setText(text + SmallIntegerSingletonFactory.getInstance().getInstance(index));

        this.gauge.setValue(this.gauge.getValue() + this.getMaxValue() / value);
    }
    
    public void addPortion(int value, String text, int index)
    {
        //CommonStrings.getInstance().START_LABEL + 
        //LogUtil.put(LogFactory.getInstance(this.text, this, ADD_PORTION));
        //PreLogUtil.put(this.text, this, ADD_PORTION);
        
        this.setText(text + SmallIntegerSingletonFactory.getInstance().getInstance(index));

        this.gauge.setValue(this.gauge.getValue() + this.getMaxValue() / value);

        //For Help calculating portion time and such
        //BaseRefreshHelper.process();
    }
    
    public void addPortion(int value, String text)
    {
        //CommonStrings.getInstance().START_LABEL + 
        //LogUtil.put(LogFactory.getInstance(text, this, ADD_PORTION));
        //PreLogUtil.put(text, this, ADD_PORTION);

        this.setText(text);

        this.gauge.setValue(this.gauge.getValue() + this.getMaxValue() / value);

        //For Help calculating portion time and such
        //BaseRefreshHelper.process();
    }

    protected void setValue(int value)
    {
        this.value = value;
        this.gauge.setValue(value);
    }

    public void paint(Graphics graphics)
    {
        // LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "paint"));
     
        this.gauge.paint(graphics, 0, 0);
    }

    public void paintThreed(Graphics graphics)
    {
    	
    }
    /*
    public void waitUntilDisplayed()
    {
        try
        {
            int index = 0;
            while ((!this.isDisplayed()) && index < 50)
            {
                LogUtil.put(LogFactory.getInstance(
                        "Waiting for it to be displayed", this,
                        "waitUntilDisplayed"));
                Thread.sleep(200);
                index++;
            }
            LogUtil.put(LogFactory.getInstance("Displayed", this,
                    "waitUntilDisplayed"));

        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this,
                    "waitUntilDisplayed", e));
        }
    }
    */

    protected float getValue()
    {
        return value;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return text;
    }
    
    protected void setBackground(boolean background)
    {
        this.background = background;
    }

    protected boolean isBackground()
    {
        return background;
    }
}
