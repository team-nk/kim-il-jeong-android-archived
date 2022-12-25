package com.teamnk.kimiljung.feature.map

import android.content.Context.LOCATION_SERVICE
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseFragment
import com.teamnk.kimiljung.databinding.FragmentMapBinding

class MapFragment : BaseFragment<FragmentMapBinding>(
    R.layout.fragment_map,
), OnMapReadyCallback {

    private val mapFragment by lazy {
        SupportMapFragment.newInstance()
    }

    private val locationManager: LocationManager by lazy {
        requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager
    }

    private lateinit var currentLocation: LatLng

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUserLocation()
        initMapView()
    }

    private fun initMapView(){
        childFragmentManager.beginTransaction()
            .replace(R.id.map_fragment_map_main, mapFragment, "MapTag").commit()
        mapFragment.getMapAsync(this@MapFragment)
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
        }
    }

    override fun observeEvent() {}
}