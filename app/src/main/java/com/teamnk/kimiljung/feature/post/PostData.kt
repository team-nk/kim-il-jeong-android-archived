package com.teamnk.kimiljung.feature.post

data class PostListResponse(
    val postList : ArrayList<PostList>
)

data class PostList(
    val title : String,
    val schedule_content : String,
    val address : String,
    val color : String,
    val comment_count : Int,
    val account_id : String,
    val create_time : String,
    val is_mine : Boolean,
)