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
package allbinary.data.tables.user.commerce.inventory.item;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

public class InventoryEntityFactory
{
    private static final InventoryEntityFactory instance =
            new InventoryEntityFactory();

    /**
     * @return the instance
     */
    public static InventoryEntityFactory getInstance() {
        return instance;
    }

   //private final String CLASSNAME = "allbinary.data.tables.InventoryEntity";
   
   private InventoryEntityFactory()
   {
   }

   public InventoryEntity getInventoryEntityInstance() //throws LicensingException
   {
      try
      {
         //Object object = AbeFactory.getInstance(CLASSNAME);
         //return (InventoryEntityInterface) InterfaceCastProxy.newInstance(object);
         return new allbinary.data.tables.user.commerce.inventory.item.InventoryEntity();
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
            LogUtil.put(LogFactory.getInstance(error, "InventoryEntityFactory","getInventoryEntityInstance()", e));
         }
         return null;
      }
   }
   
}
