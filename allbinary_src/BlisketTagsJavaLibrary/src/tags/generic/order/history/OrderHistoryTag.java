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
package tags.generic.order.history;

import java.lang.reflect.Method;

import abcs.logic.communication.log.LogUtil;

import abcs.logic.system.security.licensing.LicensingException;

import taghelpers.OrderHistoryHelperFactory;

import allbinary.business.user.commerce.inventory.order.OrderHistoryData;

import tags.StoreValidationTransformTag;

import abcs.logic.communication.http.request.AbResponseHandler;
import abcs.logic.communication.log.LogFactory;
import javax.servlet.jsp.JspTagException;

public class OrderHistoryTag extends StoreValidationTransformTag
{
   //private String id;
   private String status;
   //private String groupId;
   /*
   private String shipped;
   private String partiallyShipped;
   private String processing;
   private String preprocessing;
   private String cancelled;
    */
   public OrderHistoryTag()
   {
   }
   
/*
   public void setId(String id)
   {
      this.id=id;
   }
 */
   
   public void setStatus(String value)
   {
      this.status=value;
   }
   
  /*
   public void setGroupId(String value)
   {
      this.groupId=value;
   }
   */
   /*
   public void setShipped(String value)
   {
      this.shipped=value;
   }
    
   public void setPartiallyShipped(String value)
   {
      this.partiallyShipped=value;
   }
    
   public void setPreprocessing(String value)
   {
      this.preprocessing=value;
   }
    
   public void setProcessing(String value)
   {
      this.processing=value;
   }
    
   public void setCancelled(String value)
   {
      this.cancelled=value;
   }
    */
   
   private String setOrderStatus() throws LicensingException
   {
      try
      {
         Object object =
         new OrderHistoryHelperFactory().getInstance(
         this.getPropertiesHashMap(), pageContext);
         
         Class helperClass = object.getClass();
         
         Method method = helperClass.getMethod("setOrderStatus",null);
         
         String result = (String) method.invoke(object, null);
         return result;
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed to view order table";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"setOrderStatus()",e));
         }
         return error;
      }
   }
   
   public int doStartTag() throws JspTagException
   {
      try
      {
         this.setName("Basic Order History View");
         this.setObjectFile("views.generic.order.history.ValidationView");
         
         if(this.getCommand()!=null)
         {
            
            if (this.getCommand().compareTo(OrderHistoryData.SETSTATUS)==0)
            {
               this.getPropertiesHashMap().put(OrderHistoryData.STATUS, this.status);
               pageContext.getOut().print(this.setOrderStatus());
            }
            else
               if (this.getCommand().compareTo(allbinary.globals.GLOBALS.VIEW)==0)
               {
                  
               }
               else
               {
                  throw new Exception("No Such View Command: " + this.getCommand());
               }
            return super.doStartTag();
         }
         throw new Exception("Command Null");
      }
      catch(LicensingException e)
      {
         AbResponseHandler.sendJspTagLicensingRedirect(
         this.pageContext,
         e);
         return SKIP_BODY;
      }
      catch(Exception e)
      {
         AbResponseHandler.sendJspTagRedirect(
         this.pageContext,
         e);
         return SKIP_BODY;
      }
   }
   
}

