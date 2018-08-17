package com.eslam.pokemon

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        checkPermission();
    }

    var userPermissionResult=123;
    fun checkPermission(){
        if (Build.VERSION.SDK_INT>=23){
            if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),userPermissionResult);
                return;
            }
        }
        getMyLocation();
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode==userPermissionResult){
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                getMyLocation()
            }
            else{
                Toast.makeText(this,"You rejected the permission",Toast.LENGTH_LONG).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    var mylocationListener:MyLocationListener?=null;

    fun getMyLocation(){
        Toast.makeText(this,"Get location started",Toast.LENGTH_LONG).show()
        mylocationListener = MyLocationListener();
        var locationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager;
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3,3f,mylocationListener);
        var myThread=MyThread();
        myThread.start();
    }

    var myLocation:Location?=null;
    inner class MyLocationListener:LocationListener{
        constructor(){
            myLocation=Location("Inital me");
            myLocation!!.longitude=151.0
            myLocation!!.latitude=-34.0
        }
        override fun onLocationChanged(p0: Location?) {
            Log.v("Location_change","location : ${p0!!.longitude} ${p0!!.latitude}")
            myLocation=p0;
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

        }

        override fun onProviderEnabled(p0: String?) {

        }

        override fun onProviderDisabled(p0: String?) {

        }

    }

    inner class MyThread:Thread{
        constructor():super()

        override fun run() {
            while (true)
            {
                try{
                    runOnUiThread {
                    mMap.clear();
                    val sydney = LatLng(myLocation!!.latitude, myLocation!!.longitude)
                    mMap.addMarker(MarkerOptions().position(sydney).title("Me").snippet("Here is my location").icon(BitmapDescriptorFactory.fromResource(R.drawable.superman)));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,10f))

                }
                    Thread.sleep(10000)
                }
                catch (ex:Exception){

                }

            }
            super.run()
        }
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
//        getMyLocation();
//        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Me").snippet("Here is my location").icon(BitmapDescriptorFactory.fromResource(R.drawable.superman)));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,10f))
    }
}
