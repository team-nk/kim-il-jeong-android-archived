package com.teamnk.kimiljung.feature.fragment.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseFragment
import com.teamnk.kimiljung.databinding.FragmentMapBinding

class MapFragment : BaseFragment<FragmentMapBinding>(
    R.layout.fragment_map,
), OnMapReadyCallback {

    private val mapView : MapView by lazy {
        binding.mvMap
    }

    private val seoul : LatLng by lazy {
        LatLng(37.554891, 126.970814)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView.run {
            this.onCreate(savedInstanceState)
            getMapAsync(this@MapFragment)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.run {
            addMarker(
                MarkerOptions()
                    .title("서울")
                    .snippet("한국의 수도")
                    .position(seoul)
            )
            setMinZoomPreference(10F)
            setMaxZoomPreference(18F)
            moveCamera(
                CameraUpdateFactory.newLatLng(seoul)
            )
            animateCamera(
                CameraUpdateFactory.zoomTo(500F)
            )
            mapType = GoogleMap.MAP_TYPE_NORMAL
        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun observeEvent() {
    }
}