package com.example.week4.model


class Feed(
    val username: String,
    val profilePicture: String,
    val feedContent: String,
    var isLike: Boolean,
    var isSaved: Boolean,
    var like: Int,
    val caption: String,
    val date: String
) {

}