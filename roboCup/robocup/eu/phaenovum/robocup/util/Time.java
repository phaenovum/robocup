package eu.phaenovum.robocup.util;

/**
 * 
 * @author Felix Joeken
 */
public class Time {
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			//ignore
		}
	}
}
