package com.teamnk.kimiljung.feature.postcomment

import com.google.gson.annotations.SerializedName

data class CommentListResponse(
    val comment_list: ArrayList<CommentList>,
)

data class CommentList(
    @SerializedName("content") val content: String,
    @SerializedName("account_id") val account_id: String,
    @SerializedName("profile") val profile: String,
    @SerializedName("create_time") val create_time: String,
)
