package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {

	private GPSPoint[] gpspoints;

	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		for(int i = 1; i < gpspoints.length; i++) {
			distance += GPSUtils.distance(gpspoints[i-1], gpspoints[i]);
		}

		return distance;

	}

	// beregn totale høydemeter (i meter)

	public double totalElevation() {

		double elevation = 0;

		for(int i = 1; i < gpspoints.length; i++) {
			if(gpspoints[i].getElevation() - gpspoints[i-1].getElevation() > 0) {
				elevation += (gpspoints[i].getElevation() - gpspoints[i-1].getElevation());
			}
		}
		return elevation;

	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {

		int tid = gpspoints[gpspoints.length-1].getTime() - gpspoints[0].getTime();
		return tid;

	}

	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {

		double [] speeds = new double[gpspoints.length-1];
		for(int i = 0; i < speeds.length; i++ ) {
			speeds[i] = GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
		}
		return speeds;

	}

	public double maxSpeed() {

		double maxspeed = 0;

		for(int i = 1; i < gpspoints.length; i++) {
			if(maxspeed < GPSUtils.speed(gpspoints[i-1], gpspoints[i])) {
				maxspeed = GPSUtils.speed(gpspoints[i-1], gpspoints[i]);
			}
		}
		return maxspeed;
	}

	public double averageSpeed() {

		return (totalDistance()/totalTime())*3.6;

	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling,
	 * general 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0
	 * bicycling, 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9
	 * mph, racing or leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph,
	 * racing/not drafting or >19 mph drafting, very fast, racing general 12.0
	 * bicycling, >20 mph, racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;

		if(speedmph <10) {
			met = 4.0;
		}else if(speedmph < 12) {
			met = 6.0;
		}else if(speedmph < 14) {
			met = 8.0;
		}else if(speedmph < 16) {
			met = 10.0;
		}else if(speedmph < 20) {
			met = 12.0;
		}else {
			met = 16.0;
		}

		double h = secs/3600.0;
		kcal = met*weight*h;
		return kcal;

	}

	public double totalKcal(double weight) {

		double totalkcal = kcal(weight, totalTime(), averageSpeed());

		return totalkcal;
	}

	private static double WEIGHT = 80.0;

	public void displayStatistics() {

		System.out.println("==============================================");

		System.out.println("Total time	   :" + totalTime());
		System.out.println("Total distance :" + totalDistance() + " km");
		System.out.println("Total elevation:" + totalElevation() + " m");
		System.out.println("Max speed	   :" + maxSpeed()+ " km/t");
		System.out.println("Average speed  :" + averageSpeed()+" km/t");
		System.out.println("Energy		   :" + totalKcal(WEIGHT) + " kcal");

	}
	
	public static double highestPoint(GPSPoint [] points) {
		double elevation = 0;
		for(GPSPoint point : points) {
			if(elevation < point.getElevation())
				elevation = point.getElevation();
		}
		return elevation;
	}

}
