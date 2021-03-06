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
package tags.workflow.processing.basic;

import javax.servlet.jsp.*;

import abcs.logic.communication.log.LogUtil;


import abcs.logic.communication.http.request.AbResponseHandler;
import abcs.logic.communication.log.LogFactory;

import allbinary.business.DynamicObjectData;

import tags.StoreWorkFlowTag;

//Future implementation use the workflow url to specify the correct customizer view
//and remove the hidden fields
public class BasicWorkFlowTag extends StoreWorkFlowTag
{
   public BasicWorkFlowTag()
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAG))
      {
         LogUtil.put(LogFactory.getInstance("Tag Constructed",this,"BasicWorkFlowTag()"));
      }
   }
   
   public int doStartTag() throws JspTagException
   {
      try
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAG))
         {
            LogUtil.put(LogFactory.getInstance("Start",this,"doStartTag()"));
         }

         //Temporary should be replaced by view in xml of workflow
         this.getPropertiesHashMap().put(DynamicObjectData.NAME,"workflows.template.data.BasicStoreWorkFlow");
         
         return super.doStartTag();
      }
      catch(Exception e)
      {
         AbResponseHandler.sendJspTagRedirect(this.pageContext, e);
         return SKIP_BODY;
      }
   }
}
