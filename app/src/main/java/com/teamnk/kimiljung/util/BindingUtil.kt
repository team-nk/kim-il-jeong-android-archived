package com.teamnk.kimiljung.util

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner

fun initializeBinding(binding: ViewDataBinding, lifecycleOwner: LifecycleOwner) {
    binding.lifecycleOwner = lifecycleOwner



}