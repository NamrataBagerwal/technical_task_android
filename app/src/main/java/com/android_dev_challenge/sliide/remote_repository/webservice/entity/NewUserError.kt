package com.android_dev_challenge.sliide.remote_repository.webservice.entity


data class NewUserError(
    val code: Int,
    val data: List<Data>,
    val meta: Any?
) {
    data class Data(
        val field: String,
        val message: String
    )
}