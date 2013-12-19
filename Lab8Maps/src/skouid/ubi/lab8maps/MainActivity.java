package skouid.ubi.lab8maps;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import skouid.ubi.lab8maps.R;
import android.app.Activity;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnItemSelectedListener {

	// LatLangs for places hosted
	static final LatLng Glasgow = new LatLng(55.8580, -4.2950);
	static final LatLng Delhi = new LatLng(28.61, 77.23);
	static final LatLng Melbourne = new LatLng(-37.813611, 144.963056);
	static final LatLng Manchester = new LatLng(53.466667, -2.233333);
	static final LatLng Kualalumpur = new LatLng(3.1475, 101.693333);
	static final LatLng Victoria = new LatLng(48.428611, -123.365556);
	static final LatLng Auckland = new LatLng(-36.840417, 174.739869);
	static final LatLng Brisbane = new LatLng(-27.467917, 153.027778);
	static final LatLng Edmonton = new LatLng(53.533333, -113.5);
	static final LatLng Christchurch = new LatLng(-43.53, 172.620278);
	static final LatLng Edinburgh = new LatLng(55.939, -3.172);

	// LatLangs for locations in glasgow
	static final LatLng SECC = new LatLng(55.8607, -4.2871);
	static final LatLng BARRYBUDDONSHOOTING = new LatLng(56.499, -2.7543);
	static final LatLng PARKHEAD = new LatLng(55.8497, -4.2055);
	static final LatLng CATHKINBRAES = new LatLng(55.79434, -4.2193);
	static final LatLng VELODROME = new LatLng(55.847, -4.2076);
	static final LatLng INTERNATIONALHOCKEY = new LatLng(55.8447, -4.236);
	static final LatLng HAMPDEN = new LatLng(55.8255, -4.2520);
	static final LatLng IBROX = new LatLng(55.853, -4.309);
	static final LatLng KELVINGROVEBOWLS = new LatLng(55.867, -4.2871);
	static final LatLng SCOTSTOUN = new LatLng(55.8813, -4.3405);
	static final LatLng TOLLCROSS = new LatLng(55.845, -4.177);
	static final LatLng STRATHCLYDE = new LatLng(55.7971971, -4.0342997);
	static final LatLng EDINBURGHPOOL = new LatLng(55.939, -3.172);

	//map
	private GoogleMap map;
	//button that changes views 
	private Button viewButton;
	int i;
	//spinner dropdown
	private Spinner places;
	//button to find user position
	
	private Button posButton;
	//user position variable
	LatLng myPosition;
	EditText latText;
	EditText lonText;
	Location location;
	//latitude and longitude
	double latitude;
	double longitude;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//setup edittext boxes
		latText = (EditText) this.findViewById(R.id.lat);
		lonText = (EditText) this.findViewById(R.id.lon);

		//setup map fragment
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		//set user location to true to auto detect location
		map.setMyLocationEnabled(true);
		map.getUiSettings().setZoomControlsEnabled(true);
		//set button so user can find the way back to their location
		map.getUiSettings().setMyLocationButtonEnabled(true);
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		Criteria criteria = new Criteria();

		// Getting the name of the best provider
		String provider = locationManager.getBestProvider(criteria, true);

		// Getting Current Location
		location = locationManager.getLastKnownLocation(provider);

		if (location != null) {
			// Getting latitude of the current location

			latitude = location.getLatitude();

			longitude = location.getLongitude();
			
			myPosition = new LatLng(latitude, longitude);
			//setup marker on your position
			map.addMarker(new MarkerOptions().position(myPosition).title(
					"Your Position"));
		}

		//setup spinner
		places = (Spinner) this.findViewById(R.id.PlaceSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.Places, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		places.setAdapter(adapter);
		places.setOnItemSelectedListener(this);

		///add markers with titles icons and snippets with relevant information using the ltlngs setup above//
		map.addMarker(new MarkerOptions().position((Glasgow)).flat(true)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.scot))
				.title("Scotland").snippet("Glasgow 2014"));
		// add markers for locations on glasgow
		map.addMarker(new MarkerOptions()
				.position((SECC))
				.flat(true)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.boxing))
				.title("SECC")
				.snippet(
						"Exhibition Way Glasgow G3 8YW "
								+ "Marital arts, gymnastics and Weightlifting"));
		map.addMarker(new MarkerOptions()
				.position((BARRYBUDDONSHOOTING))
				.flat(true)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.shooting))
				.title("Barry Buddon Shooting Centre")
				.snippet(
						"Barry Carnoustie Angus DD7 7RY "+"Shooting"));
		map.addMarker(new MarkerOptions().position((PARKHEAD)).flat(true)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.open))
				.title("Parkhead")
				.snippet("Celtic Park Glasgow G40 3RE "+"Opening Ceremony"));
		map.addMarker(new MarkerOptions()
				.position((CATHKINBRAES))
				.flat(true)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.cycling))
				.title("Cathkin Braes Mountain Bike Trails")
				.snippet(
						"Cathkin Road Glasgow G45 " +
						"Mountain Biking"
								));
		map.addMarker(new MarkerOptions()
				.position((VELODROME))
				.flat(true)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.cycling))
				.title("Emirates Arena and Velodrome")
				.snippet(
						 "1000 London Rd, Glasgow Scotland G40 3H "+"Range of sports and Cycling"
								));
		map.addMarker(new MarkerOptions()
				.position((INTERNATIONALHOCKEY))
				.flat(true)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.hockey))
				.title("Glasgow Green Hockey Centre")
				.snippet("28 King's Dr Glasgow Glasgow City G40 1HB "+"Hockey"));
		map.addMarker(new MarkerOptions().position((HAMPDEN)).flat(true)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.track))
				.title("Hampden")
				.snippet("Letherby Dr Glasgow G42 9BA "+"Track and Field"));
		map.addMarker(new MarkerOptions()
				.position((IBROX))
				.flat(true)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.rugby))
				.title("Ibrox")
				.snippet("150 Edmiston Dr Glasgow Lanarkshire G51 2XD "+"Rugby Sevens"));
		map.addMarker(new MarkerOptions()
				.position((KELVINGROVEBOWLS))
				.flat(true)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.bowls))
				.title("Kelvingrove Lawn Bowls Centre")
				.snippet(
						"Kelvingrove Park Kelvin Way Glasgow G3 7TA "+"Lawn Bowls"));
		map.addMarker(new MarkerOptions()
				.position((SCOTSTOUN))
				.flat(true)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.ttennis))
				.title("Scotsoun Sports Campus")
				.snippet("72 Danes Dr Glasgow G14 9HD "+"Table Tennis"));
		map.addMarker(new MarkerOptions()
				.position((TOLLCROSS))
				.flat(true)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.swimming))
				.title("Tollcross International Swimming Centre")
				.snippet("367 Wellshot Rd Glasgow G32 7Q P"+
						"Swimming"));
		map.addMarker(new MarkerOptions()
				.position((STRATHCLYDE))
				.flat(true)
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.triathlon))
				.title("Strathclyde Country Park")
				.snippet("366 Hamilton Rd Motherwell ML1 3ED "+"Triathlon"));
		map.addMarker(new MarkerOptions()
				.position((EDINBURGHPOOL))
				.flat(true)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.swimming))
				.title("Royal Commonwealth Pool")
				.snippet("21 Dalkeith Rd Edinburgh Midlothian EH16 5BB "+
						"Diving"));
		
		//setup position button
		posButton = (Button) this.findViewById(R.id.posButton);
		posButton.setText("Get Position");
		posButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String latString = latText.getText().toString();
				String lonString = lonText.getText().toString();

				//if no input set location to normal latitude an longitutdes
				//else set new marker on the input values and move the camera of the map
				if (location != null) {
					// Getting latitude of the current location
					if (TextUtils.isEmpty(latString)) {
						latitude = location.getLongitude();
					} else {
						latitude = Double.parseDouble(latString);
					}
					if (TextUtils.isEmpty(lonString)) {
						longitude = location.getLongitude();
					} else {
						longitude = Double.parseDouble(lonString);
					}
					myPosition = new LatLng(latitude, longitude);

					map.addMarker(new MarkerOptions().position(myPosition)
							.title("Your Set Position"));
					map.moveCamera(CameraUpdateFactory.newLatLng(myPosition));
				}
			}
		});
		
		//setup view button
		viewButton = (Button) this.findViewById(R.id.viewButton);
		viewButton.setText("Normal View");
		viewButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				//each click increase the number and change the map view accordingly
				i++;
				if (i == 1) {
					map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
					viewButton.setText("Satellite View");
				} else if (i == 2) {
					map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
					viewButton.setText("Terrain View");
				} else if (i == 3) {
					map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
					viewButton.setText("Hybrid View");
				} else {
					map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
					viewButton.setText("Normal View");
					i = 0;
				}
			}
		});
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		
		//for all the locations in the spinner give them a marker, title, icon and snippet
		if (places.getSelectedItem().toString().equals("Glasgow")) {
			map.moveCamera(CameraUpdateFactory.newLatLng(Glasgow));
			map.addMarker(new MarkerOptions()
					.position((Glasgow))
					.flat(true)
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.scot))
					.title("Scotland").snippet("Glasgow 2014"));

		} else if (places.getSelectedItem().toString().equals("Delhi")) {
			map.moveCamera(CameraUpdateFactory.newLatLng(Delhi));

			map.addMarker(new MarkerOptions()
					.position((Delhi))
					.flat(true)
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.india)).title("India")
					.snippet("Delhi 2010 " + "Total Medals For Scotland: 26"));

		} else if (places.getSelectedItem().toString().equals("Melbourne")) {
			map.moveCamera(CameraUpdateFactory.newLatLng(Melbourne));
			map.addMarker(new MarkerOptions()
					.position((Melbourne))
					.flat(true)
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.aust))
					.title("Australia")
					.snippet(
							"Melbourne 2006 " + "Total Medals For Scotland: 29"));

		} else if (places.getSelectedItem().toString().equals("Manchester")) {
			map.moveCamera(CameraUpdateFactory.newLatLng(Manchester));
			map.addMarker(new MarkerOptions()
					.position((Manchester))
					.flat(true)
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.eng))
					.title("England")
					.snippet(
							"Manchester 2002 "
									+ "Total Medals For Scotland: 29"));

		} else if (places.getSelectedItem().toString().equals("Kuala Lumpur")) {
			map.moveCamera(CameraUpdateFactory.newLatLng(Kualalumpur));
			map.addMarker(new MarkerOptions()
					.position((Kualalumpur))
					.flat(true)
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.maly))
					.title("Malaysia")
					.snippet(
							"Kuala Lumpur 1998 "
									+ "Total Medals For Scotland: 12"));

		} else if (places.getSelectedItem().toString().equals("Victoria")) {
			map.moveCamera(CameraUpdateFactory.newLatLng(Victoria));
			map.addMarker(new MarkerOptions()
					.position((Victoria))
					.flat(true)
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.can))
					.title("Canada")
					.snippet("Victoria 1994 " + "Total Medals For Scotland: 20"));

		} else if (places.getSelectedItem().toString().equals("Auckland")) {
			map.moveCamera(CameraUpdateFactory.newLatLng(Auckland));
			map.addMarker(new MarkerOptions()
					.position((Auckland))
					.flat(true)
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.newz))
					.title("New Zealand")
					.snippet("Auckland 1990 " + "Total Medals For Scotland: 22"));

		} else if (places.getSelectedItem().toString().equals("Edinburgh")) {
			map.moveCamera(CameraUpdateFactory.newLatLng(Edinburgh));
			map.addMarker(new MarkerOptions()
					.position((Edinburgh))
					.flat(true)
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.scot))
					.title("Scotland")
					.snippet(
							"Edinburgh 1986 " + "Total Medals For Scotland: 33"));

		} else if (places.getSelectedItem().toString().equals("Brisbane")) {
			map.moveCamera(CameraUpdateFactory.newLatLng(Brisbane));
			map.addMarker(new MarkerOptions()
					.position((Brisbane))
					.flat(true)
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.aust))
					.title("Australia")
					.snippet("Brisbane 1982 " + "Total Medals For Scotland: 26"));

		} else if (places.getSelectedItem().toString().equals("Edmonton")) {
			map.moveCamera(CameraUpdateFactory.newLatLng(Edmonton));
			map.addMarker(new MarkerOptions()
					.position((Edmonton))
					.flat(true)
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.can))

					.title("Canada")
					.snippet("Edmonton 1978 " + "Total Medals For Scotland: 14"));

		} else if (places.getSelectedItem().toString().equals("Christchurch")) {
			map.moveCamera(CameraUpdateFactory.newLatLng(Christchurch));
			map.addMarker(new MarkerOptions()
					.position((Christchurch))
					.flat(true)
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.newz))
					.title("New Zealand")
					.snippet(
							"Christchurch 1974 "
									+ "Total Medals For Scotland: 19"));

		}
	}

	/** Called when the user clicks the Send button */
	public void sendMessage(View view) {
		/*
		 * Intent intent = new Intent(this, DiaryClass.class); EditText editText
		 * = (EditText) findViewById(R.id.edit_message); String message =
		 * editText.getText().toString(); intent.putExtra(EXTRA_MESSAGE,
		 * message);
		 */
//start the new diary class
		Intent intent = new Intent(this, DiaryActivity.class);
		startActivity(intent);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {

	}
}
