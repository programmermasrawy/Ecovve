package com.q8intouch.ecovve.ui.splash

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.media.audiofx.BassBoost
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.Settings
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.google.android.gms.maps.model.LatLng

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentSplashBinding
import com.q8intouch.ecovve.util.Shared
import org.jetbrains.anko.bundleOf
import androidx.core.content.ContextCompat.getSystemService
import com.q8intouch.ecovve.ui.HomeActivity


class SplashFragment : BaseFragment<SplashViewModel, FragmentSplashBinding>() {

    var mProgressBar: ProgressBar?=null
    var mCountDownTimer: CountDownTimer?=null
    var i = 0
    var USER_ID = -1
    override fun getLayoutRes(): Int = R.layout.fragment_splash

    @SuppressLint("MissingPermission")
    override fun onResume() {
        super.onResume()
        var service = activity!!.getSystemService(LOCATION_SERVICE) as LocationManager
        var enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);

        // check if enabled and if not send user to the GSP settings
        // Better solution would be to display a dialog and suggesting to
        // go to the settings
        if (!enabled) {
            var intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        } else {
            val sharedPreference: Shared = Shared(context!!)
            USER_ID = sharedPreference.getValueInt("id")
            if (USER_ID != -1) {
                viewModel.startSplashAndInitializeApp(0, true, sharedPreference.getValueString("token")!!)
                    .observe(this, Observer { isPasswordRemembered ->
                        getLocation(isPasswordRemembered)
                    })
            } else {
                viewModel.startSplashAndInitializeApp(0, false, "")
                    .observe(this, Observer { isPasswordRemembered ->
                        //                    if (isPasswordRemembered) this.findNavController().navigate(R.id.action_splashFragment_to_homeActivity)
//                    else this.findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                        getLocation(isPasswordRemembered)
                    })
            }
        }
    }
    fun getLocation(passwordRemembered: Boolean) {
        var locationManager = activity!!.getSystemService(LOCATION_SERVICE) as LocationManager?

        if (ContextCompat.checkSelfPermission(activity!!, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                activity!!,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                100)
            return
        }
        if (ContextCompat.checkSelfPermission(activity!!, android.Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(activity!!, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED ) {
            var a =  arrayOf(android.Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_PHONE_STATE)
            ActivityCompat.requestPermissions(activity!!,a,1)
        }

//        Toast.makeText(context!!,USER_ID,Toast.LENGTH_LONG).show()

        var bundle = bundleOf("amount" to ""+USER_ID)

        if (passwordRemembered) {
//            this.findNavController().navigate(R.id.action_splashFragment_to_homeActivity,bundle)
            val intent = Intent(activity, HomeActivity::class.java)
            intent.putExtra("amount", "" + USER_ID)
            activity!!.finish()
            startActivity(intent)
        }else {
            val bundle = Bundle()
            bundle.putString("id", "")
            bundle.putString("token","")
            this.findNavController().navigate(R.id.action_splashFragment_to_loginFragment,bundle)
        }
        locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            when (grantResults[0]) {
//                PackageManager.PERMISSION_GRANTED -> getLocation(passwordRemembered)
//                PackageManager.PERMISSION_DENIED -> //Tell to user the need of grant permission
            }
        }
        if (requestCode == 0){
            var gps_enabled = false
            var network_enabled = false

            val lm = activity!!
                .getSystemService(Context.LOCATION_SERVICE) as LocationManager

            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            var net_loc: Location? = null
            var gps_loc: Location? = null
            var finalLoc: Location? = null

            if (gps_enabled)
                gps_loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (network_enabled)
                net_loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

            if (gps_loc != null && net_loc != null) {

                //smaller the number more accurate result will
                if (gps_loc.accuracy > net_loc.accuracy)
                    finalLoc = net_loc
                else
                    finalLoc = gps_loc

                // I used this just to get an idea (if both avail, its upto you which you want to take as I've taken location with more accuracy)

            } else {

                if (gps_loc != null) {
                    finalLoc = gps_loc
                } else if (net_loc != null) {
                    finalLoc = net_loc
                }
            }
        }
    }

    private val locationListener: LocationListener = object : LocationListener {
                override fun onLocationChanged(location: Location) {
            val sydney = LatLng(location.latitude, location.longitude)
//            mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney").icon(BitmapDescriptorFactory.fromResource(R.drawable.address_map_mark)));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

//            val geocoder: Geocoder
//            val addresses: List<Address>
//            geocoder = Geocoder(applicationContext, Locale.getDefault())
//
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
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    val TAG = "mylocation"
    private fun displayLocationSettingsRequest(context: Context, passwordRemembered: Boolean) {
        var googleApiClient =  GoogleApiClient.Builder(context)
            .addApi(LocationServices.API).build();
        googleApiClient.connect();

        var locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        var builder =  LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        var result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback { p0 ->

            var status = p0.getStatus();

            when (status.getStatusCode()) {
                LocationSettingsStatusCodes.SUCCESS ->
                    getLocation(passwordRemembered)
//                    Log.i(TAG, "All location settings are satisfied.")

                LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE ->
              //  getLocation()
                    Log.i(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog not created.");

                LocationSettingsStatusCodes.RESOLUTION_REQUIRED->
                    Log.i(TAG, "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");
//                status.startResolutionForResult(this@MapsAddressActivity, REQUEST_CHECK_SETTINGS);


            }
        };
    }

}
