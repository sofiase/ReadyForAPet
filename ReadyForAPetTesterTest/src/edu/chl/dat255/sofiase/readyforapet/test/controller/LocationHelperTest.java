package edu.chl.dat255.sofiase.readyforapet.test.controller;

import edu.chl.dat255.sofiase.readyforapet.util.LocationHelper;
import junit.framework.TestCase;
import android.util.Log;

import com.google.android.maps.GeoPoint;

public class LocationHelperTest extends TestCase {

	private double distance1 = 0;
	private double distance2 = 0;
	private static final String LOG_TAG = "Differens mellan expected och beräknad";

	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	/**
	 * Method for testing the distance that is counted in class Geodetics, which is imported as a library
	 * in the android project. The test compares the distance from Geodetics with 
	 * distance and coordinates from two different maps, Google Maps and Eniro. 
	 * More about which distances that is used can be found in documentation for testing.
	 * 
	 */
	public void testDistance () {
		GeoPoint geoA = new GeoPoint(LocationHelper.degreesToMicrodegrees(57.689442),LocationHelper.degreesToMicrodegrees(11.980654));
		GeoPoint geoB = new GeoPoint(LocationHelper.degreesToMicrodegrees(57.687596),LocationHelper.degreesToMicrodegrees(11.982285));
		distance1 = distance1 + nu.placebo.whatsup.util.Geodetics.distance(geoA, geoB);
		Double expectedResult1 = 230.0;
		Double diff1 = distance1 - expectedResult1;
		Log.d(LOG_TAG,Double.toString(diff1));
		assertTrue(diff1<5);
		GeoPoint geoC = new GeoPoint(LocationHelper.degreesToMicrodegrees(57.69600),LocationHelper.degreesToMicrodegrees(11.93959));
		GeoPoint geoD = new GeoPoint(LocationHelper.degreesToMicrodegrees(57.69843),LocationHelper.degreesToMicrodegrees(11.95685));
		distance2 = distance2 + nu.placebo.whatsup.util.Geodetics.distance(geoC, geoD);
		Double expectedResult2 = 1063.0;
		Double diff2 = distance2 - expectedResult2;
		Log.d(LOG_TAG,Double.toString(diff2));
		assertTrue(diff2<5);
	}
	
	
}
