package eu.phaenovum.robocup;

import eu.phaenovum.robocup.util.Point;

/**
 * 
 * @author Felix Joeken
 *
 */
public class Location {
	
	private static Point location = new Point();
	
	/**
	 * 
	 * @return return's the roboter's Location
	 */
	public static Point getLocation() {
		
		float[] ultraF = new float[Ev3.getUltrasonics().sampleSize()];
		Ev3.getUltrasonics().C1.getDistanceMode().fetchSample(ultraF, 0);
		location.y = Math.sin(Math.toDegrees(Ev3.getGyro().getValue() * ultraF[0]));
		
		float[] ultraR = new float[Ev3.getUltrasonics().sampleSize()];
		Ev3.getUltrasonics().C2.getDistanceMode().fetchSample(ultraR, 0);
		location.x = Math.cos(Math.toDegrees(Ev3.getGyro().getValue() * ultraR[0])); 
		
		return location;
	}
	
	@Override
	public String toString() {
		return (new double[] {location.x, location.y}.toString());
	}
}