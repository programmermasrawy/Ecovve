package com.q8intouch.ecovve.ui.cart.add_address

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.*
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentAddAddressBinding
import kotlinx.android.synthetic.main.fragment_add_address.*
import java.util.*
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.MapView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.q8intouch.ecovve.network.model.EcovveCitiesWithAreas
import com.q8intouch.ecovve.network.model.EcovveOutletArea
import com.q8intouch.ecovve.ui.home.CitiesAdapter
import com.q8intouch.ecovve.util.AppUtils
import com.q8intouch.ecovve.util.LoadingDialog
import com.q8intouch.ecovve.util.Shared
import com.q8intouch.ecovve.util.URLs
import com.q8intouch.ecovve.util.extension.errorResponse
import org.jetbrains.anko.onClick


class AddAddressFragment : BaseFragment<AddAddressViewModel,FragmentAddAddressBinding>()  , OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    override fun getLayoutRes(): Int {
        return  R.layout.fragment_add_address
    }
    var CityId = ""
    lateinit var arrayItems: List< EcovveCitiesWithAreas.Data>
    var mMapView: MapView? = null
    var mapFragment : SupportMapFragment?=null
    @SuppressLint("MissingPermission", "WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpArea()
        btnContinue.setOnClickListener {
            if (validation()) {
                val dialog = LoadingDialog.showDialog(context!!)
                viewModel.addAddress(etAddressName.text.toString(),
                        etPhone.text.toString(),
                        CityId,
                        etBlock.text.toString(),
                        etParcel.text.toString()+".",
                        etHouse.text.toString()+".",
                        etFloor.text.toString()+".",
                        etAdditionInstruction.text.toString()+".",
                        etStreet.text.toString()+".",
                        "",""
                ).observe(this, Observer {
                    dialog.dismiss()
                    if (it.isSuccess) {
                        user()
                    } else {
                        AppUtils.showDailog(activity!!,it.errorResponse().message.toString())
                    }
                })

            }
        }

        mMapView = view.findViewById(R.id.minMap) as MapView
        mMapView!!.onCreate(savedInstanceState)

        mMapView!!.onResume() // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(activity!!.applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        mMapView?.getMapAsync(this)
    }
    var DelArea = ArrayList<EcovveOutletArea.Data.DeliveryArea>()
    @SuppressLint("WrongConstant")
    private fun setUpArea(){
        val sharedPreference: Shared = Shared(context!!)

        val serializedObject = sharedPreference.getValueString("delArea")
        if (serializedObject != null) {
            val gson = Gson()
            val type = object : TypeToken<List<EcovveOutletArea.Data.DeliveryArea>>() {
            }.type
            DelArea = gson.fromJson(serializedObject, type)
        }
        if (DelArea.size !=0 ){
            var list_of_items = ArrayList<String>()
            list_of_items.add(resources.getString(R.string.all_area))
            binding.city.onClick {
                val factory = LayoutInflater.from(activity!!)
                val addToCartDialogView = factory.inflate(R.layout.select_category_item, null)
                val deleteDialog = android.app.AlertDialog.Builder(activity!!).create()
                deleteDialog.setView(addToCartDialogView)
                deleteDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                deleteDialog.window!!.setBackgroundDrawableResource(R.drawable.tab_layout_ui);
                deleteDialog.window!!.setGravity(Gravity.BOTTOM);
                deleteDialog.window!!.attributes.windowAnimations = R.style.DialogAnimation2; //style id

                deleteDialog.show()
                deleteDialog.findViewById<TextView>(R.id.caty_name).text = resources.getString(R.string.select_your_area)

                var lang = URLs.lang
                val promoadapter = DelAreaAdapter(DelArea, lang, context!!, object :
                        DelAreaAdapter.ControlsListeners {
                    override fun click(id: String, name: String, position: Int) {
                        city.text = name
                        CityId = "" + id
                        deleteDialog.dismiss()
                    }
                })
                val layoutmanager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
                deleteDialog.findViewById<RecyclerView>(R.id.sub_caty).layoutManager = layoutmanager
                deleteDialog.findViewById<RecyclerView>(R.id.sub_caty).adapter = promoadapter

                deleteDialog.findViewById<ImageView>(R.id.cancel).setOnClickListener {
                    //delete
                    deleteDialog.dismiss()
                }
            }
        }
        else {
            arrayItems = ArrayList<EcovveCitiesWithAreas.Data>()
            val serializedObject = sharedPreference.getValueString("dataMap")
            if (serializedObject != null) {
                val gson = Gson()
                val type = object : TypeToken<List<EcovveCitiesWithAreas.Data>>() {
                }.type
                arrayItems = gson.fromJson(serializedObject, type)
            }

            var list_of_items = ArrayList<String>()
            list_of_items.add(resources.getString(R.string.all_area))

            binding.city.onClick {
                val factory = LayoutInflater.from(activity!!)
                val addToCartDialogView = factory.inflate(R.layout.select_category_item, null)
                val deleteDialog = android.app.AlertDialog.Builder(activity!!).create()
                deleteDialog.setView(addToCartDialogView)
                deleteDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                deleteDialog.window!!.setBackgroundDrawableResource(R.drawable.tab_layout_ui);
                deleteDialog.window!!.setGravity(Gravity.BOTTOM);
                deleteDialog.window!!.attributes.windowAnimations = R.style.DialogAnimation2; //style id

                deleteDialog.show()
                deleteDialog.findViewById<TextView>(R.id.caty_name).text = resources.getString(R.string.select_your_area)

                var lang = URLs.lang
                val promoadapter = CitiesAdapter(arrayItems, lang, context!!, object :
                        CitiesAdapter.ControlsListeners {
                    override fun click(id: String, name: String, position: Int) {
                        city.text = name
                        CityId = "" + id
                        deleteDialog.dismiss()
                    }
                })
                val layoutmanager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
                deleteDialog.findViewById<RecyclerView>(R.id.sub_caty).layoutManager = layoutmanager
                deleteDialog.findViewById<RecyclerView>(R.id.sub_caty).adapter = promoadapter

                deleteDialog.findViewById<ImageView>(R.id.cancel).setOnClickListener {
                    //delete
                    deleteDialog.dismiss()
                }
            }
        }
    }
    private fun user(){
        var id = activity!!.intent.extras.getString("amount")
        val sharedPreference = Shared(context!!)
        viewModel.fetchUserData(id).observe(this, Observer {
            if (it.isSuccess) {
//                    if (it.resource!!.data.addresses !=null)
                sharedPreference.setList("addresses", it.resource!!.data.addresses!!)
                if (sharedPreference.getValueString("bot").equals(""))
                findNavController().navigate(R.id.OrderCheckoutFragment)
                else
                    onBackPressed()

            }
            else{
                Log.v("infoError",it.error.toString())
            }
        })
    }
    private fun validation() : Boolean {
        var state = false
        if (etAddressName.text.toString().isEmpty()){
            etAddressName.setError(getString(R.string.invaidorempty))
            state = false
        }
        else if (etPhone.text.toString().isEmpty()){
            etPhone.setError(getString(R.string.invaidorempty))
            state = false
        }
        else if (etPhone.text.toString().length<8){
            etPhone.setError(getString(R.string.phonelength))
            state = false
        }
        else if (CityId == ""){
           Snackbar.make(view!!,getString(R.string.area_not_selected),Snackbar.LENGTH_LONG).show()
            state = false
        }
//        else if (etHouse.text.toString().isEmpty()){
//            etHouse.setError(getString(R.string.invaidorempty))
//            state = false
//        }
        else if (etBlock.text.toString().isEmpty()){
            etBlock.setError(getString(R.string.invaidorempty))
            state = false
        }
//        else if (etFloor.text.toString().isEmpty()){
//            etFloor.setError(getString(R.string.invaidorempty))
//            state = false
//        }
//        else if (etStreet.text.toString().isEmpty()){
//            etStreet.setError(getString(R.string.invaidorempty))
//            state = false
//        }
//        else if (etParcel.text.toString().isEmpty()){
//            etParcel.setError(getString(R.string.invaidorempty))
//            state = false
//        }
        else state = true
        return  state
    }

