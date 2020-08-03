package com.example.wiprosystemtask.repo

open class BaseResponse {
    var message: String? = null

    var status: Int = 0

    override fun toString(): String {
        return "ClassPojo [message = $message, status = $status]"
    }
}