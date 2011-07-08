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
package tags.generic.shipping;

import abcs.logic.system.security.licensing.LicensingException;

import tags.StoreValidationTransformTag;

import abcs.logic.communication.http.request.AbResponseHandler;
import javax.servlet.jsp.JspTagException;

public class ShippingMethodsTag extends StoreValidationTransformTag
{
   //private String shippingType;
   
   public ShippingMethodsTag()
   {
   }
   
   public int doStartTag() throws JspTagException
   {
      try
      {
         if(this.getCommand()!=null)
         {
            
            this.setName("Basic Shipping View");
            this.setObjectFile("views.generic.shipping.ValidationView");
            //   this.propertiesHashMap.put(ShippingData.SHIPPINGTYPE, this.shippingType);
            
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
