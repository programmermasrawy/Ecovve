package com.q8intouch.ecovve.ui.maps

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil.setContentView
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.google.android.gms.location.places.Place
import com.q8intouch.ecovve.R


import com.google.android.gms.location.places.ui.PlaceSelectionListener
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.q8intouch.ecovve.network.model.EcovveAllAreaOutlet
import com.q8intouch.ecovve.ui.HomeActivity
import com.q8intouch.ecovve.util.Shared
import kotlinx.android.synthetic.main.activity_maps_address.*
import kotlin.collections.ArrayList
import android.widget.Toast
import android.app.PendingIntent.getActivity
import android.content.pm.PackageManager
import android.location.*
import android.location.LocationListener
import androidx.core.app.ActivityCompat
import com.facebook.share.Share
import com.google.maps.android.projection.Point
import com.q8intouch.ecovve.ui.cart.add_address.AddAddressFragment
import org.jetbrains.anko.locationManager
import java.util.*


class MapsAddressActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private val PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 100

    override fun onMarkerClick(marker: Marker?): Boolean {
//        confirm.setOnClickListener {
//            val intent = Intent(this, HomeActivity::class.java)
//            intent.putExtra("lat",marker!!.position.latitude)
//            intent.putExtra("lon",marker!!.position.longitude)
//            intent.putExtra("amount",""+getIntent().extras.getString("amount",""))
//            finish()
//            startActivity(intent)
//        }
        return true
    }

    private lateinit var mMap: GoogleMap
    var lat = ""
    var lon = ""
    var locationManager: LocationManager? = null

    var list: List<EcovveAllAreaOutlet.Data> = ArrayList<EcovveAllAreaOutlet.Data>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps_address)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment

        Log.e("mammsmp", getIntent().extras.getString("amount", ""))
        val sharedPreference: Shared = Shared(baseContext)
        val serializedObject = sharedPreference.getValueString("dataMap")
        if (serializedObject != null) {
            val gson = Gson()
            val type = object : TypeToken<List<EcovveAllAreaOutlet.Data>>() {
            }.type
            list = gson.fromJson(serializedObject, type)
        }

        confirm.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            val shared = Shared(baseContext)
            shared.save("lat", lat.toString())
            shared.save("lon", lon.toString())

            intent.putExtra("amount", "" + getIntent().extras.getString("amount", ""))
            finish()
            startActivity(intent)
        }

        cancel.setOnClickListener {
            onBackPressed()
        }

        mapFragment.getMapAsync(this)
        val autocompleteFragment =
                fragmentManager.findFragmentById(R.id.place_autocomplete_fragment) as PlaceAutocompleteFragment

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onError(p0: com.google.android.gms.common.api.Status?) {

            }

            override fun onPlaceSelected(place: Place) {
                mMap.clear()
                lat = "" + place.latLng.latitude
                lon = "" + place.latLng.longitude
                mMap.addMarker(MarkerOptions().position(place.getLatLng())
                        .title(place.getName().toString()).icon(BitmapDescriptorFactory.fromResource(R.drawable.coffee_cup)))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(place.getLatLng()))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 17.0f))
            }

        })
    }

    val TAG = "mylocation"
    private fun displayLocationSettingsRequest(context: Context) {
        var googleApiClient = GoogleApiClient.Builder(context)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        var locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        var builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        var result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback { p0 ->

            var status = p0.getStatus();

            when (status.getStatusCode()) {
                LocationSettingsStatusCodes.SUCCESS ->
                    Log.i(TAG, "All location settings are satisfied.");

                LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE ->
                    Log.i(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog not created.");

                LocationSettingsStatusCodes.RESOLUTION_REQUIRED ->
                    Log.i(TAG, "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");
//                status.startResolutionForResult(this@MapsAddressActivity, REQUEST_CHECK_SETTINGS);


            }
        };
    }

    //define the listener
    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            val sydney = LatLng(location.latitude, location.longitude)
            var ADELAIDE =  LatLngBounds(LatLng(location.latitude, location.longitude),
                    LatLng(location.latitude, location.longitude));
            // Constrain the camera target to the Adelaide bounds.
            mMap.setLatLngBoundsForCameraTarget(ADELAIDE);
            recenter()

            val mapBounds = mMap.getProjection().getVisibleRegion().latLngBounds;
            val nothEastPoint = LatLng(location.latitude, location.longitude)
            val souhWestPoint = mMap.getProjection().toScreenLocation(mapBounds.southwest);

//            val newNorthEast = android.graphics.Point(nothEastPoint.latitude, nothEastPoint.longitude + bottomView.getHeight() / 2);
//            val newSouhWestPoint = android.graphics.Point(souhWestPoint.x, souhWestPoint.y + bottomView.getHeight() / 2);
//
//            val newBounds = LatLngBounds.builder()
//                    .include(mMap.getProjection().fromScreenLocation(newNorthEast))
//                    .include(mMap.getProjection().fromScreenLocation(newSouhWestPoint))
//                    .build();

//            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(newBounds, 0));
//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newBounds.center, 10.0f))
            mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney").icon(BitmapDescriptorFactory.fromResource(R.drawable.address_map_mark)))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 8.0f))
            val geocoder: Geocoder
            val addresses: List<Address>
            geocoder = Geocoder(baseContext!!, Locale.getDefault())

//            addresses = geocoder.getFromLocation(
//                location.latitude,
//                location.longitude,
//                1
//            ) // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//
//            val address =
//                addresses[0].getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//            val city = addresses[0].getLocality()
//            val state = addresses[0].getAdminArea()
//            val country = addresses[0].getCountryName()
//            val postalCode = addresses[0].getPostalCode()
//            val knownName = addresses[0].getFeatureName()
//
//            Log.e("all_address",city+"\n"+country)

//            binding.etArea.setText(city)
//            binding.etBlock.setText(state)
//            binding.etStreet.setText(knownName)
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        recenter()
        getLocation()
        mMap.setOnInfoWindowClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            val shared = Shared(baseContext)
            shared.save("lat", lat.toString())
            shared.save("lon", lon.toString())
            intent.putExtra("amount", "" + getIntent().extras.getString("amount", ""))
            finish()
            startActivity(intent)
        }
        mMap.setOnMarkerClickListener(object : GoogleMap.OnMarkerClickListener {
            override fun onMarkerClick(marker: Marker): Boolean {
                val intent = Intent(this@MapsAddressActivity, HomeActivity::class.java)
                val shared = Shared(baseContext)
                shared.save("lat", lat.toString())
                shared.save("lon", lon.toString())
                intent.putExtra("amount", "" + getIntent().extras.getString("amount", ""))
                finish()
                startActivity(intent)
                return true
            }
        })
    }


     fun recenter() {
        val mapBounds = mMap.getProjection().getVisibleRegion().latLngBounds;
        val nothEastPoint = mMap.getProjection().toScreenLocation(mapBounds.northeast);
        val souhWestPoint = mMap.getProjection().toScreenLocation(mapBounds.southwest);

        val newNorthEast = android.graphics.Point(nothEastPoint.x, nothEastPoint.y + bottomView.getHeight() / 2);
        val newSouhWestPoint = android.graphics.Point(souhWestPoint.x, souhWestPoint.y + bottomView.getHeight() / 2);

        val newBounds = LatLngBounds.builder()
                .include(mMap.getProjection().fromScreenLocation(newNorthEast))
                .include(mMap.getProjection().fromScreenLocation(newSouhWestPoint))
                .build();

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(newBounds, 0));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newBounds.center, 10.0f))

    }

    fun getLocation() {
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?;

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
                    PERMISSION_REQUEST_ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(baseContext!!, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSION_REQUEST_ACCESS_FINE_LOCATION)
            return
        } else {

            locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
        }
    }


    @SuppressLint("MissingPermission")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AddAddressFragment.PERMISSION_REQUEST_ACCESS_FINE_LOCATION) {
//            when (grantResults[0]) {
//                PackageManager.PERMISSION_GRANTED ->
            locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)

            getLocation()
//                PackageManager.PERMISSION_DENIED -> //Tell to user the need of grant permission
//            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == AddAddressFragment.PERMISSION_REQUEST_ACCESS_FINE_LOCATION) {
            when (grantResults[0]) {
                PackageManager.PERMISSION_GRANTED -> {
                    locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
                    getLocation()
                }
                PackageManager.PERMISSION_DENIED -> {
                    Log.e("unable to", "get location")
                }
            }
        }
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorDrawableResourceId: Int): BitmapDescriptor {
        var background = ContextCompat.getDrawable(context, vectorDrawableResourceId);
        background!!.setBounds(0, 0, background.getIntrinsicWidth(), background.getIntrinsicHeight());
        var vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId);
        vectorDrawable!!.setBounds(40, 20, vectorDrawable.getIntrinsicWidth() + 40, vectorDrawable.getIntrinsicHeight() + 20);
        var bitmap = Bitmap.createBitmap(background.getIntrinsicWidth(), background.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        var canvas = Canvas(bitmap);
        background.draw(canvas);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

}
