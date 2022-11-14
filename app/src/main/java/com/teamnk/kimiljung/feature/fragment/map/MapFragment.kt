package com.teamnk.kimiljung.feature.fragment.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseFragment
import com.teamnk.kimiljung.databinding.FragmentMapBinding


class MapFragment : BaseFragment<FragmentMapBinding>(
    R.layout.fragment_map,
), OnMapReadyCallback{

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mvMap.run {
            onCreate(savedInstanceState)
            getMapAsync(this@MapFragment)
        }
    }

    override fun observeEvent() {
    }

    override fun onMapReady(googleMap: GoogleMap) {

        MapsInitializer.initialize(requireActivity())

        val seoul = LatLng(37.554891, 126.970814)

        googleMap.run {
            addMarker(MarkerOptions().run {
                position(seoul)
                title("서울")
                snippet("한국의 수도")
            })
            moveCamera(CameraUpdateFactory.newLatLng(seoul))
            animateCamera(CameraUpdateFactory.zoomTo(500F))
            mapType = GoogleMap.MAP_TYPE_HYBRID
        }
    }
}