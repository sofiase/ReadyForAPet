package edu.chl.dat255.sofiase.readyforapet.test.controller;

import Controller.LocationHelper;
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
