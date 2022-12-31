package com.teamnk.kimiljung.feature.postcomment

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityPostCommentBinding
import com.teamnk.kimiljung.util.showShortSnackBar

class PostCommentActivity : BaseActivity<ActivityPostCommentBinding>(
    R.layout.activity_post_comment
) {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            CommentViewModelFactory(
                CommentRepository(),
                application,
            )
        )[CommentViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSendButton()
    }

    private fun initSendButton() {
        binding.run {
            btnActivityPostCommentSend.setOnClickListener{
                etActivityPostCommentComment.text.toString().run {
                    if(this.isNotBlank()){
                        viewModel.postComment(
                            postId = intent.getIntExtra("id", 0),
                            commentRequest = CommentRequest(etActivityPostCommentComment.text.toString())
                        )
                    }else{
                        showShortSnackBar(
                            view = binding.root,
                            text = this@PostCommentActivity.getString(R.string.activity_post_comment_please_enter_comment)
                        )
                    }
                }
            }
        }
    }

    override fun observeEvent() {
        viewModel.commentListResponse.observe(
            this,
        ) {
            initCommentListRecyclerView(
                commentList = it.body()!!.comment_list,
            )
        }

        viewModel.snackBarMessage.observe(
            this,
        ){
            showShortSnackBar(
                view = binding.root,
                text = it
            )
        }
    }

    private fun initCommentListRecyclerView(
        commentList: ArrayList<CommentList>,
    ) {
        binding.rvActivityPostCommentMain.apply {
            adapter = PostCommentAdapter(
                postList = arrayListOf(),
                commentList = commentList,
                temp = 2,
            )
            layoutManager = LinearLayoutManager(this@PostCommentActivity)
        }
    }
}