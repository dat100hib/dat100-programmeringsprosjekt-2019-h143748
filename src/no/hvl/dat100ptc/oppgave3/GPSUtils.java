package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	//Finner og returnerer minste double i tabellen. Må være minst etet element i tabellen.
	public static double findMin(double[] da) {

		double min = da[0];

		for(double d : da) {
			if(min > d) {
				min = d;
			}
		}
		return min;

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double [] da = new double[gpspoints.length];
		for(int i = 0; i < da.length; i++) {
		da[i] = gpspoints[i].getLatitude();
		}
		
		return da;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double [] da = new double[gpspoints.length];
		for(int i = 0; i < da.length; i++) {
		da[i] = gpspoints[i].getLongitude();
		}
		
		return da;

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		latitude1 = toRadians(gpspoint1.getLatitude());
		longitude1 = toRadians(gpspoint1.getLongitude());
		
		latitude2 = toRadians(gpspoint2.getLatitude());
		longitude2 = toRadians(gpspoint2.getLongitude());
		
		double latChange = latitude2-latitude1;
		double longChange = longitude2-longitude1;
		double a = pow(sin(latChange/2), 2) + cos(latitude1) * cos(latitude2) * pow(sin(longChange/2),2);
		double c  = 2*atan2(sqrt(a), sqrt(1-a));
		d = R * c;
		
		return d;

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		//returnerer km/h.
		int secs;
		double speed;
		
		secs = gpspoint2.getTime()-gpspoint1.getTime();
		double h = secs/3600.0;
		
		double km = distance(gpspoint1, gpspoint2)/1000;

		
		speed = km/h;
		return speed;
	}

	public static String formatTime(int secs) {

		String timestr;
		
		int h = secs / 3600;
		int m = (secs/60)-(h*60);
		int s = secs%60;
		
		timestr = String.format("  %02d:%02d:%02d", h, m, s);
		return timestr;


	}

	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {
		
		return String.format("%10.2f", d);
		
	}
}
