package com.teamnk.kimiljung.util

import android.app.Activity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner

fun initializeBinding(activity: Activity, binding: ViewDataBinding, lifecycleOwner: LifecycleOwner) {
    activity.setContentView(binding.root)
    binding.lifecycleOwner = lifecycleOwner
}