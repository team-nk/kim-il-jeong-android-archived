package com.teamnk.kimiljung.feature.postcomment

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
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
import java.io.ByteArrayOutputStream

class PostCommentAdapter(
    private val postList: ArrayList<PostList>,
    private val commentList: ArrayList<CommentList>,
    private val temp: Int,
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
                (holder as PostViewHolder).run {
                    postList[position].run {
                        setViewItemPostTagColor(
                            view = binding.viewItemPostPostTag,
                            context = itemView.context,
                            color = color,
                        )
                        bind(this)
                        itemView.setOnClickListener {
                            itemView.context.startActivity(
                                Intent(
                                    holder.itemView.context,
                                    DetailPostActivity::class.java,
                                )
                                    .putExtra("id", id)
                                    .putExtra("title", title)
                                    .putExtra("schedule_content", schedule_content)
                                    .putExtra("account_id", account_id)
                                    .putExtra("address", address)
                                    .putExtra("create_time", create_time)
                                    .putExtra("comment_count", comment_count)
                                    .putExtra("color", getPostColor(color))

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

    private fun setViewItemPostTagColor(
        view : View,
        context : Context,
        color : String,
    ){
        view.background = ActivityCompat.getDrawable(context, getPostColor(color))
    }

    private fun getPostColor(
        color: String,
    ): Int =
        when (color) {
            "RED" -> R.drawable.background_color_indicator_red_selector
            "BLUE" -> R.drawable.background_color_indicator_blue_selector
            "YELLOW" -> R.drawable.background_color_indicator_yellow_selector
            "GREEN" -> R.drawable.background_color_indicator_yellow_selector
            else -> R.drawable.background_color_indicator_purple_selector
        }
}