package com.anas.cleanarchitecturetestlab.firebase.presention.addPost

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.anas.cleanarchitecturetestlab.firebase.data.remote.PostDto
import com.anas.cleanarchitecturetestlab.firebase.domain.useCase.AddPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddPostViewModel @Inject constructor(
    private val addPostUseCase: AddPostUseCase
) : ViewModel() {

    fun addPost(postDto: PostDto , imageUri: Uri?): LiveData<Boolean>{

        return liveData {
            emit(addPostUseCase(postDto,imageUri))
        }

    }

}