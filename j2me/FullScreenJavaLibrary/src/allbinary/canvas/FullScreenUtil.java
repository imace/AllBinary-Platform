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
package allbinary.canvas;

import allbinary.game.configuration.feature.Features;
import allbinary.game.configuration.feature.MainFeatureFactory;

import javax.microedition.lcdui.CommandListener;

public class FullScreenUtil
{

    public static final void init(
        FullScreenInterface fullScreenInterface,
        CommandListener commandListener)
        throws Exception
    {
        fullScreenInterface.waitOnNotify(0);

        if (commandListener != null)
        {
            FullScreenUtil.init(fullScreenInterface);
        }
    }

    public static final void init(FullScreenInterface fullScreenInterface)
    {
        MainFeatureFactory mainFeatureFactory =
            MainFeatureFactory.getInstance();

        Features features = Features.getInstance();

        if (features.isFeature(mainFeatureFactory.FULL_SCREEN))
        {
            fullScreenInterface.setFullScreenMode(true);
        }
        else
        {
            fullScreenInterface.setFullScreenMode(false);
        }
    }

    public static final boolean isScreenChange(
        boolean isFullScreen)
    {
        MainFeatureFactory mainFeatureFactory =
            MainFeatureFactory.getInstance();

        Features features = Features.getInstance();

        if (features.isFeature(mainFeatureFactory.FULL_SCREEN) != isFullScreen)
        {
            return true;
        }

        return false;
    }

    public static final boolean isScreenChange(
        FullScreenInterface fullScreenInterface)
    {
        return isScreenChange(fullScreenInterface.isFullScreenMode());
    }
}
