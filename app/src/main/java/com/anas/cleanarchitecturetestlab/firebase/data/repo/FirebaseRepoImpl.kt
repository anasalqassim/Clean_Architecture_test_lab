package com.anas.cleanarchitecturetestlab.firebase.data.repo

import android.net.Uri
import com.anas.cleanarchitecturetestlab.firebase.data.remote.PostDto
import com.anas.cleanarchitecturetestlab.firebase.domain.repo.FirebaseRepo
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class FirebaseRepoImpl @Inject constructor(
    private val firebase:Firebase
) : FirebaseRepo {
    override suspend fun registerUser(email: String, password: String): Boolean {

        val registerTask = firebase.auth.createUserWithEmailAndPassword(email, password)

        return registerTask.await().user != null
    }

    override suspend fun loginUser(email: String, password: String): Boolean {
        return firebase.auth
            .signInWithEmailAndPassword(email, password)
            .await()
            .user != null
    }

    override suspend fun getPostList(): CollectionReference {
      return  firebase
            .firestore
            .collection("posts")
    }

    override suspend fun getPost(postId: String): DocumentReference {
        return firebase
            .firestore
            .collection("posts")
            .document(postId)
    }

    override suspend fun addPost(post: PostDto,imageURI: Uri?): Boolean {
        val collectionRef = firebase.firestore.collection("posts")
        val id = collectionRef.document().id
        var result = false
        post.postId = id

        if (imageURI == null){

            collectionRef.add(post).addOnSuccessListener {
                result = true
            }.await()

        }else{

           val imageUrl =  uploadImageToPost(imageURI).await().toString()

            post.pic = imageUrl

            collectionRef.add(post).addOnSuccessListener {
                result = true
            }.await()

        }



    return result
    }

    override suspend fun updatePost(post: PostDto, options: String): Task<Void> {

        return firebase.firestore.collection("posts")
            .document(post.postId)
            .set(post)
    }

    override suspend fun deletePost(postId: String): Task<Void> {
        return firebase.firestore.collection("posts")
            .document(postId)
            .delete()
    }

    override suspend fun uploadImageToPost(imageURI: Uri): Task<Uri>  {
        val storageRef = firebase.storage.reference.child("tets.jpg")
        return storageRef.putFile(imageURI).continueWithTask {
            storageRef.downloadUrl
        }
    }
}