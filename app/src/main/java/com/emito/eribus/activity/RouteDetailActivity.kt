package com.emito.eribus.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.emito.eribus.R
import com.emito.eribus.directionhelpers.FetchURL
import com.emito.eribus.directionhelpers.TaskLoadedCallback
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_route_detail.*

class RouteDetailActivity : AppCompatActivity() , OnMapReadyCallback, TaskLoadedCallback {
    lateinit var map: GoogleMap
    lateinit var btnGetDirection: Button
    lateinit var place1: MarkerOptions
    lateinit var place2: MarkerOptions
    lateinit var currentPolyline: Polyline
   lateinit var  cameraPosition:LatLng
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_route_detail)

        setContentView(R.layout.activity_route_detail)
        //btnGetDirection = findViewById(R.id.btnGetDirection)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapNearBy) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        var incomingIntent = getIntent()

        var routeCode = incomingIntent.getStringExtra("routeCode")
        var routeFrom = incomingIntent.getStringExtra("routeFrom")
        var routeTo = incomingIntent.getStringExtra("routeTo")
        var deptLat = incomingIntent.getDoubleExtra("deptLat", 0.0)
        var deptLong = incomingIntent.getDoubleExtra("deptLong", 0.0)
        var destLat = incomingIntent.getDoubleExtra("destLat", 0.0)
        var destLong = incomingIntent.getDoubleExtra("destLong", 0.0)

        tvRouteCode.setText("$routeCode")
        tvRouteFrom.setText(routeFrom.toString())
        tvRouteTo.setText(routeTo.toString())
        place1 = MarkerOptions().position(LatLng(deptLat, deptLong)).title("Location 1").icon(
            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        place2 = MarkerOptions().position(LatLng(destLat, destLong)).title("Location 2");

        var url = getUrl(place1.position, place2.position, "driving")
        FetchURL(this).execute(url,"driving")

        cameraPosition=LatLng(deptLat,deptLong)
    }




    override fun onMapReady(googleMap: GoogleMap ) {
        map = googleMap;
        map.isMyLocationEnabled=true
        Log.d("mylog", "Added Markers");
        map.addMarker(place1);
        map.addMarker(place2);

        map.moveCamera(CameraUpdateFactory.newLatLng(cameraPosition))
    }


    private fun getUrl(origin: LatLng, dest: LatLng, directionMode: String ): String {
        // Origin of route
        var str_origin: String = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        var str_dest:String = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        var mode:String = "mode=" + directionMode;
        // Building the parameters to the web service
        var parameters:String = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        var output:String = "json";
        // Building the url to the web service
        var url:String = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key)
        return url;
    }

    override fun onTaskDone(vararg values: Any?) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = map.addPolyline(values[0] as PolylineOptions);
    }
}
