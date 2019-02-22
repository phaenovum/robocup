package eu.phaenovum.robocup.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.BaseSensor;

/**
 * 
 * @author Felix Joeken
 *
 * @param <T> The Type of the Sensor (you can leave this empty)
 */
public class EasySensor<T extends BaseSensor> {
	
	private static final int offset = 0;
	private BaseSensor sensor;
	private float[] sample;
	
	/**
	 * 
	 * @param port the sensor's port
	 * @param typeClass typeClass extends BaseSensor
	 */
	public EasySensor(Port port, Class<T> typeClass) {
		try {
			Constructor<T> constructor = typeClass.getConstructor(Port.class);
			sensor = constructor.newInstance(port);
			
		} catch (InvocationTargetException | InstantiationException | IllegalAccessException  | NoSuchMethodException  | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sample = new float[sensor.sampleSize()];
	}
	
	/**
	 * 
	 * Look's up the Sensor's value and returns it
	 * 
	 * @return first Entry of the sample list
	 */
	public float getValue() {
		try {
			sensor.fetchSample(sample, offset);
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
		return sample[0];
	}
	
	public BaseSensor getSensor() {
		return sensor;
	}
	
	public float[] getSample() {
		return sample;
	} 
	
//	just for Testing: 
//	
//	public static void main(String[] args) {
//		EasySensor<EV3UltrasonicSensor> ultra= new EasySensor<>(SensorPort.S4, EV3UltrasonicSensor.class);
//		while (Button.ENTER.isUp()) {
//			LCD.clear();
//			LCD.drawString("" + ultra.getSample(), 0, 0);
//			Time.sleep(200);
//		}
//	}
}
