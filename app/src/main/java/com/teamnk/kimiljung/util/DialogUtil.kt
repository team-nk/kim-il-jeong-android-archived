package com.teamnk.kimiljung.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseBottomSheetDialogFragment
import com.teamnk.kimiljung.databinding.DialogCreateScheduleBinding
import com.teamnk.kimiljung.databinding.DialogDoubleButtonBinding
import com.teamnk.kimiljung.databinding.DialogSearchLocationBinding
import com.teamnk.kimiljung.databinding.DialogSingleButtonBinding

fun showDialogWithSingleButton(
    context: Context,
    title: String,
    description: String,
    functionWhenAcceptButtonClicked: () -> Unit
) {

    val binding: DialogSingleButtonBinding by lazy {
        DialogSingleButtonBinding.inflate(
            LayoutInflater.from(context)
        )
    }

    val dialog = initDialog(
        context = context,
        binding = binding,
    )

    with(binding) {
        tvDialogSingleButtonTitle.text = title
        tvDialogSingleButtonContent.text = description
        btnDialogSingleButtonAction.setOnClickListener {
            functionWhenAcceptButtonClicked()

            dialog.dismiss()
        }
    }
}

fun showDialogWithDoubleButton(
    context: Context,
    title: String,
    actionText: String,
    functionWhenPrimaryButtonClicked: () -> Unit
) {

    val binding: DialogDoubleButtonBinding by lazy {
        DialogDoubleButtonBinding.inflate(
            LayoutInflater.from(context)
        )
    }

    val dialog = initDialog(
        context = context,
        binding = binding,
    )

    with(binding) {
        tvDialogDoubleButtonTitle.text = title
        btnDialogDoubleButtonAction.apply {
            text = actionText
            setOnClickListener {
                functionWhenPrimaryButtonClicked()

                dialog.dismiss()
            }
        }
        btnDialogDoubleButtonCancel.setOnClickListener {
            dialog.dismiss()
        }
    }
}

fun showScheduleCreateDialog(
    context: Context,
    fragmentManager: FragmentManager,
) {
    val binding: DialogCreateScheduleBinding by lazy {
        DialogCreateScheduleBinding.inflate(
            LayoutInflater.from(context)
        )
    }

    binding.tvDialogCreateScheduleSearchLocation.setOnClickListener {
        val searchLocationDialog = SearchLocationDialog()
        searchLocationDialog.show(fragmentManager, searchLocationDialog.tag)
    }
}

class SearchLocationDialog : BaseBottomSheetDialogFragment<DialogSearchLocationBinding>(
    R.layout.dialog_search_location
), OnMapReadyCallback{

    private val mapFragment by lazy {
        SupportMapFragment.newInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMapView()
        initCloseButton()
    }

    private fun initMapView(){
        childFragmentManager.beginTransaction()
            .replace(R.id.map_dialog_search_location_map_main, mapFragment, "MapTag").commit()
        mapFragment.getMapAsync(this@SearchLocationDialog)
    }

    private fun initCloseButton(){
        binding.btnDialogSearchLocationCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun onCreateDialog(
        savedInstanceState: Bundle?,
    ): Dialog {

        isCancelable = false

        return BottomSheetDialog(requireContext()
        ).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback(){
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if(newState == BottomSheetBehavior.STATE_DRAGGING){
                        behavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                }
            })
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.run {
            addMarker(
                MarkerOptions()
                    .position(LatLng(3.4, 5.6))
            )
            setMinZoomPreference(10F)
            setMaxZoomPreference(18F)
            moveCamera(
                CameraUpdateFactory.newLatLng(LatLng(3.4, 5.6))
            )
            animateCamera(
                CameraUpdateFactory.zoomTo(500F)
            )
            mapType = GoogleMap.MAP_TYPE_NORMAL
        }
    }

    override fun observeEvent() {

    }
}

private fun initDialog(
    context: Context,
    binding: ViewDataBinding
): Dialog =
    Dialog(context).apply {
        setContentView(binding.root)
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        show()
    }

private fun initBottomSheetDialog(
    context: Context,
    binding: ViewDataBinding
): BottomSheetDialog =
    BottomSheetDialog(context).apply {
        setContentView(binding.root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
        show()
    }