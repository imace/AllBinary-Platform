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
package allbinary.logic.visual.transform.template;

import java.util.HashMap;

import javax.servlet.jsp.PageContext;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.data.tables.transform.info.TransformInfoEntity;
import allbinary.data.tables.transform.info.TransformInfoEntityBuilder;
import allbinary.logic.visual.transform.info.TransformInfoInterface;
import allbinary.logic.visual.transform.info.TransformInfoObjectFactory;

public class TransformTemplateFactory
{
   private TransformTemplateFactory()
   {
   }

   //create a root/parent instance from db and/or request
   public static TransformTemplateInterface getInstance(
         String templateName, 
         HashMap propertiesHashMap, 
         PageContext pageContext) 
         throws Exception
   {
      try
      {
         TransformInfoEntity transformInfoEntity =
        	 TransformInfoEntityBuilder.getInstance();

         TransformInfoInterface transformInfoInterface =
            transformInfoEntity.get(
               templateName, propertiesHashMap, pageContext);

         return (TransformTemplateInterface) 
            TransformInfoObjectFactory.getInstance(transformInfoInterface);
      }
      catch(Exception e)
      {
         String error = "Failed To Get Instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, templateName + "->TemplateFactory", "getInstance(viewName, HashMap, PageContext)", e));
         }
         throw e;
      }
   }
}