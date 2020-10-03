package com.android_dev_challenge.sliide.remote_repository.webservice.entity


import com.google.gson.annotations.SerializedName

data class NewUser(
    val code: Int,
    val `data`: Data,
    val meta: Any
) {
    data class Data(
        @SerializedName("created_at")
        val createdAt: String,
        val email: String,
        val gender: String,
        val id: Int,
        val name: String,
        val status: String,
        @SerializedName("updated_at")
        val updatedAt: String
    )
}