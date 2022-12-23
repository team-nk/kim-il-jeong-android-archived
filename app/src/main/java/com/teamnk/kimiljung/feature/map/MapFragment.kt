package com.teamnk.kimiljung.feature.map

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseFragment
import com.teamnk.kimiljung.databinding.FragmentMapBinding

class MapFragment : BaseFragment<FragmentMapBinding>(
    R.layout.fragment_map,
), OnMapReadyCallback {

    private val mapView: MapView by lazy {
        binding.mapFragmentMapMain
    }

    private val latLngSeoul: LatLng by lazy {
        LatLng(37.554891, 126.970814)
    }

    private val mapFragment by lazy {
        SupportMapFragment.newInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView.run {
            this.onCreate(savedInstanceState)
            childFragmentManager.beginTransaction().replace(R.id.map_fragment_map_main, mapFragment, "MapTag").commit()
            mapFragment.getMapAsync(this@MapFragment)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.run {
            addMarker(
                MarkerOptions().title("서울").snippet("한국의 수도").position(latLngSeoul)
            )
            setMinZoomPreference(10F)
            setMaxZoomPreference(18F)
            moveCamera(
                CameraUpdateFactory.newLatLng(latLngSeoul)
            )
            animateCamera(
                CameraUpdateFactory.zoomTo(500F)
            )
            mapType = GoogleMap.MAP_TYPE_NORMAL
        }
    }

    override fun observeEvent() {}
}