package eu.phaenovum.robocup;

import lejos.hardware.sensor.HiTechnicIRSeekerV2;

/**
 * 
 * @author Yael
 * @deprecated better use EasySensor
 */
@Deprecated
public class Seeker {
	
	HiTechnicIRSeekerV2 seeker;
	float sample[];
	
	Seeker (HiTechnicIRSeekerV2 seeker){
		this.seeker = seeker;
		this.sample = new float[seeker.getModulatedMode().sampleSize()];
	}
	
	public float getAngle() {
		seeker.fetchSample(sample, 0);
		return sample[0];
	}
}