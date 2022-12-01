package com.teamnk.kimiljung.base

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.teamnk.kimiljung.util.SharedPreferencesName

abstract class BaseFragment<B : ViewDataBinding>(
    @LayoutRes private val layoutId: Int
) : Fragment() {

    protected val defaultSharedPreferences: SharedPreferences by lazy {
        requireActivity().getSharedPreferences(
            SharedPreferencesName.DEFAULT,
            AppCompatActivity.MODE_PRIVATE
        )
    }

    protected val defaultSharedPreferencesEditor: SharedPreferences.Editor by lazy {
        defaultSharedPreferences.edit()
    }

    protected lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        observeEvent()
    }

    abstract fun observeEvent()
}