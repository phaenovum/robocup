package eu.phaenovum.robocup;

import eu.phaenovum.robocup.util.Time;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;

 @SuppressWarnings("deprecation")
public class Main extends Ev3{
	
	public static void main(String[] args) {
		init();
		while (Button.ENTER.isUp()) {}
		Time.sleep(1000);
		while (Button.ENTER.isUp()) {
			LCD.drawString("" + Location.getGoalDirection(), 0, 0);
			Time.sleep(50);
			LCD.clear();
		}
		Sound.beep();
	}
	
}
