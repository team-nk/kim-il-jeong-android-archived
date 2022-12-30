package com.teamnk.kimiljung.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun ImageView.loadImage(profile : String?){
    Glide.with(this)
        .load(profile)
        .into(this)
}