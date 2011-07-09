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
package allbinary.data.tables.user.commerce.inventory.item.special;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

public class SpecialItemsEntityFactory
{
    private static final SpecialItemsEntityFactory instance
            = new SpecialItemsEntityFactory();

    /**
     * @return the instance
     */
    public static SpecialItemsEntityFactory getInstance() {
        return instance;
    }

   //private final String CLASSNAME = "allbinary.data.tables.InventoryEntity";
   
   private SpecialItemsEntityFactory()
   {
   }
   
   public SpecialItemsEntity getSpecialItemsEntityInstance() //throws LicensingException
   {
      try
      {
         //Object object = AbeFactory.getInstance(CLASSNAME);
         //return (InventoryEntityInterface) InterfaceCastProxy.newInstance(object);
         return new allbinary.data.tables.user.commerce.inventory.item.special.SpecialItemsEntity();
      }
      /*
      catch(LicensingException e)
      {
         String error = "Failed to get instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.ENTITYFACTORYERROR))
         {
            LogUtil.put(error,"InventoryEntityFactory","getInstance()",e);
         }
         throw e;
      }
      */
      catch(Exception e)
      {
         String error = "Failed to get instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,"InventoryEntityFactory","getInstance",e));
         }
         return null;
      }
   }
   
}
