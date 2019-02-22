package eu.phaenovum.robocup;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;

/**
 * 
 * @author Yael
 * @deprecated better use EasySensor
 */
@Deprecated
public class Gyrosensor {
	
	EV3GyroSensor gyro;
	final SampleProvider Sample;
	int SampleSize;
	float[] samples;
	static int degrees = 0;

	public Gyrosensor(EV3GyroSensor gyro) {
		this.gyro = gyro;
		this.Sample = this.gyro.getAngleMode();
		this.SampleSize = Sample.sampleSize();
		this.samples = new float[SampleSize];
	}

	public int degrees() {
		degrees = 0;
		Sample.fetchSample(samples, 0);
		degrees = (int) samples[0];

		if (degrees > 180) {
			degrees -= 360;
		}
		if (degrees < -180) {
			degrees += 360;
		}
		if (Math.abs(degrees) == 360) {
			gyro.reset();
		}
		return degrees;
	}
	
	public static void main(String[] args) {
		EV3GyroSensor gyrosensor = new EV3GyroSensor(SensorPort.S3);
		Gyrosensor gyro = new Gyrosensor(gyrosensor);
		while (true) {
			int gyrowert = gyro.degrees();
			if (System.currentTimeMillis() % 100 == 0) {
				LCD.clear();
				LCD.drawString("Grad:" + gyrowert, 3, 4);
			}
		}
	}
}
