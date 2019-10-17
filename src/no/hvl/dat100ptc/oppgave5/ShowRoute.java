package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);

//		playRoute(MARGIN + MAPYSIZE);
//		What is this?	
        showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 
		//System.out.println("xstep: " + xstep);
		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
		
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		double ystep = MAPYSIZE / (Math.abs(maxlat - minlat)); 

		return ystep;
		
	}

	public void showRouteMap(int ybase) {
		setSpeed(7);
		double xsteps = 0;
		double ysteps = 0;

		setColor(0, 225, 0);
		for(int i = 0; i < gpspoints.length; i++) {
			xsteps = ((gpspoints[i].getLongitude()-GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints)))*xstep() );
			ysteps  = (GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints))-gpspoints[i].getLatitude())*ystep();
			System.out.println("y : " + ysteps);

			System.out.println("x : " + xsteps);
			fillCircle((int)xsteps, (int)ysteps , 4);
		}
		setColor(0, 0, 225);
		fillCircle((int)xsteps, (int)ysteps, 4);
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		int WEIGHT = 80;
		
		drawString("Total time	      :" + gpscomputer.totalTime(), TEXTDISTANCE, TEXTDISTANCE);
		drawString("Total distance  :" + gpscomputer.totalDistance() + " km", TEXTDISTANCE, TEXTDISTANCE*2);
		drawString("Total elevation :" + gpscomputer.totalElevation() + " m", TEXTDISTANCE, TEXTDISTANCE*3);
		drawString("Max speed	       :" + gpscomputer.maxSpeed()+ " km/t", TEXTDISTANCE, TEXTDISTANCE*4);
		drawString("Average speed   :" + gpscomputer.averageSpeed()+" km/t", TEXTDISTANCE, TEXTDISTANCE*5);
		drawString("Energy		          :" + gpscomputer.totalKcal(WEIGHT) + " kcal", TEXTDISTANCE, TEXTDISTANCE*6);
	}

	public void playRoute(int ybase) {

		// TODO - START
		
		throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
	}

}
