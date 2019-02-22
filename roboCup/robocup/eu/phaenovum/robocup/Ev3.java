package eu.phaenovum.robocup;

import eu.phaenovum.robocup.util.EasySensor;
import eu.phaenovum.robocup.util.MindsensorsEV3SensorMUX;
import eu.phaenovum.robocup.util.MindsensorsEV3SensorMUX.MindsensorsEV3SensorMUXPort;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.RCXMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.HiTechnicIRSeekerV2;
import lejos.robotics.navigation.OmniPilot;

/**
 * 
 * @author Felix Joeken, Yael
 * @version 1.0
 */
@SuppressWarnings("deprecation")
public class Ev3 {
	
	public static final Port motorRPort = MotorPort.A;
	public static final Port motorLPort = MotorPort.B;
	public static final Port motorBPort = MotorPort.C;
	public static final Port motorDribbblerPort = MotorPort.D;
	
	public static final Port ultrasonicsPort = SensorPort.S2;
	public static final Port gyroPort = SensorPort.S3;
	public static final Port colorFrontPort = SensorPort.S4;
	public static final Port seekerPort = SensorPort.S1;
	
	private static EV3LargeRegulatedMotor motorR;
	private static EV3LargeRegulatedMotor motorL;
	private static EV3LargeRegulatedMotor motorB;
	private static RCXMotor motorDribbbler;
	
	private static EasySensor<HiTechnicIRSeekerV2> seeker;
	private static EasySensor<EV3GyroSensor> gyro;
	private static EasySensor<EV3ColorSensor> colorFront;
	private static MindsensorsEV3SensorMUX ultrasonics;
	
	public static MindsensorsEV3SensorMUXPort UltraF;
	public static MindsensorsEV3SensorMUXPort UltraR;
	public static MindsensorsEV3SensorMUXPort UltraB;
	
	public static OmniPilot omniPilot;
	public static Location location;
	
	/**
	 * init's the Sensors and Motors of the Robot. 
	 */
	public static void init() {
		motorR = new EV3LargeRegulatedMotor(motorRPort);
		motorL = new EV3LargeRegulatedMotor(motorLPort);
		motorB = new EV3LargeRegulatedMotor(motorBPort);
		motorDribbbler = new RCXMotor(motorDribbblerPort);
		
		seeker = new EasySensor<>(seekerPort, HiTechnicIRSeekerV2.class);
		gyro = new EasySensor<>(gyroPort, EV3GyroSensor.class);
		colorFront = new EasySensor<>(colorFrontPort, EV3ColorSensor.class);
		ultrasonics = new MindsensorsEV3SensorMUX(ultrasonicsPort);
		
		UltraF = ultrasonics.C1;
		UltraR = ultrasonics.C2;
		UltraB = ultrasonics.C3;
		
		omniPilot = new OmniPilot(114.5f, 70f, motorB, motorL, motorR, true, false, null);
		location = new Location();
		
		LCD.drawString("init complete", 0, 0);
	}
	
	public static RCXMotor getMotorDribbbler() {
		return motorDribbbler;
	}
	
	public static OmniPilot getOmniPilot() {
		return omniPilot;
	}
	
	public static EasySensor<HiTechnicIRSeekerV2> getSeeker() {
		return seeker;
	}
	
	public static EasySensor<EV3GyroSensor> getGyro() {
		return gyro;
	}
	
	public static EasySensor<EV3ColorSensor> getColorFront() {
		return colorFront;
	}
	
	public static MindsensorsEV3SensorMUX getUltrasonics() {
		return ultrasonics;
	}
}
