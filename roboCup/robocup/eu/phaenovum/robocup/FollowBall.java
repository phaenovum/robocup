package eu.phaenovum.robocup;

/**
 * 
 * @author Felix Joeken, Yael
 *
 */
@Deprecated
public class FollowBall implements Runnable{
	
	public static Thread thread;
	
	public static void startBallFollowing() {
		
		thread = new Thread(new FollowBall());
		thread.start();
		
	}
	
	@Override
	public void run() {
		//while (Ev3.getColorFront().getValue() != 7) {

			//omniPilot.moveStraight(300f, (int) (180 + seeker.getValue()));;
		//}
	}
	
	public static Thread getThread() {
		return thread;
	}
}
