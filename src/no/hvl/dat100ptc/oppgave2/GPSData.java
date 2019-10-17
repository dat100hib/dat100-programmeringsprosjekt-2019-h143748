package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import java.lang.Integer.*;
import static java.lang.Double.*;
public class GPSData {
	
	/* GPS punktene som leses inn fra fil og konverteres skal representeres ved å bruke en referanasetabell dvs. 
	 * en tabell der elementene som er lagret er pekere til GPSPoint-objekt.
	 */

	private GPSPoint[] gpspoints; //skal brukes til å peke på referansetabellenen av GPS-punkter
	protected int antall = 0; // brukes til insettelse i tabellen, holde  kontroll på hvor neste punkt skal settes inn.
	
	//Konstruktør som lager ny tabell med lengde n, og setter antall = 0.
	public GPSData(int antall) {
		gpspoints = new GPSPoint [antall];
		antall = 0;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	//Setter inn nytt GPSPoint objekt i tabellen om det er plass, returnerer false om ikke.
	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = false;

		if(antall < gpspoints.length) {
			gpspoints[antall] = gpspoint;
			antall++;
			inserted = true;
		}
		return inserted;
	}

	//Får inn ny GPS-punkt via strengformat, konverterer, bruker insertGPS til å sette den inn i tabell,
	//returnerer true om det ble gjort. 
	public boolean insert(String time, String latitude, String longitude, String elevation) {
		return insertGPS(GPSDataConverter.convert(time, latitude, longitude, elevation));
		
		}

	public void print() {

		System.out.println("====== Konvertert GPS Data - START ======");

		for(GPSPoint point : gpspoints) {
			System.out.println(point.toString());
		}
		System.out.println("====== Konvertert GPS Data - SLUTT ======");

	}
}
