package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import static java.lang.Integer.*;
import static java.lang.Double.*;

public class GPSDataConverter {

	// konverter tidsinformasjon i gps data punkt til antall sekunder fra midnatt
	// dvs. ignorer information om dato og omregn tidspunkt til sekunder
	// Eksempel - tidsinformasjon (som String): 2017-08-13T08:52:26.000Z
    // skal omregnes til sekunder (som int): 8 * 60 * 60 + 52 * 60 + 26 
	
	private static int TIME_STARTINDEX = 11; // startindex for tidspunkt i timestr

	
	//omgjør stringen timestr til int, og så omgjør timer/minutter/sekunder til totale sekunder. 
	public static int toSeconds(String timestr) {
		
		int secs;
		int hr, min, sec;
		
		hr = parseInt(timestr.substring(11, 13));
		min = parseInt(timestr.substring(14, 16));
		sec = parseInt(timestr.substring(17, 19));
		
		secs = (hr*3600) + (min*60) + sec;
		
		return secs;
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		GPSPoint gpspoint;
		
		int time = toSeconds(timeStr);
		
		gpspoint = new GPSPoint(time, parseDouble(latitudeStr), 
								parseDouble(longitudeStr), parseDouble(elevationStr));
		
		return gpspoint;

	    
	}
	
}
