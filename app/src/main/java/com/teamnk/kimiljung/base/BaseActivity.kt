package com.teamnk.kimiljung.base

import android.content.SharedPreferences
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.teamnk.kimiljung.util.SharedPreferencesName

abstract class BaseActivity<B : ViewDataBinding>(
    @LayoutRes private val layoutId: Int,
) : AppCompatActivity() {

    protected val binding: B by lazy {
        DataBindingUtil.setContentView(this, layoutId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        observeEvent()
    }

    // protected abstract fun initView()

    protected abstract fun observeEvent()
}