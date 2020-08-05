package com.example.wiprosystemtask.base

interface BaseInterface {


    interface IResponseListener<T> {

        fun onProgress()

        fun onSuccess(resp: T)

        fun onFailure(t: Throwable)

    }
}