package no.hvl.dat100ptc.oppgave5;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import javax.swing.JOptionPane;

public class ShowProfile extends EasyGraphics {

	private static int MARGIN = 50;		// margin on the sides 

	//FIXME: use highest point and scale accordingly
	private static int MAXBARHEIGHT = 500; // assume no height above 500 meters

	
	private GPSPoint[] gpspoints;

	public ShowProfile() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		GPSComputer gpscomputer =  new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length; // number of data points

		makeWindow("Height profile", 2 * MARGIN + 3 * N, 2 * MARGIN + MAXBARHEIGHT);

		// top margin + height of drawing area
		showHeightProfile(MARGIN + MAXBARHEIGHT); 
	}

	public void showHeightProfile(int ybase) {

		// ybase indicates the position on the y-axis where the columns should start
		int maxPoint = (int)GPSComputer.highestPoint(gpspoints);
		if(maxPoint>500) {
			maxPoint = 500;
		}
		int window = maxPoint+MARGIN*2;
		makeWindow("Grafikk", (MARGIN*2)+gpspoints.length, window);
		setColor(0, 0, 225);
		
		for(int i = 0; i < gpspoints.length; i++) {
			
			int startX = MARGIN;
			
			int startY = window - MARGIN;
			int endY = startY - (int)gpspoints[i].getElevation();
			if(endY > startY) {
				endY = startY;
			}else if(endY < window - MAXBARHEIGHT) {
				endY = MARGIN;
			}

			drawLine(startX+i, startY, startX+i , endY);
		}

	}
	
}