package com.example.wiprosystemtask.base

interface BaseInterface {

    fun unBind()

    interface IResponseListener<T> {

        fun onProgress()

        fun onSuccess(resp: T)

        fun onFailure(t: Throwable)

    }
}