package com.bryan.maps;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	// latLngs for Events
	static final LatLng GLASGOW = new LatLng(55.8580, -4.2590);
	static final LatLng SECC = new LatLng(55.8607, -4.2871);
	static final LatLng SHOOTING = new LatLng(56.499, -2.7543);
	static final LatLng PARKHEAD = new LatLng(55.8497, -4.2055);
	static final LatLng CATHKINBRAES = new LatLng(55.79434, -4.2193);
	static final LatLng VELODROME = new LatLng(55.847, -4.2076);
	static final LatLng HOCKEY = new LatLng(55.8447, -4.236);
	static final LatLng HAMPDEN = new LatLng(55.8255, -4.2520);
	static final LatLng IBROX = new LatLng(55.853, -4.309);
	static final LatLng BOWLS = new LatLng(55.867, -4.2871);
	static final LatLng SCOTSTOUN = new LatLng(55.8813, -4.3405);
	static final LatLng TOLLCROSS = new LatLng(55.845, -4.177);
	static final LatLng STRATHCLYDE = new LatLng(55.7971971, -4.0342997);
	static final LatLng EDINBURGH = new LatLng(55.939, -3.172);

	// The map
	private GoogleMap map;
	// Markers for Home and User locations
	private Marker userLoc, homeLoc;

	// Arrays for Events and Previous Host Cities
	private Marker[] events = new Marker[13];
	private Marker[] hosts = new Marker[10];

	// LatLngs for previous Host Cities
	static final LatLng DELHI = new LatLng(28.61, 77.23);
	static final LatLng MELBOURNE = new LatLng(-37.813611, 144.963056);
	static final LatLng MANCHESTER = new LatLng(53.466667, -2.233333);
	static final LatLng KUALALUMPUR = new LatLng(3.1475, 101.693333);
	static final LatLng VICTORIA = new LatLng(48.428611, -123.365556);
	static final LatLng AUCKLAND = new LatLng(-36.840417, 174.739869);
	static final LatLng BRISBANE = new LatLng(-27.467917, 153.027778);
	static final LatLng EDMONTON = new LatLng(53.533333, -113.5);
	static final LatLng CHRISTCHURCH = new LatLng(-43.53, 172.620278);

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Set the map
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		//Populate the array with the Markers for each event
		events[0] = createMarker(GLASGOW, "Glasgow", "City of Glasgow", 0);
		events[1] = createMarker(
				SECC,
				"SECC Precinct",
				"Sports: Boxing, Gymnastics, Judo, Netball, Wrestling & Weightlifting",
				R.drawable.secc);
		events[2] = createMarker(SHOOTING, "Barry Buddon Shooting Centre",
				"Sport: Shooting", R.drawable.shooting);
		events[3] = createMarker(PARKHEAD, "Celtic Park", "Opening Ceremony",
				R.drawable.parkhead);
		events[4] = createMarker(CATHKINBRAES,
				"Cathkin Braes Mountain Bike Trail", "Sport: Mounting Biking",
				R.drawable.cathkinbraes);
		events[5] = createMarker(VELODROME,
				"The Emirates Arena & Sir Chris Hoy Velodrome",
				"Sports: Badminton & Cycling", R.drawable.emirates);
		events[6] = createMarker(HOCKEY, "Glasgow National Hockey Centre",
				"Sport: Hockey", R.drawable.hockey);
		events[7] = createMarker(HAMPDEN, "Hampden Park", "Sport: Athletics",
				R.drawable.hampden);
		events[8] = createMarker(IBROX, "Ibrox Stadium", "Sport: Rugby Sevens",
				R.drawable.ibrox);
		events[9] = createMarker(BOWLS, "Kelvingrove Lawn Bowls Centre",
				"Sport: Lawn Bowls", R.drawable.bowls);
		events[10] = createMarker(SCOTSTOUN, "Scotstoun Sports Campus",
				"Sports: Squash & Table Tennis", R.drawable.scotstoun);
		events[11] = createMarker(TOLLCROSS,
				"Tollcross International Swimming Centre", "Sport: Swimming",
				R.drawable.tollcross);
		events[12] = createMarker(STRATHCLYDE, "Strathclyde Country Park",
				"Sport: Triathlon", R.drawable.strathclyde);

		hosts[0] = createMarker(DELHI, "New Delhi, India",
				"2010: Scotland won 26 Medals here", 0);
		hosts[1] = createMarker(MELBOURNE, "Melbourne, Australia",
				"2006: Scotland won 29 Medals here", 0);
		hosts[2] = createMarker(MANCHESTER, "Manchester, United Kingdom",
				"2002: Scotland won 22 Medals here", 0);
		hosts[3] = createMarker(KUALALUMPUR, "Kuala Lumpur, Malaysia",
				"1998: Scotland won 12 Medals here", 0);
		hosts[4] = createMarker(VICTORIA, "Victoria, Canada",
				"1994: Scotland won 20 Medals here", 0);
		hosts[5] = createMarker(AUCKLAND, "Auckland, New Zealand",
				"1990: Scotland won 22 Medals here", 0);
		hosts[6] = createMarker(EDINBURGH, "Edinburgh, United Kingdom",
				"1986: Scotland won 33 Medals here", 0);
		hosts[7] = createMarker(BRISBANE, "Brisbane, Australia",
				"1982: Scotland won 26 Medals here", 0);
		hosts[8] = createMarker(EDMONTON, "Edmonton, Canada",
				"1978: Scotland won 14 Medals here", 0);
		hosts[9] = createMarker(CHRISTCHURCH, "Christchurch, New Zealand",
				"1974: Scotland won 19 Medals here", 0);

		//Set the listener for long taps
		map.setOnMapLongClickListener(new OnMapLongClickListener() {
			@Override
			public void onMapLongClick(LatLng latLng) {
				//Get the position of the tap
				final LatLng l = new LatLng(latLng.latitude, latLng.longitude);

				//Start building an alert dialog
				final AlertDialog.Builder b = new AlertDialog.Builder(
						MainActivity.this);
				//Set the title and message for the dialog
				b.setMessage("Set a custom location which can later be used to determine distance to destination. Use 'Home' to create a Home City Marker");
				b.setTitle("Enter a title");
				//Create an EditText on the dialog
				final EditText uTitle = new EditText(MainActivity.this);
				//Set the view
				b.setView(uTitle);
				//Create the Yes button
				b.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								//Get the users title text
								String uTT = uTitle.getText().toString();
								//If the user did not specify a title
								if (uTT.length() == 0) {
									//Set it to the default
									uTT = "User Placed Marker";
								}
								//Check if this is the users Home marker
								if (uTT.equals("Home") || uTT.equals("home")) {
									//If one has been set already, remove it
									if (homeLoc != null) {
										homeLoc.remove();
									}
									//Now create the Home marker, including the Home icon
									homeLoc = createMarker(l, uTT,
											"My home City", R.drawable.home);
									//Toast message so the user knows it was successful
									Toast.makeText(MainActivity.this,
											"Setting home city...",
											Toast.LENGTH_LONG).show();
								} 
								else {
									//If not a home marker, create a normal marker
									createMarker(l, uTT, "User placed Marker",
											0);
								}
							}
						});
				//Create the Cancel button
				b.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								//Does nothing, just removes the dialog
							}
						});
				//Show the dialog box
				b.show();
			}
		});

		//Set the listener for the Info Windows
		map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
			@Override
			public void onInfoWindowClick(Marker marker) {
				//Get the marker that the info window is for
				final Marker mark = marker;
				//Make sure it hasn't already been set to visited
				if (mark.getSnippet().contains("(Visited)") != true) {
					//Create the alert dialog
					final AlertDialog.Builder bu = new AlertDialog.Builder(
							MainActivity.this);
					//Set the title and message
					bu.setTitle("Options");
					bu.setMessage("Click OK to set this event as visited");
					//Create the Ok button
					bu.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									//Check for the specific Event and change to the Visited icon
									//Also add (Visited) to the snippet
									if (mark.getTitle().equals("SECC Precinct") == true) {
										BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
												.fromResource(R.drawable.seccv);
										mark.setIcon(bitmapDescriptor);
										mark.setSnippet(mark.getSnippet()
												+ " (Visited)");
									} else if (mark.getTitle().equals(
											"Barry Buddon Shooting Centre") == true) {
										BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
												.fromResource(R.drawable.shootingv);
										mark.setIcon(bitmapDescriptor);
										mark.setSnippet(mark.getSnippet()
												+ " (Visited)");
									} else if (mark.getTitle().equals(
											"Celtic Park") == true) {
										BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
												.fromResource(R.drawable.parkheadv);
										mark.setIcon(bitmapDescriptor);
										mark.setSnippet(mark.getSnippet()
												+ " (Visited)");
									} else if (mark
											.getTitle()
											.equals("Cathkin Braes Mountain Bike Trail") == true) {
										BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
												.fromResource(R.drawable.cathkinbraesv);
										mark.setIcon(bitmapDescriptor);
										mark.setSnippet(mark.getSnippet()
												+ " (Visited)");
									} else if (mark
											.getTitle()
											.equals("The Emirates Arena & Sir Chris Hoy Velodrome") == true) {
										BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
												.fromResource(R.drawable.emiratesv);
										mark.setIcon(bitmapDescriptor);
										mark.setSnippet(mark.getSnippet()
												+ " (Visited)");
									} else if (mark.getTitle().equals(
											"Glasgow National Hockey Centre") == true) {
										BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
												.fromResource(R.drawable.hockeyv);
										mark.setIcon(bitmapDescriptor);
										mark.setSnippet(mark.getSnippet()
												+ " (Visited)");
									} else if (mark.getTitle().equals(
											"Hampden Park") == true) {
										BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
												.fromResource(R.drawable.hampdenv);
										mark.setIcon(bitmapDescriptor);
										mark.setSnippet(mark.getSnippet()
												+ " (Visited)");
									} else if (mark.getTitle().equals(
											"Ibrox Stadium") == true) {
										BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
												.fromResource(R.drawable.ibroxv);
										mark.setIcon(bitmapDescriptor);
										mark.setSnippet(mark.getSnippet()
												+ " (Visited)");
									} else if (mark.getTitle().equals(
											"Kelvingrove Lawn Bowls Centre") == true) {
										BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
												.fromResource(R.drawable.bowlsv);
										mark.setIcon(bitmapDescriptor);
										mark.setSnippet(mark.getSnippet()
												+ " (Visited)");
									} else if (mark.getTitle().equals(
											"Scotstoun Sports Campus") == true) {
										BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
												.fromResource(R.drawable.scotstounv);
										mark.setIcon(bitmapDescriptor);
										mark.setSnippet(mark.getSnippet()
												+ " (Visited)");
									} else if (mark
											.getTitle()
											.equals("Tollcross International Swimming Centre") == true) {
										BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
												.fromResource(R.drawable.tollcrossv);
										mark.setIcon(bitmapDescriptor);
										mark.setSnippet(mark.getSnippet()
												+ " (Visited)");
									} else if (mark.getTitle().equals(
											"Strathclyde Country Park") == true) {
										BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
												.fromResource(R.drawable.strathclydev);
										mark.setIcon(bitmapDescriptor);
										mark.setSnippet(mark.getSnippet()
												+ " (Visited)");
									} else {
										//If it isn't an event, just change the description
										mark.setSnippet(mark.getSnippet()
												+ " (Visited)");
									}
								}
							});
					//Create the Cancel button
					bu.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									//Do nothing, just removes the dialog
								}
							});
					//Show the dialog box
					bu.show();
				}
			}
		});
		// Move the camera instantly to Glasgow with a zoom of 5
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(GLASGOW, 5));
		// Zoom in, animating the camera.
		map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

	}

	protected void animMoveCamera(LatLng l) {
		//Method for animating the camera
		
		// Move the camera instantly to Glasgow with a zoom of 5.
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(l, 5));
		// Zoom in, animating the camera.
		map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
	}

	protected Marker createMarker(LatLng l, String a, String b, int i) {
		//Method for creating the Markers, first create a new marker with the position and title
		Marker m = map.addMarker(new MarkerOptions().position(l).title(a));
		//Check if a description is also required
		if (b != null) {
			//If so, set it
			m.setSnippet(b);
		}
		//Now check if a custom icon is specified
		if (i != 0) {
			//If so, set it
			m.setIcon(BitmapDescriptorFactory.fromResource(i));
		}
		//Return the created marker
		return m;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//Inflate the options menu
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		//Respond to menu item selection
		
		//Create an int for the mapType for later
		int mapType;
		//Check which option has been selected
		switch (item.getItemId()) {
		case R.id.action_return:
			//If return to Glasgow, animate camera and go there
			animMoveCamera(GLASGOW);
			return true;
		case R.id.action_getGPS:
			//If getGPS, call the method to do so
			getGPS();
			return true;
		case R.id.action_distance:
			//If Distance to Glasgow, calculate the distance between Home and Glasgow
			calcHomeDist(events[0]);
			return true;
		case R.id.subCountry1:
			//Next 10 options will move the camera to the specified city
			animMoveCamera(DELHI);
			return true;
		case R.id.subCountry2:
			animMoveCamera(MELBOURNE);
			return true;
		case R.id.subCountry3:
			animMoveCamera(MANCHESTER);
			return true;
		case R.id.subCountry4:
			animMoveCamera(KUALALUMPUR);
			return true;
		case R.id.subCountry5:
			animMoveCamera(VICTORIA);
			return true;
		case R.id.subCountry6:
			animMoveCamera(AUCKLAND);
			return true;
		case R.id.subCountry7:
			animMoveCamera(EDINBURGH);
			return true;
		case R.id.subCountry8:
			animMoveCamera(BRISBANE);
			return true;
		case R.id.subCountry9:
			animMoveCamera(EDMONTON);
			return true;
		case R.id.subCountry10:
			animMoveCamera(CHRISTCHURCH);
			return true;
		case R.id.subEvent1:
			//Next 12 options will calculate the distance between the GPS coords and the specific Event
			calcDistMarker(events[1]);
			return true;
		case R.id.subEvent2:
			calcDistMarker(events[2]);
			return true;
		case R.id.subEvent3:
			calcDistMarker(events[3]);
			return true;
		case R.id.subEvent4:
			calcDistMarker(events[4]);
			return true;
		case R.id.subEvent5:
			calcDistMarker(events[5]);
			return true;
		case R.id.subEvent6:
			calcDistMarker(events[6]);
			return true;
		case R.id.subEvent7:
			calcDistMarker(events[7]);
			return true;
		case R.id.subEvent8:
			calcDistMarker(events[8]);
			return true;
		case R.id.subEvent9:
			calcDistMarker(events[9]);
			return true;
		case R.id.subEvent10:
			calcDistMarker(events[10]);
			return true;
		case R.id.subEvent11:
			calcDistMarker(events[11]);
			return true;
		case R.id.subEvent12:
			calcDistMarker(events[12]);
			return true;
		case R.id.subViewNorm:
			//Sets the map type to Normal
			mapType = GoogleMap.MAP_TYPE_NORMAL;
			map.setMapType(mapType);
			return true;
		case R.id.subViewTerr:
			//Sets the map type to Terrain
			mapType = GoogleMap.MAP_TYPE_TERRAIN;
			map.setMapType(mapType);
			return true;
		case R.id.subViewHyb:
			//Sets the map type to Hybrid
			mapType = GoogleMap.MAP_TYPE_HYBRID;
			map.setMapType(mapType);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void getGPS() {
		//Method for getting the GPS coordinates of the user
		
		//Create a new Location
		Location location;
		//Create a new LocationManager to get the coords
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		//New Criteria for the LocationManager
		Criteria criteria = new Criteria();
		//String to contain the provider to be used, attempt to use GPS first
		String provider = locationManager.getBestProvider(criteria, false);
		//Set the location to the GPS coords
		location = locationManager.getLastKnownLocation(provider);
		//If GPS did not work
		if (location == null) {
			//Use the Network Provided value instead
			location = locationManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}
		//Now, check that worked
		if (location != null) {
			//If either one worked, we now get the location LatLng
			LatLng l = new LatLng(location.getLatitude(),
					location.getLongitude());
			//If a Marker already exists for the user, remove it
			if (userLoc != null) {
				userLoc.remove();
			}
			//Create a marker at the users location, with the GPS Icon
			userLoc = createMarker(l, "Your current location", "",
					R.drawable.gps);
			//Toast message to let user know it worked
			Toast.makeText(MainActivity.this, "You are here...",
					Toast.LENGTH_SHORT);
			//Move camera to the users location
			animMoveCamera(l);
		} else {
			//If Network values didn't work either, show an error message
			Toast toast = Toast.makeText(this, "Could not detect GPS Location",
					Toast.LENGTH_LONG);
			toast.show();
		}
	}

	public void calcDistMarker(Marker m) {
		//Method for calculating the distance from an event
		//Could also be used for any other Marker
		
		//First, check the user has set their GPS location
		if (userLoc == null) {
			//If not, ask them to do so
			Toast toast = Toast
					.makeText(
							this,
							"Please use the 'Get GPS Location' Option to determine distance",
							Toast.LENGTH_LONG);
			toast.show();
		} else {
			//If so, use the forumala to calculate the distance and convert to km
			double d2r = (Math.PI / 180);
			double u = (m.getPosition().latitude - userLoc.getPosition().latitude)
					* d2r;
			double v = (m.getPosition().longitude - userLoc.getPosition().longitude)
					* d2r;
			double a = Math.pow(Math.sin(u / 2.0), 2)
					+ Math.cos(m.getPosition().latitude * d2r)
					* Math.cos(userLoc.getPosition().latitude * d2r)
					* Math.pow(Math.sin(v / 2.0), 2);
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
			double d = 6371 * c;
			//Format it so it doesn't look terrible
			DecimalFormat df = new DecimalFormat(".00");
			df.setMaximumFractionDigits(2);
			//Displat the distance as a Toast message
			Toast toast = Toast.makeText(this, "You are " + df.format(d)
					+ "km from " + m.getTitle(), Toast.LENGTH_LONG);
			toast.show();
		}
	}

	public void calcHomeDist(Marker m) {
		//Method for calculating the distance from the Users Home to Glasgow
		
		//First, make sure they've set a Home Marker
		if (homeLoc == null) {
			//If not, ask them to do so
			Toast toast = Toast
					.makeText(
							this,
							"Please set a Home City marker by 'long tapping' over your home City and using the title 'Home'",
							Toast.LENGTH_LONG);
			toast.show();
		} else {
			//If they have, use the formula to calculate the distance and convert to km
			double d2r = (Math.PI / 180);
			double u = (m.getPosition().latitude - homeLoc.getPosition().latitude)
					* d2r;
			double v = (m.getPosition().longitude - homeLoc.getPosition().longitude)
					* d2r;
			double a = Math.pow(Math.sin(u / 2.0), 2)
					+ Math.cos(m.getPosition().latitude * d2r)
					* Math.cos(homeLoc.getPosition().latitude * d2r)
					* Math.pow(Math.sin(v / 2.0), 2);
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
			double d = 6371 * c;
			//Again, format it
			DecimalFormat df = new DecimalFormat(".00");
			df.setMaximumFractionDigits(2);
			//And display the distance as a Toast message
			Toast toast = Toast.makeText(this, "You are " + df.format(d)
					+ "km from your Home Country", Toast.LENGTH_LONG);
			toast.show();
		}
	}
}