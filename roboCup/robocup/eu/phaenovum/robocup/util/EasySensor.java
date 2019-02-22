package eu.phaenovum.robocup.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.BaseSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;

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
	private final SampleProvider provider;
	
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
		
		if (sensor instanceof EV3GyroSensor) {
			provider = ((EV3GyroSensor) sensor).getAngleMode();
		}else {
			provider = new SampleProvider() {
				
				@Override
				public int sampleSize() {
					return 1;
				}
				
				@Override
				public void fetchSample(float[] sample, int offset) {
					sensor.fetchSample(sample, offset);
				}
			};
		}
	}
	
	/**
	 * 
	 * Look's up the Sensor's value and returns it
	 * 
	 * @return first Entry of the sample list
	 */
	public float getValue() {
		provider.fetchSample(sample, offset);
		return sample[0];
	}
	
	public float getValue(int index) {
		provider.fetchSample(sample, offset);
		return sample[index];
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
