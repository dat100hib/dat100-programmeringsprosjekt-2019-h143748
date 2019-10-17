package no.hvl.dat100ptc.oppgave1;

import no.hvl.dat100ptc.TODO;

public class GPSPoint {
	
	//Objektvariablene
	
	private int time; //tid i sekunder
	private double latitude;  //breddegrad
	private double longitude; //lengdegrad
	private double elevation; //høyde
	
	
	//Konstruktør som tar inn alle objektvariablene.
	
	public GPSPoint(int time, double latitude, double longitude, double elevation) {

		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
	}

	//getters og setters:
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public double getLatitude() {
		return latitude;		
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
		
	}

	public double getLongitude() {
		return longitude;		
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
		
	}

	public double getElevation() {
		return elevation;		
	}

	public void setElevation(double elevation) {
		this.elevation = elevation;
		
	}
	
	public String toString() {
		return time + " (" + latitude + "," + longitude + ") " + elevation + "\n";
	}
}
