package com.teamnk.kimiljung.feature.post

data class PostListResponse(
    val postList : ArrayList<PostList>
)

data class PostList(
    val title : String,
    val scheduleContent : String,
    val address : String,
    val color : String,
    val commentCount : Int,
    val accountId : String,
    val createTime : String,
    val mine : Boolean,
)