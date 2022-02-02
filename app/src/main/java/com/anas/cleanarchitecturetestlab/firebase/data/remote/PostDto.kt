package com.anas.cleanarchitecturetestlab.firebase.data.remote

import com.anas.cleanarchitecturetestlab.constant.AppConstants
import com.anas.cleanarchitecturetestlab.firebase.domain.models.Post
import java.text.DateFormat
import java.util.*

data class PostDto(
    val title:String ="",
    val creationDate: Date = Date(),
    var pic: String ="",
    val description:String ="",
    val ownerId:String ="",
    var postId:String = "",
    val isPublished:Boolean = false,
)

fun PostDto.toPost():Post{
  val x =  android.text.format.DateFormat.format(AppConstants.DATE_FORMAT,creationDate)

    return Post(
        title = title,
        creationDate = x as String,
        pic = pic,
        description= description,
        ownerId = ownerId,
        postId = postId,
        isPublished = isPublished
    )
}
