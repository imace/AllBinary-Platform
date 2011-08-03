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
package abcs.logic.basic.io;

import java.io.DataOutputStream;
import java.io.OutputStream;

/**
 *
 * @author user
 */
public class AbDataOutputStream
    extends DataOutputStream
{
    public AbDataOutputStream(OutputStream out)
    {
        super(out);
    }
}
