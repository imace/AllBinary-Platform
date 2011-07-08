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
package allbinary.business.context.modules.storefront.statistics.users;

import allbinary.business.context.modules.storefront.StoreFrontInterface;
import allbinary.business.user.UserInterface;
import allbinary.business.user.role.UserRole;
import allbinary.data.tables.user.UserEntityFactory;
import allbinary.data.tables.user.UserEntityInterface;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class StoreFrontUsersStatistics implements StoreFrontUsersStatisticsInterface
{
   private Long totalNumberOfUsers;
   private HashMap totalUsersByRoleHashMap;
   
   public StoreFrontUsersStatistics(StoreFrontInterface storeFrontInterface) throws Exception
   {
      this.totalUsersByRoleHashMap = new HashMap();
      UserEntityInterface userEntityInterface = 
         UserEntityFactory.getInstance();
      //Vector userVector = userEntityInterface.getUsers(storeFrontInterface);
      Vector userVector = userEntityInterface.getCustomers();
      this.totalNumberOfUsers = new Long(userVector.size());
      Iterator iter = userVector.iterator();

      while(iter.hasNext())
      {
         UserInterface userInterface = (UserInterface) iter.next();
         UserRole nextUserRole = userInterface.getRole();
         
         Long currentNumberOfUsersForRole = this.getNewTotal(nextUserRole);

         totalUsersByRoleHashMap.put(nextUserRole, currentNumberOfUsersForRole);
      }
   }

   private Long getNewTotal(UserRole userRole)
   {
       Long numberOfUsersForRoleLong = (Long) totalUsersByRoleHashMap.get(userRole);
       
       if(numberOfUsersForRoleLong == null)
       {
           numberOfUsersForRoleLong = new Long(0);
       }
       
       return new Long(numberOfUsersForRoleLong.longValue() + 1);
   }

   public Long getNumberOfUsers()
   {
      return this.totalNumberOfUsers;
   }

   public Long getNumberOfUsersByRole(String role)
   {
      Long totalForRole = (Long) this.totalUsersByRoleHashMap.get(role);
      return totalForRole;
   }
   
   public HashMap toHashMap()
   {
      HashMap hashMap = new HashMap();
      
      hashMap.put(StoreFrontUsersStatisticsData.getInstance().NUMBEROFUSERS, this.getNumberOfUsers().toString());
      
      Set setOfUserRoles = this.totalUsersByRoleHashMap.keySet();
      Iterator iter = setOfUserRoles.iterator();
      while(iter.hasNext())
      {
         UserRole nextUserRole = (UserRole) iter.next();
	 Long totalForRole = (Long) this.totalUsersByRoleHashMap.get(nextUserRole);
         hashMap.put(nextUserRole.toString(), totalForRole.toString());
      }
      return hashMap;
   }
   
   public Vector toVector()
   {
      return null;
   }

   public Object getKey()
   {
      return null;
   }
   
}