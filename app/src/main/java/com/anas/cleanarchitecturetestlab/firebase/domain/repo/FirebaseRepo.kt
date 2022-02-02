package com.anas.cleanarchitecturetestlab.firebase.domain.repo

import android.net.Uri
import com.anas.cleanarchitecturetestlab.firebase.data.remote.PostDto
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference

interface FirebaseRepo {

    suspend fun registerUser(email:String , password:String):Boolean

    suspend fun loginUser(email:String , password:String):Boolean

    suspend fun getPostList(): CollectionReference

    suspend fun getPost(postId:String): DocumentReference

    suspend fun addPost(post: PostDto,imageURI: Uri?):Boolean

    suspend fun updatePost(post: PostDto,options:String = ""):Task<Void>

    suspend fun deletePost(postId: String): Task<Void>

    suspend fun uploadImageToPost(imageURI:Uri):Task<Uri>

}