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
package allbinary.logic.visual.transform.info;

import allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigNoManipulationFactory;
import java.util.HashMap;
import javax.servlet.jsp.PageContext;

/**
 *
 * @author user
 */
public class TransformInfoHttpStoreNoManipulation
extends TransformInfoHttpStore
{
   //TWB - for Inserting transforms into database
   public TransformInfoHttpStoreNoManipulation(
       HashMap propertiesHashMap, PageContext pageContext)
      throws Exception
   {
      super(propertiesHashMap, pageContext);
   }

   protected void setObjectConfigFile(String value) throws Exception
   {
      this.setObjectConfigFileName(value);
      
      this.setObjectConfigInterface(
         TransformInfoObjectConfigNoManipulationFactory.getInstance(
            this, this.getObjectConfigFilePath()));
   }

   protected void setObjectConfig(String value) throws Exception
   {
      throw new Exception("Should not set from actual config");
   }
}
