package com.teamnk.kimiljung.util

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.databinding.ItemCommentBinding
import com.teamnk.kimiljung.databinding.ItemPostBinding
import com.teamnk.kimiljung.feature.post.DetailPostActivity
import com.teamnk.kimiljung.feature.post.PostList
import com.teamnk.kimiljung.feature.postcomment.CommentList

class Adapter(
    private val postList: ArrayList<PostList>,
    private val commentList: ArrayList<CommentList>,
    private val temp: Int,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val POST_LIST = 1
        const val COMMENT_LIST = 2
    }

    class PostViewHolder(
        val binding: ItemPostBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(postList: PostList) {
            binding.postList = postList
        }
    }

    class CommentViewHolder(
        val binding: ItemCommentBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(commentList: CommentList) {
            binding.commentList = commentList
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder =
        when(temp){
            POST_LIST ->{
                PostViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_post,
                        parent,
                        false,
                    )
                )
            }
            COMMENT_LIST ->{
                CommentViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_comment,
                        parent,
                        false,
                    )
                )
            }
            else->{
                TODO()
            }
        }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        when (temp) {
            POST_LIST -> {
                (holder as PostViewHolder).apply {
                    bind(postList[position])
                    itemView.apply {
                        setOnClickListener {
                            context.startActivity(
                                Intent(holder.itemView.context, DetailPostActivity::class.java)
                                    .putExtra("title", postList[position].title)
                                    .putExtra(
                                        "schedule_content",
                                        postList[position].schedule_content
                                    )
                                    .putExtra("account_id", postList[position].account_id)
                                    .putExtra("address", postList[position].address)
                                    .putExtra("create_time", postList[position].create_time)
                                    .putExtra("comment_count", postList[position].comment_count)
                            )
                        }
                    }
                }
            }
            COMMENT_LIST -> {
                (holder as CommentViewHolder).apply {
                    bind(commentList = commentList[position])
                    binding.tvItemCommentCreateTime.text = commentList[position].create_time.split('T').get(1)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return when(temp){
            POST_LIST -> postList.size
            COMMENT_LIST -> commentList.size
            else -> {
                TODO()
            }
        }
    }
}