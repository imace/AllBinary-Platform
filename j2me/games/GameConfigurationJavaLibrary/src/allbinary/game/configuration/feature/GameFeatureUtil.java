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
package allbinary.game.configuration.feature;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.microedition.lcdui.ChoiceGroup;

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.HashtableUtil;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

public class GameFeatureUtil
{
    private static final GameFeatureUtil instance = new GameFeatureUtil();

    public static GameFeatureUtil getInstance()
    {
        return instance;
    }

    public void setDefault(ChoiceGroup choiceGroup)
        throws Exception
    {
        StringBuilder stringBuffer = new StringBuilder();

        final String METHOD_NAME = "setDefault";
        final String SELECTED_ARRAY_RETURN = "selectedArray_return[";
        final String SELECTED_SEP = "] = ";

        boolean[] selectedArray_return = new boolean[choiceGroup.size()];
        int total = choiceGroup.getSelectedFlags(selectedArray_return);
        
        stringBuffer.append("Multiple Total Choices: ");
        stringBuffer.append(total);
        
        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, METHOD_NAME));

        Features features = Features.getInstance();

        for (int index = 0; index < selectedArray_return.length; index++)
        {
            boolean isSelected = selectedArray_return[index];
            
            stringBuffer.delete(0, stringBuffer.length());
            
            stringBuffer.append(SELECTED_ARRAY_RETURN);
            stringBuffer.append(index);
            stringBuffer.append(SELECTED_SEP);
            stringBuffer.append(isSelected);
            
            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, METHOD_NAME));
            
            String selectedChoiceLabel = choiceGroup.getString(index);
            Feature gameFeature = Feature.getInstance(selectedChoiceLabel);
            
            if (features.isDefault(gameFeature))
            {
                features.add(gameFeature);
                choiceGroup.setSelectedIndex(index, true);
            }
            else
            {
                features.remove(gameFeature);
                choiceGroup.setSelectedIndex(index, false);
            }
        }
    }

    public void updateMultiple(ChoiceGroup choiceGroup) throws Exception
    {
        StringBuilder stringBuffer = new StringBuilder();
        
        final String METHOD_NAME = "updateMultiple";
        final String SELECTED_ARRAY_RETURN = "selectedArray_return[";
        final String SELECTED_SEP = "] = ";

        boolean[] selectedArray_return = new boolean[choiceGroup.size()];
        int total = choiceGroup.getSelectedFlags(selectedArray_return);

        stringBuffer.append("Multiple Total Choices: ");
        stringBuffer.append(total);
        
        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, METHOD_NAME));

        Features features = Features.getInstance();

        for (int index = 0; index < selectedArray_return.length; index++)
        {
            boolean isSelected = selectedArray_return[index];
            
            stringBuffer.delete(0, stringBuffer.length());
            
            stringBuffer.append(SELECTED_ARRAY_RETURN);
            stringBuffer.append(index);
            stringBuffer.append(SELECTED_SEP);
            stringBuffer.append(isSelected);
            
            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, METHOD_NAME));

            String selectedChoiceLabel = choiceGroup.getString(index);
            Feature gameFeature = Feature.getInstance(selectedChoiceLabel);

            if (!isSelected && features.isFeature(gameFeature))
            {
                features.remove(gameFeature);
            }
            else if (isSelected && !features.isFeature(gameFeature))
            {
                features.add(gameFeature);
            }
        }
    }

    public void updateExclusive(ChoiceGroup choiceGroup)
            throws Exception
    {
        StringBuilder stringBuffer = new StringBuilder();

        final String METHOD_NAME = "updateExclusive";
        final String SELECTED_ARRAY_RETURN = "selectedArray_return[";
        final String SELECTED_SEP = "] = ";

        boolean[] selectedArray_return = new boolean[choiceGroup.size()];
        int total = choiceGroup.getSelectedFlags(selectedArray_return);

        stringBuffer.append("Exclusive Total Choices: 1==");
        stringBuffer.append(total);
        
        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, METHOD_NAME));

        for (int index = 0; index < selectedArray_return.length; index++)
        {
            boolean isSelected = selectedArray_return[index];

            stringBuffer.delete(0, stringBuffer.length());

            stringBuffer.append(SELECTED_ARRAY_RETURN);
            stringBuffer.append(index);
            stringBuffer.append(SELECTED_SEP);
            stringBuffer.append(isSelected);

            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, METHOD_NAME));

            if (isSelected)
            {
                String selectedChoiceLabel = choiceGroup.getString(index);
                updateExclusive(selectedChoiceLabel);
            }
        }
    }

    public boolean isExclusive(String itemLabel)
    {
        Enumeration enumeration = 
            GameFeatureChoiceGroups.getExclusiveInstance().get().keys();

        while (enumeration.hasMoreElements())
        {
            String name = (String) enumeration.nextElement();

            if (itemLabel.compareTo(name) == 0)
            {
                return true;
            }
        }

        return false;
    }

    private void updateExclusive(String selectedChoiceLabel)
            throws Exception
    {
        LogUtil.put(LogFactory.getInstance(
                "Item: " + selectedChoiceLabel, this, "updateExclusive"));

        Feature gameFeature = Feature.getInstance(selectedChoiceLabel);

        Hashtable hashtable = GameFeatureChoiceGroups.getExclusiveInstance().get();
        
        Object[] objectArray = HashtableUtil.getInstance().getKeysAsArray(hashtable);
        int size = objectArray.length;
        for (int index = 0; index < size; index++)
        {
            updateExclusive(gameFeature, (BasicArrayList) hashtable
                    .get(objectArray[index]));
        }

        /*
         * if (gameFeature == GameFeature.IMAGE_GRAPHICS) {
         * 
         * GameFeature.remove(GameFeature.VECTOR_GRAPHICS);
         * GameFeature.add(GameFeature.IMAGE_GRAPHICS);
         *  } else if (gameFeature == GameFeature.VECTOR_GRAPHICS) {
         * 
         * GameFeature.remove(GameFeature.IMAGE_GRAPHICS);
         * GameFeature.add(GameFeature.VECTOR_GRAPHICS); }
         */
    }

    public void updateExclusive(Feature gameFeature, BasicArrayList list)
    throws Exception
    {
        if (list.contains(gameFeature))
        {
            Features features = Features.getInstance();
            
            int addIndex = list.indexOf(gameFeature);
            int size = list.size();
            
            for (int index = 0; index < size; index++)
            {
                if(addIndex != index)
                {
                    features.remove((Feature) list.objectArray[index]);
                }
                else
                {
                    features.add((Feature) list.objectArray[index]);
                }
            }
        }
    }
}
