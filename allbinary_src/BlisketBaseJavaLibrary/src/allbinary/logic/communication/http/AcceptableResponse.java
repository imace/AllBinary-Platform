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
package allbinary.logic.communication.http;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

public class AcceptableResponse
{

    protected AcceptableResponse(String candidate, String tagName, int index)
    {
        LogUtil.put(LogFactory.getInstance(tagName, this, "Constructor"));

        AcceptableResponseUtil acceptableResponseUtil =
            AcceptableResponseUtil.getInstance();

        acceptableResponseUtil.candidateVector.add(index, candidate);
        acceptableResponseUtil.tagNameVector.add(index, tagName);
    }
}
