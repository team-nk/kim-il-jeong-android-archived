package com.teamnk.kimiljung.feature.postcomment

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseActivity
import com.teamnk.kimiljung.databinding.ActivityPostCommentBinding

class PostCommentActivity : BaseActivity<ActivityPostCommentBinding>(
    R.layout.activity_post_comment
) {

    private val commentViewModel by lazy {
        ViewModelProvider(
            this,
            CommentViewModelFactory(
                CommentRepository(),
                application,
            )
        )[CommentViewModel::class.java]
    }

    override fun observeEvent() {
        commentViewModel.commentListResponse.observe(
            this,
        ){
            initCommentListRecyclerView(
                commentList = it.body()!!.comment_list,
            )
        }
    }

    private fun initCommentListRecyclerView(
        commentList : ArrayList<CommentList>,
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