package eu.phaenovum.robocup;

import eu.phaenovum.robocup.util.MindsensorsEV3SensorMUX;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.RCXMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;

/**
 * 
 * @author Yael
 * @deprecated better use EasySensor
 */
@Deprecated
public class Ultra {
	
	MindsensorsEV3SensorMUX ultra;
	float sample[];
	
	Ultra (MindsensorsEV3SensorMUX ultra){
		this.ultra = ultra;
		this.sample = new float[ultra.C1.getDistanceMode().sampleSize()];
	}
	
	public float getDistance() {
		ultra.C1.getDistanceMode().fetchSample(sample, 0);
		return sample[0];
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		MindsensorsEV3SensorMUX ultra1 = new MindsensorsEV3SensorMUX(SensorPort.S2);
		Ultra ultraD = new Ultra(ultra1);
		RCXMotor motorRCX = new RCXMotor(MotorPort.D);
		motorRCX.setPower(100);
		motorRCX.backward();
		while (true) {
			if(System.currentTimeMillis() % 300 == 0) {
			LCD.drawString("distanz:" + ultraD.getDistance(), 2, 3);
			}
		}
	}
}