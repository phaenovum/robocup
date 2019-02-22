package eu.phaenovum.robocup;

import eu.phaenovum.robocup.util.EasySensor;
import lejos.hardware.sensor.HiTechnicIRSeekerV2;
import lejos.robotics.navigation.OmniPilot;

/**
 * 
 * @author Felix Joeken, Yael
 *
 */
@SuppressWarnings("deprecation")
public class FollowBall implements Runnable{
	
	public static Thread thread;
	private EasySensor<HiTechnicIRSeekerV2> seeker = Ev3.getSeeker();
	private OmniPilot omniPilot = Ev3.getOmniPilot();
	
	public static void startBallFollowing() {
		
		thread = new Thread(new FollowBall());
		thread.start();
		
	}
	
	@Override
	public void run() {
		while (Ev3.getColorFront().getValue() != 7) {

			omniPilot.moveStraight(300f, (int) (180 + seeker.getValue()));;
		}
	}
	
	public static Thread getThread() {
		return thread;
	}
}
