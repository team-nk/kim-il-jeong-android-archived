package com.teamnk.kimiljung.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("profileImage")
fun ImageView.loadImage(url : String){
    Glide.with(this)
        .load(url)
        .into(this)
}