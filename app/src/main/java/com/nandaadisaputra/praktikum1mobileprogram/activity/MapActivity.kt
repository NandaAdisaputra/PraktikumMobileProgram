package com.nandaadisaputra.praktikum1mobileprogram.activity

import android.Manifest
import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnSuccessListener
import com.nandaadisaputra.praktikum1mobileprogram.R
import pub.devrel.easypermissions.EasyPermissions
import java.io.IOException
import java.util.*

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    //private lateinit var mFusedLocation: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        // GET MY CURRENT LOCATION
        val mFusedLocation = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocation.lastLocation.addOnSuccessListener(this, object : OnSuccessListener<Location> {
            override fun onSuccess(location: Location?) {
                // In some rare condition it would be null
                if(location != null){
                    // Do it all with location
                    Log.d("My Current location", "Lat : ${location?.latitude} Long : ${location?.longitude}")
                    // Display in Toast
                    Toast.makeText(this@MapActivity,
                            "Lat : ${location?.latitude} Long : ${location?.longitude}",
                            Toast.LENGTH_LONG).show()

                    val myLocation = LatLng(location.latitude, location.longitude)
                    val geoCoder = Geocoder(this@MapActivity, Locale.getDefault())

                    try {
                        val listAddress : List<Address> = geoCoder.getFromLocation(location.latitude, location.longitude, 1)
                        if(listAddress.size > 0){
                            val placeAddress= listAddress.get(0).getAddressLine(0)
                            val placeName = listAddress.get(0).featureName
                            Log.d("location me", "${listAddress.get(0).featureName} ${listAddress.get(0).adminArea} ${listAddress.get(0).subLocality} ${listAddress.get(0).locale}")

                            Toast.makeText(applicationContext, "$placeName $placeAddress", Toast.LENGTH_LONG).show()

                        }

                    }catch (e: IOException){
                        e.printStackTrace()
                    }

                    mMap.addMarker(MarkerOptions().position(myLocation).title("My Location"))
                    //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 12f))
                    val cu = CameraUpdateFactory.newLatLngZoom(myLocation, 16f)
                    // Animate Camera
                    mMap.animateCamera(cu)
                }
            }

        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Request permission
        requestLocationPermission()
        // Add a marker in Sydney and move the camera


    }


    private fun requestLocationPermission() {
        val perms = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)

        if (EasyPermissions.hasPermissions(this, *perms)) {
            // Already have permission, do the thing
            // ...
            getCurrentLocation()
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "Need", 1, *perms)
//            EasyPermissions.requestPermissions(this@MainActivity, "Membutuhkan akses GPS",
//                    RC_LOCATION, perms)

        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);

        // Get user location

    }

}
