package com.teamnk.kimiljung.feature.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.databinding.ItemPostBinding

class PostAdapter(private val postList: ArrayList<PostList>) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(postList : PostList){
            binding.postList = postList
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_post,
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(postList[position])
    }

    override fun getItemCount(): Int =
        postList.size

}