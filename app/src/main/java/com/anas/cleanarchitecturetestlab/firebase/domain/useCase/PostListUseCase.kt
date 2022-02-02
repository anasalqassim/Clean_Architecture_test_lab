package com.anas.cleanarchitecturetestlab.firebase.domain.useCase

import android.util.Log
import com.anas.cleanarchitecturetestlab.constant.Resource
import com.anas.cleanarchitecturetestlab.firebase.data.remote.PostDto
import com.anas.cleanarchitecturetestlab.firebase.data.remote.toPost
import com.anas.cleanarchitecturetestlab.firebase.domain.models.Post
import com.anas.cleanarchitecturetestlab.firebase.domain.repo.FirebaseRepo
import com.kiwimob.firestore.coroutines.snapshotAsFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostListUseCase @Inject constructor(
    private val repo: FirebaseRepo) {

    operator fun invoke():Flow<Resource<List<Post>>> = flow{

        try {
            emit(Resource.OnLoading<List<Post>>())

            repo.getPostList()
                .snapshotAsFlow()
                .collect{ snapshot ->
                    val data = snapshot.toObjects(PostDto::class.java).map { it.toPost() }
                    emit(Resource.OnSuccess<List<Post>>(data))
                }


        }catch (e:Exception){
            emit(Resource.OnFail<List<Post>>(message = e.localizedMessage))
        }

    }.flowOn(Dispatchers.IO)
}