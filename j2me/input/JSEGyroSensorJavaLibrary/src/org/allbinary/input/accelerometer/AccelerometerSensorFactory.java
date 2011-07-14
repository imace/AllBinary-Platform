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
package org.allbinary.input.accelerometer;

import org.allbinary.input.gyro.AllBinaryOrientationSensor;

import allbinary.game.configuration.feature.Features;
import allbinary.game.configuration.feature.SensorFeatureFactory;

public class AccelerometerSensorFactory
{
    //private static final AccelerometerSensorFactory instance = new AccelerometerSensorFactory();
    
    private static AllBinaryOrientationSensor allBinaryAccelerometerSensor;
    
    public static void init() throws Exception
    {
        //PreLogUtil.put("Start", instance, "init");
        
        Features features = 
            Features.getInstance();
        SensorFeatureFactory sensorFeatureFactory = 
            SensorFeatureFactory.getInstance();
        
        if (features.isFeature(
                sensorFeatureFactory.ORIENTATION_SENSORS))
        {
            throw new Exception();
        }
        else if (features.isFeature(
                sensorFeatureFactory.SIMULATED_ORIENTATION_SENSORS))
        {
            throw new Exception();
        }
        else if (features.isFeature(
                sensorFeatureFactory.NO_ORIENTATION))
        {
            allBinaryAccelerometerSensor = new NoAccelerometerSensor();
        }
        else
        {
            throw new Exception("Not Such SensorFeature");
        }
    }
    
    public static AllBinaryOrientationSensor getInstance()
    {
        return allBinaryAccelerometerSensor;
    }

}
