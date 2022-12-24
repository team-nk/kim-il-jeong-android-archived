package com.teamnk.kimiljung.feature.fragment.map

import android.Manifest
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
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

    private val mapView: MapView by lazy {
        binding.mapFragmentMapMain
    }

    private val locationManager: LocationManager by lazy {
        requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager
    }

    private lateinit var currentLocation : LatLng

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView.run {
            this.onCreate(savedInstanceState)
            childFragmentManager.beginTransaction().replace(R.id.map_fragment_map_main, mapFragment, "MapTag").commit()
            mapFragment.getMapAsync(this@MapFragment)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        checkPermission()
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

    override fun observeEvent() {}

    private fun checkPermission() {
        if (requireActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                10,
            )
        } else {
            locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)?.apply {
                currentLocation = LatLng(this.latitude, this.longitude)
            }
        }
    }
}