package com.teamnk.kimiljung.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding>(
    @LayoutRes private val layoutId: Int
) : AppCompatActivity() {

    protected val binding: B by lazy {
        DataBindingUtil.setContentView(this, layoutId)
    }
}