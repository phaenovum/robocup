package eu.phaenovum.robocup;

import eu.phaenovum.robocup.util.Point;

/**
 * 
 * @author Felix Joeken
 *
 */
public class Location{
	
	private static Point location = new Point();
	static float[] ultraB;
	static float[] ultraR;
	
	static final float breiteFeld = 1.22f;
	static final float hoeheFeld = 1.83f;
	
	/**
	 * 
	 * @return return's the roboter's Location
	 */
	public static Point getLocation() {
		
		ultraB = new float[Ev3.getUltrasonics().sampleSize()];
		Ev3.getUltrasonics().C1.getDistanceMode().fetchSample(ultraB, 0);
		location.y = Math.cos(Math.toDegrees(Ev3.getGyro().getValue())) * ultraB[0];
		
		ultraR = new float[Ev3.getUltrasonics().sampleSize()];
		Ev3.getUltrasonics().C2.getDistanceMode().fetchSample(ultraR, 0);
		location.x = Math.cos(Math.toDegrees(Ev3.getGyro().getValue())) * ultraR[0]; 
		
		return location;
	}
	
	@Override
	public String toString() {
		return (new double[] {location.x, location.y}.toString());
	}
	
	public static float getX_ultra() {
		return ultraR[0];
	}
	
	public static float getY_ultra() {
		return ultraB[0];
	}
	
	public static double getGoalDirection() {
		float x = 0; 
		float y = 0;
		for (int i = 0; i < 10; i++) {
			Point p = Location.getLocation();
			x += p.x;
			y += p.y;
		}
		x /= 10;
		y /= 10;
		
		float x_1 = x -0.5f * breiteFeld;
		float y_1 = y - hoeheFeld;
		
		return Math.toDegrees(Math.asin(y_1/x_1));
	}
}