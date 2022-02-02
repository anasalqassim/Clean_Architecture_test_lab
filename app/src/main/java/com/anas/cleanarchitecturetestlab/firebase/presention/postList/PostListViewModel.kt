package com.anas.cleanarchitecturetestlab.firebase.presention.postList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anas.cleanarchitecturetestlab.constant.Resource
import com.anas.cleanarchitecturetestlab.firebase.domain.models.Post
import com.anas.cleanarchitecturetestlab.firebase.domain.useCase.PostListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.math.log

private const val TAG = "PostListViewModel"
@HiltViewModel
class PostListViewModel @Inject constructor(
    private val postListUseCase: PostListUseCase
) : ViewModel() {

    private val _postLiveData = MutableLiveData<List<Post>>()
    val postLiveData:LiveData<List<Post>> = _postLiveData


    fun getPostsList(){

        postListUseCase().onEach {
            when(it){
                is Resource.OnSuccess ->{
                    _postLiveData.value = it.data!!
                }
                is Resource.OnFail -> {
                    Log.d(TAG, "getPostsList: Error : ${it.message}")
                }
                is Resource.OnLoading -> {

                }
            }
        }.launchIn(viewModelScope)


    }
}