package com.teamnk.kimiljung.feature.postcomment

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.databinding.ItemCommentBinding
import com.teamnk.kimiljung.databinding.ItemPostBinding
import com.teamnk.kimiljung.feature.post.DetailPostActivity
import com.teamnk.kimiljung.feature.post.PostList

class PostCommentAdapter(
    private val postList: ArrayList<PostList>,
    private val commentList: ArrayList<CommentList>,
    private val temp: Int,
    private val context : Context,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val POST_LIST = 1
        const val COMMENT_LIST = 2
    }

    inner class PostViewHolder(
        val binding: ItemPostBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(postList: PostList) {
            binding.postList = postList
        }
    }

    inner class CommentViewHolder(
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
        when (temp) {
            POST_LIST -> {
                PostViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_post,
                        parent,
                        false,
                    )
                )
            }
            else -> {
                CommentViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_comment,
                        parent,
                        false,
                    )
                )
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
                    binding.viewItemPostPostTag.background = postColor(postList[position].color)
                    itemView.apply {
                        setOnClickListener {
                            context.startActivity(
                                Intent(holder.itemView.context, DetailPostActivity::class.java)
                                    .putExtra("id", postList[position].id)
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
                    binding.tvItemCommentCreateTime.text =
                        commentList[position].createTime.split('T')[1]
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return when (temp) {
            POST_LIST -> postList.size
            else -> commentList.size
        }
    }

    private fun postColor(
        color : String,
    ) : Drawable? =
        when(color){
            "RED" -> ActivityCompat.getDrawable(context, R.drawable.background_color_indicator_red_selector)
            "BLUE" -> ActivityCompat.getDrawable(context, R.drawable.background_color_indicator_blue_selector)
            "YELLOW" -> ActivityCompat.getDrawable(context, R.drawable.background_color_indicator_yellow_selector)
            "GREEN" -> ActivityCompat.getDrawable(context, R.drawable.background_color_indicator_green_selector)
            else -> ActivityCompat.getDrawable(context, R.drawable.background_color_indicator_purple_selector)
        }
}