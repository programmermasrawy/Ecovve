package com.q8intouch.ecovve.ui.Tracking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.google.maps.DirectionsApi;
//import com.google.maps.DirectionsApiRequest;
//import com.google.maps.GeoApiContext;
//import com.google.maps.model.DirectionsLeg;
//import com.google.maps.model.DirectionsResult;
//import com.google.maps.model.DirectionsRoute;
//import com.google.maps.model.DirectionsStep;
//import com.google.maps.model.EncodedPolyline;
import com.q8intouch.ecovve.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrackingOrderActivity extends FragmentActivity implements OnMapReadyCallback {
    ArrayList<LatLng> locList = new ArrayList<LatLng>();

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    Circle myCircle1;
    Circle myCircle2;
    Circle myCircle3;
    Circle myCircle4;
    Circle myCircle5;
    Circle myCircle6;
    boolean isOpen = false;
    DatabaseReference databaseRef;

    public void centreMapOnLocation(Location location, String title) {
        isOpen = true;
        LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.clear();
//        mMap.addMarker(new MarkerOptions().position(userLocation).title(title).icon(BitmapDescriptorFactory.fromResource(R.drawable.markers)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 13));
        addingCircleView(userLocation);
        locList.add(userLocation);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (!isOpen) {
                    centreMapOnLocation(lastKnownLocation, "Your Location");
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_order);
        databaseRef = FirebaseDatabase.getInstance().getReference("Orders").child(getIntent().getExtras().getString("track_id"));

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Log.e("TrackID" , getIntent().getExtras().getString("track_id"));
//        Toast.makeText(this, ""+getIntent().getExtras().getString("track_id"), Toast.LENGTH_SHORT).show();
        ((Toolbar) findViewById(R.id.toolbar)).setTitle(getString(R.string.track_));
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (!isOpen) {
                    centreMapOnLocation(location, "Your Location");
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (!isOpen) {
                centreMapOnLocation(lastKnownLocation, "Your Location");
            }
        } else {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }



        Log.e("ChildFB",databaseRef.toString()+"NN");

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue()!=null) {
                    Log.e("ChildFB", dataSnapshot.getValue().toString());
                    try {
                        JSONObject jsonObject = new JSONObject(dataSnapshot.getValue().toString());
                        mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(jsonObject.getString("lat")), Double.parseDouble(jsonObject.getString("lng")))).title("Driver").icon(BitmapDescriptorFactory.fromResource(R.drawable.markers)));
                        locList.add(new LatLng(Double.parseDouble(jsonObject.getString("lat")), Double.parseDouble(jsonObject.getString("lng"))));
//                    mMap.addPolyline((new PolylineOptions()).addAll(locList)
//                            .width(5)
//                            .color(Color.RED)
//                            .geodesic(false));
                        if (locList.size() >= 2) {
                            LatLng origin = locList.get(0);
                            LatLng dest = locList.get(1);

                            // Getting URL to the Google Directions API
                            String url = getUrl(origin, dest);
                            Log.e("onMapClick", url.toString());
                            FetchUrl FetchUrl = new FetchUrl();

                            // Start downloading json data from Google Directions API
                            FetchUrl.execute(url);
                            //move map camera
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(origin));
                            mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
                        }
                        mMap.getUiSettings().setZoomControlsEnabled(true);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(TrackingOrderActivity.this, "مندوب التوصيل لم يبدأ بالتحرك", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void addingCircleView(LatLng mLatLng) {
        CircleOptions circleOptions = new CircleOptions()
                .center(mLatLng)   //set center
                .radius(500)   //set radius in meters
                .fillColor(Color.parseColor("#B7F89E25"))
                .strokeColor(Color.parseColor("#B7F89E25"))
                .strokeWidth(2);
        myCircle1 = mMap.addCircle(circleOptions);
        circleOptions = new CircleOptions()
                .center(mLatLng)   //set center
                .radius(400)   //set radius in meters
                .fillColor(Color.parseColor("#D5FA9D25"))
                .strokeColor(Color.parseColor("#D5FA9D25"))
                .strokeWidth(2);
        myCircle2 = mMap.addCircle(circleOptions);
        circleOptions = new CircleOptions()
                .center(mLatLng)   //set center
                .radius(300)   //set radius in meters
                .fillColor(Color.parseColor("#E9F5900E"))
                .strokeColor(Color.parseColor("#E9F5900E"))
                .strokeWidth(2);
        myCircle3 = mMap.addCircle(circleOptions);
        circleOptions = new CircleOptions()
                .center(mLatLng)   //set center
                .radius(200)   //set radius in meters
                .fillColor(Color.parseColor("#D5FA9D25"))
                .strokeColor(Color.parseColor("#D5FA9D25"))
                .strokeWidth(2);
        myCircle4 = mMap.addCircle(circleOptions);
        circleOptions = new CircleOptions()
                .center(mLatLng)   //set center
                .radius(100)   //set radius in meters
                .fillColor(Color.parseColor("#E9F5900E"))
                .strokeColor(Color.parseColor("#E9F5900E"))
                .strokeWidth(3);
        myCircle5 = mMap.addCircle(circleOptions);

        circleOptions = new CircleOptions()
                .center(mLatLng)   //set center
                .radius(50)   //set radius in meters
                .fillColor(Color.parseColor("#F5900E"))
                .strokeColor(Color.parseColor("#F5900E"))
                .strokeWidth(2);
        myCircle6 = mMap.addCircle(circleOptions);
    }

    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                Log.d("ParserTask",jsonData[0].toString());
                DataParser parser = new DataParser();
                Log.d("ParserTask", parser.toString());

                // Starts parsing data
                routes = parser.parse(jObject);
                Log.d("ParserTask","Executing routes");
                Log.d("ParserTask",routes.toString());

            } catch (Exception e) {
                Log.d("ParserTask",e.toString());
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points;
            PolylineOptions lineOptions = null;

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(10);
                lineOptions.color(Color.RED);

                Log.d("onPostExecute","onPostExecute lineoptions decoded");

            }

            // Drawing polyline in the Google Map for the i-th route
            if(lineOptions != null) {
                mMap.addPolyline(lineOptions);
            }
            else {
                Log.d("onPostExecute","without Polylines drawn");
            }
        }
    }
    private String getUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;


        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters+"&key=AIzaSyCVqXVn0aYTKhE86h_vGBshFdqHB37bUC8";


        return url;
    }


    // Fetches data from url passed
    private class FetchUrl extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
                Log.d("Background Task data", data.toString());
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);

        }
    }


    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();
            Log.d("downloadUrl", data.toString());
            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

}
