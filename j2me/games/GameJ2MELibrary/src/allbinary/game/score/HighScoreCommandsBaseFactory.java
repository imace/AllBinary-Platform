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
package allbinary.game.score;

import javax.microedition.lcdui.Command;

public class HighScoreCommandsBaseFactory {
    
    public final Command[] HIGH_SCORE_COMMANDS;

    protected HighScoreCommandsBaseFactory(Command[] HIGH_SCORE_COMMANDS)
    {
        this.HIGH_SCORE_COMMANDS = HIGH_SCORE_COMMANDS;
    }

    public boolean isHighScoreCommand(Command command)
    {
        for(int index = HIGH_SCORE_COMMANDS.length - 1; index >= 0; index--)
        {
            if(command == HIGH_SCORE_COMMANDS[index])
            {
                return true;
            }
        }
        return false;
    }

    public int getIndex(Command command)
    throws Exception
    {
        for(int index = HIGH_SCORE_COMMANDS.length - 1; index >= 0; index--)
        {
            if(command == HIGH_SCORE_COMMANDS[index])
            {
                return index;
            }
        }
        throw new Exception("No Such Command");
    }
}
