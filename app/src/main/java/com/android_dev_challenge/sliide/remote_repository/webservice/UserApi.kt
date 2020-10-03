package com.android_dev_challenge.sliide.remote_repository.webservice

import com.android_dev_challenge.sliide.remote_repository.webservice.get_user.GetUserApiResponse
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("users/")
    fun getUsers(
        @Query("page") page: Int = 1
    ): Flowable<GetUserApiResponse>

    @GET("users/")
    fun getUsersOkHttp(
        @Query("page") page: Int = 1
    ): Call<GetUserApiResponse>
}