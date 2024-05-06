package com.q8intouch.ecovve.ui.maps.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment
import com.google.android.gms.location.places.ui.PlaceSelectionListener
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentSearchBinding
import kotlinx.android.synthetic.main.search_map_fragment.*

class SearchMapFragment : BaseFragment<SearchMapViewModel,FragmentSearchBinding>() , OnMapReadyCallback {
    override fun getLayoutRes(): Int {
    return R.layout.search_map_fragment
    }

    companion object {
        fun newInstance() = SearchMapFragment()
        private const val PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 100
    }

    private lateinit var mMap: GoogleMap
    var lat = ""
    var lon = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        val mapFragment = fragmentManager!!.findFragmentById(R.id.map) as SupportMapFragment?

        cancel.setOnClickListener {
            findNavController().popBackStack()
        }
        confirm.setOnClickListener {

        }
        var mapviewm = view.findViewById<MapView>(R.id.mapview)
        mapviewm.getMapAsync(this)
        val autocompleteFragment =activity!!.
            fragmentManager!!.findFragmentById(R.id.place_autocomplete_fragment) as PlaceAutocompleteFragment

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onError(p0: com.google.android.gms.common.api.Status?) {

            }

            override fun onPlaceSelected(place: Place) {
                mMap.clear()
                lat = ""+place.latLng.latitude
                lon = ""+place.latLng.longitude
                mMap.addMarker(
                    MarkerOptions().position(place.getLatLng())
                        .title(place.getName().toString()).
                            icon(BitmapDescriptorFactory.fromResource(R.drawable.address_map_mark)))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(place.getLatLng()))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 17.0f))
            }

        })
    }


//    //define the listener
//    private val locationListener: LocationListener = object : LocationListener {
//        override fun onLocationChanged(location: Location) {
//            val sydney = LatLng(location.latitude, location.longitude)
//            mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney").icon(BitmapDescriptorFactory.fromResource(R.drawable.address_map_mark)));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
//
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
//        }
//        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
//        override fun onProviderEnabled(provider: String) {}
//        override fun onProviderDisabled(provider: String) {}
//    }
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

        getLocation()

    }
    fun getLocation() {
//        var locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?
//
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//            != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//                PERMISSION_REQUEST_ACCESS_FINE_LOCATION)
//            return
//        }
//        locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == PERMISSION_REQUEST_ACCESS_FINE_LOCATION) {
//            when (grantResults[0]) {
//                PackageManager.PERMISSION_GRANTED -> getLocation()
////                PackageManager.PERMISSION_DENIED -> //Tell to user the need of grant permission
//            }
//        }
    }

}
