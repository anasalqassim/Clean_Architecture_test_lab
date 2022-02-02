package com.anas.cleanarchitecturetestlab.firebase.domain.useCase

import android.net.Uri
import com.anas.cleanarchitecturetestlab.firebase.data.remote.PostDto
import com.anas.cleanarchitecturetestlab.firebase.domain.repo.FirebaseRepo
import javax.inject.Inject

class AddPostUseCase @Inject constructor(
    private val repo: FirebaseRepo
) {

    suspend operator fun invoke(postDto: PostDto, imageUri: Uri?):Boolean{
        return repo.addPost(postDto,imageUri)
    }


}