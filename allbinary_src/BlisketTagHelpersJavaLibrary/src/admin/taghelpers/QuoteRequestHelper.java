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
package admin.taghelpers;

import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.context.modules.storefront.StoreFrontData;
import allbinary.business.context.modules.storefront.StoreFrontFactory;
import allbinary.business.context.modules.storefront.StoreFrontInterface;
import allbinary.business.user.quoterequest.QuoteRequest;
import allbinary.data.tables.user.quoterequest.QuoteRequestEntityFactory;
import allbinary.logic.communication.http.request.session.WeblisketSession;

//TWB - Am I missing the correct Quote Request? Probably not
public class QuoteRequestHelper
    implements ModifyTableInterface
{
   private WeblisketSession weblisketSession;
   
   private StoreFrontInterface storeFrontInterface;
   
   private QuoteRequest quoteRequest;
   
   public QuoteRequestHelper(HashMap hashMap, PageContext pageContext) 
      throws Exception
   {
      HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
      this.weblisketSession = new WeblisketSession(hashMap, pageContext);
      this.quoteRequest = new QuoteRequest(this.weblisketSession.getUserName(), request);
          //new QuoteRequest((WeblisketSessionInterface) this.weblisketSession, request);

      String storeName = (String) hashMap.get(StoreFrontData.getInstance().NAME);
      
      if(storeName!=null)
      {
         this.storeFrontInterface = StoreFrontFactory.getInstance(storeName);
      }
   }
         
   public Boolean isValid()
   {
      return this.quoteRequest.isValid();
   }
   
   public String validationInfo()
   {
      return this.quoteRequest.validationInfo();
   }

   public String insert()
   {
      try
      {
         Vector values = this.quoteRequest.toVector();

         QuoteRequestEntityFactory.getInstance().getQuoteRequestEntityInstance().insert(values);

         String success
            = "New QuoteRequest Successfully added to the QuoteRequest Table";

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"add()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to add QuoteRequest";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "add()", e));
         }
         return error;
      }
   }
     
   public String update()
   {
      try
      {         
         HashMap values = this.quoteRequest.toHashMap();

         QuoteRequestEntityFactory.getInstance().getQuoteRequestEntityInstance().update(
            this.quoteRequest.getUserName(),values);

         String success = "New QuoteRequest Successfully updated";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success, this, "update()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to update QuoteRequest";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"update()",e));
         }
         return error;
      }
      
   }
   
   public String delete()
   {
      return "Not Implemented";
   }   
}