//    private fun getArea() : String{
//        if (etArea.selectedItemPosition!=0){
//            return "" + arrayItems[etArea.selectedItemPosition-1].id
//        }
//        else
//            return ""
//    }

    //define the listener
    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            val sydney = LatLng(location.latitude, location.longitude)
            mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney").icon(BitmapDescriptorFactory.fromResource(R.drawable.address_map_mark)))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,10.0f))

//            val geocoder: Geocoder
//            val addresses: List<Address>
//            geocoder = Geocoder(activity!!, Locale.getDefault())
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

        getLocation()

    }
    fun getLocation() {
        var locationManager = activity!!.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager?

        if (ContextCompat.checkSelfPermission(activity!!, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                activity!!,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_REQUEST_ACCESS_FINE_LOCATION)
            return
        }
        else
        locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PERMISSION_REQUEST_ACCESS_FINE_LOCATION) {
//            when (grantResults[0]) {
//                PackageManager.PERMISSION_GRANTED ->

            getLocation()
//                PackageManager.PERMISSION_DENIED -> //Tell to user the need of grant permission
//            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_ACCESS_FINE_LOCATION) {
            when (grantResults[0]) {
                PackageManager.PERMISSION_GRANTED -> getLocation()
//                PackageManager.PERMISSION_DENIED -> //Tell to user the need of grant permission
            }
        }
    }

    companion object {
        const val PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 100
        fun newInstance() = AddAddressFragment()
    }
}
