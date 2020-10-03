package com.android_dev_challenge.sliide.remote_repository.webservice

import com.android_dev_challenge.sliide.BuildConfig
import com.android_dev_challenge.sliide.remote_repository.networking_retrofit.RetrofitFactory

object UserApiFactory {
    val userApi: UserApi = RetrofitFactory.retrofit(BuildConfig.BASE_URL).create(UserApi::class.java)
}