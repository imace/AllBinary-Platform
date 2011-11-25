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
package org.allbinary.android.device;

import allbinary.graphics.color.BasicColor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

public class TrueTypeFontUtil
{
    private static final TrueTypeFontUtil instance = new TrueTypeFontUtil();

    public static TrueTypeFontUtil getInstance()
    {
        return instance;
    }

    public final String pattern = " 0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.?!$%`�\"�^&*()_+-=[]{};'#:@~,/<>\\|��";

    private final int[] _characterWidth = new int[pattern.length()];
    private final char[] characterArray = new char[1];
    private final int size = pattern.length();
    private final int lastCapIndex = pattern.indexOf('Z');

    public Bitmap getFontBitmap(String filename, int fontSize, int cellSize, BasicColor basicColor)
    {
        int cellsPerRow = 13;
        int cellsPerRow2 = cellsPerRow * 2;
        int cellsPerRow3 = cellsPerRow * 3;
        int cellsPerRow4 = cellsPerRow * 4;
        int cellsPerRow5 = cellsPerRow * 5;
        int cellsPerRow6 = cellsPerRow * 6;
        int cellsPerRow7 = cellsPerRow * 7;

        Typeface typeface = Typeface.DEFAULT;
        // Typeface.createFromAsset(
        // ResourceUtil.getInstance().getContext().getAssets(),
        // filename);

        Bitmap bitmap = Bitmap.createBitmap(cellsPerRow * cellSize, 8 * cellSize,
                Bitmap.Config.ARGB_8888);
        // AndroidBitmapConfigUtil.get());

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setTypeface(typeface);
        paint.setTextSize(fontSize);
        paint.setARGB((int) (basicColor.getAlphaComponent() * 255),
                (int) (basicColor.getRedComponent() * 255),
                (int) (basicColor.getGreenComponent() * 255),
                (int) (basicColor.getBlueComponent() * 255));

        int biggestHeight = 0;
        Rect bounds = new Rect();
        int x;
        int y;
        for (int index = 0; index < size; index++)
        {
            characterArray[0] = pattern.charAt(index);
            paint.getTextBounds(characterArray, 0, 1, bounds);
            _characterWidth[index] = bounds.right;
            if (bounds.bottom - bounds.top > biggestHeight)
                biggestHeight = bounds.bottom - bounds.top;
            x = (index % cellsPerRow) * cellSize;
            x += (cellSize >> 1);
            x -= (_characterWidth[index] >> 1);
            y = 0;
            if (index >= cellsPerRow)
                y += cellSize;
            if (index >= cellsPerRow2)
                y += cellSize;
            if (index >= cellsPerRow3)
                y += cellSize;
            if (index >= cellsPerRow4)
                y += cellSize;
            if (index >= cellsPerRow5)
                y += cellSize;
            if (index >= cellsPerRow6)
                y += cellSize;
            if (index >= cellsPerRow7)
                y += cellSize;
            y += cellSize;
            y -= (cellSize >> 2);
            canvas.drawText(characterArray, 0, 1, x, y, paint);
        }
        canvas.save();

        return bitmap;
    }

    public int[] getFontWidths(String filename, int fontSize)
    {
        Typeface typeface = Typeface.DEFAULT;
        // Typeface.createFromAsset(
        // ResourceUtil.getInstance().getContext().getAssets(),
        // filename);

        Paint paint = new Paint();
        paint.setTypeface(typeface);
        paint.setTextSize(fontSize);
        paint.setARGB(255, 255, 255, 255);

        Rect bounds = new Rect();
        for (int index = 0; index < size; index++)
        {
            characterArray[0] = pattern.charAt(index);
            paint.getTextBounds(characterArray, 0, 1, bounds);

            if (index < lastCapIndex)
            {
                if (characterArray[0] == 'J' || characterArray[0] == 'V')
                {
                    _characterWidth[index] = bounds.right + 1;
                } else if (characterArray[0] == '1' || characterArray[0] == '2'
                        || characterArray[0] == '9' || characterArray[0] == 'B'
                        || characterArray[0] == 'H' || characterArray[0] == 'I'
                        || characterArray[0] == 'N' || characterArray[0] == 'S'
                        || characterArray[0] == 'U')
                {
                    _characterWidth[index] = bounds.right + 1;
                } else if (characterArray[0] == '4' || characterArray[0] == 'C'
                        || characterArray[0] == 'M' || characterArray[0] == 'O')
                {
                    _characterWidth[index] = bounds.right - 1;
                } else if (characterArray[0] == 'A' || characterArray[0] == 'T'
                        || characterArray[0] == 'W')
                {
                    _characterWidth[index] = bounds.right - 2;
                } else
                {
                    _characterWidth[index] = bounds.right;
                }

            } else
            {
                if (characterArray[0] == 'l')
                {
                    _characterWidth[index] = bounds.right + 3;
                } else if (characterArray[0] == 'i' || characterArray[0] == 'j'
                        || characterArray[0] == '.' || characterArray[0] == '!'
                        || characterArray[0] == '|')
                {
                    _characterWidth[index] = bounds.right + 2;
                } else if (characterArray[0] == 'g')
                {
                    _characterWidth[index] = bounds.right - 2;
                } else if (characterArray[0] == 'm')
                {
                    _characterWidth[index] = bounds.right - 1;
                } else if (characterArray[0] == 'f' || characterArray[0] == 't'
                        || characterArray[0] == 'u' || characterArray[0] == 'v'
                        || characterArray[0] == 'y')
                {
                    _characterWidth[index] = bounds.right + 1;
                } else
                {
                    _characterWidth[index] = bounds.right;
                }
            }
        }

        _characterWidth[0] = (fontSize >> 1) - 2;

        return _characterWidth;
    }
}