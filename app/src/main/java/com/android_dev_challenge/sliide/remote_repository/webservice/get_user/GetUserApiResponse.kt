package com.android_dev_challenge.sliide.remote_repository.webservice.get_user


import com.google.gson.annotations.SerializedName

data class GetUserApiResponse(
    val code: Int,
    @SerializedName("data")
    val userList: List<User>,
    val meta: Meta
) {
    data class User(
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

    data class Meta(
        val pagination: Pagination
    ) {
        data class Pagination(
            val limit: Int,
            val page: Int,
            val pages: Int,
            val total: Int
        )
    }
}