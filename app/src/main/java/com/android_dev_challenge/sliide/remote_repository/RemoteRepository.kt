package com.android_dev_challenge.sliide.remote_repository

import com.android_dev_challenge.sliide.remote_repository.webservice.ApiCall
import com.android_dev_challenge.sliide.remote_repository.webservice.get_user.GetUserApiResponse
import io.reactivex.rxjava3.core.Flowable

class RemoteRepository(private val apiCall: ApiCall) : BaseRepository() {

    fun getUserList(): Flowable<List<GetUserApiResponse.User>> = apiCall.makeApiCall()

}