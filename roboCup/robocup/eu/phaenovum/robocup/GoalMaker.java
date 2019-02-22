package eu.phaenovum.robocup;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.navigation.OmniPilot;

/**
 * 
 * @author Yael
 *
 */
//@SuppressWarnings("deprecation")
@Deprecated
public class GoalMaker {
	
	EV3GyroSensor gyro1;
	EV3LargeRegulatedMotor motorR;
	EV3LargeRegulatedMotor motorL;
	EV3LargeRegulatedMotor motorB;	
	OmniPilot pilot;
	Gyrosensor gyro;
	Ultra ultraR;
	Ultra ultraL;
	
	public GoalMaker(EV3GyroSensor gyro, EV3LargeRegulatedMotor motorR, EV3LargeRegulatedMotor motorL, EV3LargeRegulatedMotor motorB, Ultra ultraR,
	Ultra ultraL) {
		this.gyro1 = gyro;
		this.motorB = motorB;
		this.motorR = motorR;
		this.motorL = motorL;
		this.pilot = new OmniPilot(110, 81.6f, motorB, motorL, motorR, true, true, null);
		this.gyro = new Gyrosensor(gyro1);
		this.ultraL = ultraL;
		this.ultraR = ultraR;
	}

	void turnToGoal() {
		int degrees = gyro.degrees();
		pilot.rotate(degrees);
	}
	
	void makeGoal() {
		int degrees = (int) ((ultraL.getDistance() - ultraR.getDistance()) * 100) % 90;
		pilot.moveStraight(100, degrees);
	}
	
	public static void main(String[] args) {
		
	}

}
