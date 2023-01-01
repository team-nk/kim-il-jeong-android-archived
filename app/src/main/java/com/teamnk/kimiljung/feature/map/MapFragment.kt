package com.teamnk.kimiljung.feature.map

import android.Manifest
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.teamnk.kimiljung.BuildConfig
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseFragment
import com.teamnk.kimiljung.databinding.FragmentMapBinding
import com.teamnk.kimiljung.util.showShortSnackBar

class MapFragment : BaseFragment<FragmentMapBinding>(
    R.layout.fragment_map,
), OnMapReadyCallback {

    private val mapFragment by lazy {
        SupportMapFragment.newInstance()
    }

    private val locationManager: LocationManager by lazy {
        requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkUserPermission()
    }

    override fun onResume() {
        super.onResume()
        if(checkGranted()){
            initMapView()
            setUserLocation()
        }else{
            showShortSnackBar(
                view = binding.root,
                text = getString(R.string.fragment_map_please_allow_permisson)
            )
        }
    }

    private lateinit var currentLocation: LatLng

    private fun checkUserPermission(){
        if(checkGranted()){
            initMapView()
            setUserLocation()
        }else{
            moveToOption()
        }
    }

    private fun checkGranted() : Boolean =
        ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION,
            ) == PackageManager.PERMISSION_GRANTED


    private fun moveToOption(){
        startActivity(
            Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + BuildConfig.APPLICATION_ID)
            )
        )
    }

    private fun initMapView(){
        childFragmentManager.beginTransaction()
            .replace(R.id.map_fragment_map_main, mapFragment, "MapTag").commit()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.run {
            addMarker(
                MarkerOptions()
                    .position(currentLocation)
            )
            setMinZoomPreference(10F)
            setMaxZoomPreference(18F)
            moveCamera(
                CameraUpdateFactory.newLatLng(currentLocation)
            )
            animateCamera(
                CameraUpdateFactory.zoomTo(500F)
            )
            mapType = GoogleMap.MAP_TYPE_NORMAL
        }
    }

    private fun setUserLocation(){
        locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)?.run {
            currentLocation = LatLng(this.latitude, this.longitude)
            mapFragment.getMapAsync(this@MapFragment)
        }
    }

    override fun observeEvent() {}
}