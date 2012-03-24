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
package allbinary.game.layer.hud.basic.event;

import org.allbinary.util.BasicArrayList;

//import allbinary.game.layer.hud.basic.notification.GameNotificationHud;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.EventListenerInterface;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class GameNotificationEventHandler extends BasicEventHandler
{
   private static final GameNotificationEventHandler instance = 
      new GameNotificationEventHandler();

   //private final BasicArrayList list = new BasicArrayList();
   
   private GameNotificationEventHandler()
   {
   }

   /*
   public void addListener(GameNotificationHud gameNotificationHud)
   {
       if(!list.contains(gameNotificationHud))
       {
           list.add(gameNotificationHud);
       }
   }

   public void removeListener(EventListenerInterface eventListenerInterface)
   {
       super.removeListener(eventListenerInterface);
   }

   public void fireEvent(AllBinaryEventObject eventObject) throws Exception
   {        
       for (int index = this.list.size(); --index >= 0;)
       {
           try
           {
               GameNotificationHud gameNotificationHud = (GameNotificationHud) this.list.get(index);
               gameNotificationHud.onGameNotificationEvent((GameNotificationEvent) eventObject);
           }
           catch (Exception e)
           {
               LogUtil.put(new Log(CommonStrings.getInstance().EXCEPTION, this, "fireEvent", e));
           }
       }

       super.fireEvent(eventObject);
   }
    */
   
   //This is not the default optimal method
   public synchronized void removeAllListeners()
   {
       EventListenerInterface eventListenerInterface = null;

       /*
       if(this.list.size() > 0)
       {
           eventListenerInterface = (EventListenerInterface) this.list.get(0);
       }
       
       this.list.clear();

       if(eventListenerInterface != null)
       {
           this.addListener((GameNotificationHud) eventListenerInterface);
       }
       */
       
       //Keep the system specific listener around
       BasicArrayList list = this.getEventListenerInterfaceList();
       
       if(list.size() > 0)
       {
           eventListenerInterface = (EventListenerInterface) list.objectArray[0];
       }
       
       super.removeAllListeners();
       
       if(eventListenerInterface != null)
       {
           this.addListener(eventListenerInterface);
       }
   }
   
   public static GameNotificationEventHandler getInstance()
   {
      return instance;
   }
   
   protected void process(AllBinaryEventObject eventObject,
           EventListenerInterface eventListenerInterface)
   throws Exception
   {

      ((GameNotificationListenerInterface) eventListenerInterface).onGameNotificationEvent(
              (GameNotificationEvent) eventObject);
   }
}
