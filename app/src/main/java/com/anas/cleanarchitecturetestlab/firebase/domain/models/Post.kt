package com.anas.cleanarchitecturetestlab.firebase.domain.models

import java.util.*

data class Post(
    val title:String,
    val creationDate: String,
    val pic: String,
    val description:String,
    val ownerId:String,
    val postId:String,
    val isPublished:Boolean,
)
