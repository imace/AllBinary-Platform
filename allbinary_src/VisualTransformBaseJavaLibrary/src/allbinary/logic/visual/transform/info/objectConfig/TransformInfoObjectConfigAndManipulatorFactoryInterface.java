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
package allbinary.logic.visual.transform.info.objectConfig;

import abcs.logic.basic.path.AbPath;
import allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.w3c.dom.Document;

/**
 *
 * @author user
 */
public interface TransformInfoObjectConfigAndManipulatorFactoryInterface {

    TransformInfoObjectConfigInterface getInstance(TransformInfoInterface transformInfoInterface, AbPath objectConfigFileAbPath) throws Exception;

    TransformInfoObjectConfigInterface getInstance(TransformInfoInterface transformInfoInterface) throws Exception;

    TransformInfoObjectConfigInterface getInstance(TransformInfoInterface transformInfoInterface, Document document) throws Exception;

}
