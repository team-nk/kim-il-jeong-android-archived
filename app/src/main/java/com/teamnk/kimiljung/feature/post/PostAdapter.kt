package com.teamnk.kimiljung.feature.post

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.databinding.ItemPostBinding
import com.teamnk.kimiljung.util.startActivity

class PostAdapter(private val postList: ArrayList<PostList>) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(postList: PostList) {
            binding.postList = postList
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_post,
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postList[position])
        holder.itemView.apply {
            setOnClickListener {
                context.startActivity(
                    Intent(holder.itemView.context, DetailPostActivity::class.java)
                        .putExtra("title", postList[position].title)
                        .putExtra("schedule_content", postList[position].schedule_content)
                        .putExtra("account_id", postList[position].account_id)
                        .putExtra("address", postList[position].address)
                        .putExtra("create_time", postList[position].create_time)
                        .putExtra("comment_count", postList[position].comment_count)
                )
            }
        }
    }

    override fun getItemCount(): Int =
        postList.size

}