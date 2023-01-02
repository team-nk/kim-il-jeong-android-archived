package com.teamnk.kimiljung.feature.post

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityDetailPostBinding
import com.teamnk.kimiljung.feature.postcomment.PostCommentActivity

class DetailPostActivity : BaseActivity<ActivityDetailPostBinding>(
    R.layout.activity_detail_post,
) {

    private lateinit var postCommentActivityResultLauncher: ActivityResultLauncher<Intent>

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
        initActivityResultLauncher()
        initDetailPost()
        initPostCommentButton()
        setCommentCount(intent.getIntExtra("comment_count", 0))
    }

    private fun initDetailPost() {
        binding.apply {
            intent.apply {
                for (i in 0.until(5)) {
                    viewList[i].text = getStringExtra(intentKeyList[i])
                }
                viewActivityDetailPost.background = ActivityCompat.getDrawable(
                    this@DetailPostActivity,
                    getIntExtra("color", 0,)
                )
            }
        }
    }

    private fun initPostCommentButton() {
        binding.tvActivityDetailPostPostComment.setOnClickListener {
            postCommentActivityResultLauncher.launch(Intent(this, PostCommentActivity::class.java)
                .putExtra("id", intent.getIntExtra("id", 0)))
        }
    }

    private fun initActivityResultLauncher(){
       postCommentActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            if(it.resultCode == RESULT_OK){
                setCommentCount(it.data?.getIntExtra("comment_count", 0) ?: 0)
            }
        }
    }

    private fun setCommentCount(
        count : Int,
    ){
        stringBuilder.clear()
        binding.tvActivityDetailPostComment.text =
            stringBuilder.append(getString(R.string.activity_detail_post_comment))
                .append(" ").append(count)
                .append(getString(R.string.activity_detail_post_count))
    }

    override fun observeEvent() {}
}
