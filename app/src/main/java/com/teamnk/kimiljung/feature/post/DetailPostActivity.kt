package com.teamnk.kimiljung.feature.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityDetailPostBinding

class DetailPostActivity : BaseActivity<ActivityDetailPostBinding>(
    R.layout.activity_detail_post,
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun observeEvent() {}
}