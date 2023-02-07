package com.arief.gpspalsu

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    private  val PERMISSION_REQUEST_CODE = 1000
    private lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request the required permissions if they have not been granted
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION),
                0
            )
        } else {
            // Start listening for location updates if the required permissions have been granted
            val fakeLocation = Location(LocationManager.GPS_PROVIDER)
            fakeLocation.latitude = 37.7749
            fakeLocation.longitude = -122.4194
            fakeLocation.accuracy = 5f
            fakeLocation.time = System.currentTimeMillis()
            fakeLocation.elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos()

            // Provide the fake location to the LocationManager
            locationManager.setTestProviderLocation(LocationManager.GPS_PROVIDER, fakeLocation)
        }

    }
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == PERMISSION_REQUEST_CODE && grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED) {
//            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//            // Set a fake location
//            val fakeLocation = Location(LocationManager.GPS_PROVIDER)
//            fakeLocation.latitude = 37.7749
//            fakeLocation.longitude = -122.4194
//            fakeLocation.accuracy = 5f
//            fakeLocation.time = System.currentTimeMillis()
//            fakeLocation.elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos()
//
//            // Provide the fake location to the LocationManager
//            locationManager.setTestProviderLocation(LocationManager.GPS_PROVIDER, fakeLocation)
//        } else {
//            Toast.makeText(applicationContext, "set location first", Toast.LENGTH_SHORT).show()
//        }
//    }
}