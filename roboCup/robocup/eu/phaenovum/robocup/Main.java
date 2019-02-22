package eu.phaenovum.robocup;

import eu.phaenovum.robocup.util.Time;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

public class Main extends Ev3{
	
	public static void main(String[] args) {
		init();
		while (Button.ENTER.isUp()) {
			LCD.drawString(Location.getLocation().toString(), 0, 0);
			Time.sleep(200);
		}
	}
	
}
