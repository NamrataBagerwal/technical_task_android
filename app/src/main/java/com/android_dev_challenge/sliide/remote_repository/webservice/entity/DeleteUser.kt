package com.android_dev_challenge.sliide.remote_repository.webservice.entity


data class DeleteUser(
    val code: Int,
    val data: Data?,
    val meta: Any?
) {
    data class Data(
        val message: String
    )
}