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
package abcs.logic.java.object;

import abcs.logic.basic.string.CommonSeps;
import java.lang.reflect.Constructor;


public class ConstructorUtil
{
   private ConstructorUtil()
   {
   }

   public static String viewAll(Class myClass, String lineBreak)
   {
      StringBuffer stringBuffer = new StringBuffer();
      Constructor constructor[] = myClass.getConstructors();
      stringBuffer.append("Constructors: ");
      stringBuffer.append(lineBreak);
      for(int index = 0; index < constructor.length; index++)
      {
         stringBuffer.append(ConstructorUtil.view(constructor[index], lineBreak));
      }
      return stringBuffer.toString();
   }
   
   public static String view(Constructor constructor, String lineBreak)
   {
      if(constructor != null)
      {
         StringBuffer stringBuffer = new StringBuffer();
         stringBuffer.append(constructor.getName());
         Class classes[] = constructor.getParameterTypes();
         for(int index = 0; index < classes.length; index++)
         {
            stringBuffer.append(CommonSeps.getInstance().SPACE);
            stringBuffer.append(classes[index].getName());
         }
         stringBuffer.append(lineBreak);
         return stringBuffer.toString();
      }
      else return "Constructor Null";
   }   
}
