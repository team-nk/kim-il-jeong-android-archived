package com.teamnk.kimiljung.base

import android.content.SharedPreferences
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.teamnk.kimiljung.util.SharedPreferencesName
import com.teamnk.kimiljung.util.initializeSharedPreferences

abstract class BaseActivity<B : ViewDataBinding>(
    @LayoutRes private val layoutId: Int,
) : AppCompatActivity() {

    protected val defaultSharedPreferences by lazy {
        initializeSharedPreferences(this, SharedPreferencesName.DEFAULT, MODE_PRIVATE)
    }

    protected val defaultSharedPreferencesEditor: SharedPreferences.Editor by lazy {
        defaultSharedPreferences.edit()
    }

    protected val binding: B by lazy {
        DataBindingUtil.setContentView(this, layoutId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        observeEvent()
    }

    abstract fun observeEvent()
}