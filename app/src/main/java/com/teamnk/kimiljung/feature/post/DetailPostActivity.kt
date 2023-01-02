package com.teamnk.kimiljung.feature.post

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityDetailPostBinding
import com.teamnk.kimiljung.feature.postcomment.PostCommentActivity

class DetailPostActivity : BaseActivity<ActivityDetailPostBinding>(
    R.layout.activity_detail_post,
) {

    private val stringBuilder by lazy {
        StringBuilder()
    }

    private val viewList: ArrayList<TextView> by lazy {
        arrayListOf(
            binding.tvActivityDetailPostTitle,
            binding.tvActivityDetailPostScheduleContent,
            binding.tvActivityDetailPostAccountId,
            binding.tvActivityDetailPostAddress,
            binding.tvActivityDetailPostCreateTime,
        )
    }

    private val intentKeyList: ArrayList<String> by lazy {
        arrayListOf(
            "title",
            "schedule_content",
            "account_id",
            "address",
            "create_time",
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDetailPost()
        initPostCommentButton()
    }

    private fun initPostCommentButton() {
        binding.tvActivityDetailPostPostComment.setOnClickListener {
            this.startActivity(
                Intent(this, PostCommentActivity::class.java)
                    .putExtra("id", intent.getIntExtra("id", 0))
            )
        }
    }

    private fun initDetailPost() {
        binding.apply {
            intent.apply {
                for (i in 0.until(5)) {
                    viewList[i].text = getStringExtra(intentKeyList[i])
                }
                tvActivityDetailPostComment.text =
                    stringBuilder.append(getString(R.string.activity_detail_post_comment))
                        .append(" ").append(getIntExtra("comment_count", 0))
                        .append(getString(R.string.activity_detail_post_count))
                viewActivityDetailPost.background = ActivityCompat.getDrawable(
                    this@DetailPostActivity,
                    getIntExtra("color", 0,)
                )
            }
        }
    }

    override fun observeEvent() {}
}
