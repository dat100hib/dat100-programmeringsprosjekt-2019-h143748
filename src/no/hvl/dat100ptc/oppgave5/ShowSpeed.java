package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowSpeed extends EasyGraphics {

	private static int MARGIN = 50;
	private static int BARHEIGHT = 200; // assume no speed above 200 km/t
	private static int WINDOW; // Høyden på boksen

	private GPSComputer gpscomputer;
	private GPSPoint[] gpspoints;

	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	// read in the files and draw into using EasyGraphics
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length-1; // number of data points
		
		int maxSpeed = (int)gpscomputer.maxSpeed();
		if(maxSpeed > BARHEIGHT) {
			maxSpeed = BARHEIGHT;
		}
		WINDOW = maxSpeed+MARGIN*2;

		makeWindow("Speed profile", 2*MARGIN + N, WINDOW);

		showSpeedProfile(MARGIN + BARHEIGHT,N);
	}

	public void showSpeedProfile(int ybase, int N) {
		

		System.out.println("Angi tidsskalering i tegnevinduet ...");
		
		int timescaling = Integer.parseInt(getText("Tidsskalering"));
		setColor(0, 0, 225);
		for(int i = 0; i < N; i++) {
			
			setSpeed(timescaling);

			int startX = MARGIN;

			int startY = WINDOW - MARGIN;
			int endY = startY - (int)GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
			
			if(endY > startY) {
				endY = startY;
			}
			else if(endY < WINDOW-BARHEIGHT) {
				endY = MARGIN;
			}

			drawLine(startX+i, startY, startX+i , endY);
			
			
		}
		int average = WINDOW- MARGIN-(int)gpscomputer.averageSpeed();
		setColor(0, 225, 0);
		drawLine(MARGIN, average, N+MARGIN, average);

	}
}
