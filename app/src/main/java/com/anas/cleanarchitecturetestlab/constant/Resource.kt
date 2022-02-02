package com.anas.cleanarchitecturetestlab.constant

sealed class Resource<T>(val data:T? = null,val message:String? = null){

    class OnLoading<T>( data: T? = null):Resource<T>(data)

    class OnSuccess<T>(data: T? , message: String? = null):Resource<T>(data,  message)

    class OnFail<T>(data: T? = null, message: String? ):Resource<T>(message =  message , data = data)

}
