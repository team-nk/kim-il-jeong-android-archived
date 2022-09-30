package com.teamnk.kimiljung.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding>(
    @LayoutRes private val layoutId: Int
) : AppCompatActivity() {

    private val binding: B by lazy {
        binding.lifecycleOwner = this
        DataBindingUtil.setContentView<B>(this, layoutId)
    }

    abstract fun observeEvent()
}