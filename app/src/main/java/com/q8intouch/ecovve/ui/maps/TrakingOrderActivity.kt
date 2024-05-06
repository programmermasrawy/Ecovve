package com.q8intouch.ecovve.ui.maps

import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import com.q8intouch.ecovve.R

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.android.PolyUtil;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.TravelMode;
import org.jetbrains.anko.locationManager
import org.joda.time.DateTime;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

class TrakingOrderActivity :  AppCompatActivity(),  OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private val overview = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_traking_order)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment


        mapFragment.getMapAsync(this)

    }




    override fun onMapReady(googleMap: GoogleMap) {
        setupGoogleMapScreenSettings(googleMap)
        val results = getDirectionsDetails("483 George St, Sydney NSW 2000, Australia", "182 Church St, Parramatta NSW 2150, Australia", TravelMode.DRIVING)
        if (results != null) {
            addPolyline(results, googleMap)
            positionCamera(results.routes[overview], googleMap)
            addMarkersToMap(results, googleMap)
        }
        else{
            Toast.makeText(baseContext,"no result",Toast.LENGTH_LONG).show()
        }
    }

    private fun addMarkersToMap(results: DirectionsResult, mMap: GoogleMap) {
        mMap.addMarker(MarkerOptions().position(LatLng(results.routes[overview].legs[overview].startLocation.lat, results.routes[overview].legs[overview].startLocation.lng)).title(results.routes[overview].legs[overview].startAddress))
        mMap.addMarker(MarkerOptions().position(LatLng(results.routes[overview].legs[overview].endLocation.lat, results.routes[overview].legs[overview].endLocation.lng)).title(results.routes[overview].legs[overview].startAddress).snippet(getEndLocationTitle(results)))
    }

    private fun positionCamera(route: DirectionsRoute, mMap: GoogleMap) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(route.legs[overview].startLocation.lat, route.legs[overview].startLocation.lng), 12f))
    }


    private fun addPolyline(results: DirectionsResult, mMap: GoogleMap) {
        val decodedPath = PolyUtil.decode(results.routes[overview].overviewPolyline.encodedPath)
        mMap.addPolyline(PolylineOptions().addAll(decodedPath))
    }


    private fun getGeoContext(): GeoApiContext {
        val geoApiContext = GeoApiContext()
        return geoApiContext
                .setQueryRateLimit(3)
                .setApiKey(getString(R.string.google_maps_key))
                .setConnectTimeout(1, TimeUnit.SECONDS)
                .setReadTimeout(1, TimeUnit.SECONDS)
                .setWriteTimeout(1, TimeUnit.SECONDS)
    }


    private fun getEndLocationTitle(results: DirectionsResult): String {
        return "Time :" + results.routes[overview].legs[overview].duration.humanReadable + " Distance :" + results.routes[overview].legs[overview].distance.humanReadable
    }


    private fun setupGoogleMapScreenSettings(mMap: GoogleMap) {
        mMap.isBuildingsEnabled = true
        mMap.isIndoorEnabled = true
        mMap.isTrafficEnabled = true
        val mUiSettings = mMap.uiSettings
//        mUiSettings.isZoomControlsEnabled = true
        mUiSettings.isCompassEnabled = true
        mUiSettings.isMyLocationButtonEnabled = true
        mUiSettings.isScrollGesturesEnabled = true
//        mUiSettings.isZoomGesturesEnabled = true
        mUiSettings.isTiltGesturesEnabled = true
        mUiSettings.isRotateGesturesEnabled = true
    }

    private fun getDirectionsDetails(origin: String, destination: String, mode: TravelMode): DirectionsResult? {
        val now = DateTime()
        try {
            return DirectionsApi.newRequest(getGeoContext())
                    .mode(mode)
                    .origin(origin)
                    .destination(destination)
                    .departureTime(now)
                    .await()
        } catch (e: ApiException) {
            e.printStackTrace()
            return null
        } catch (e: InterruptedException) {
            e.printStackTrace()
            return null
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

    }
}