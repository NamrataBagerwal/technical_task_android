package com.android_dev_challenge.sliide.usecase

import com.android_dev_challenge.sliide.remote_repository.RemoteRepository
import com.android_dev_challenge.sliide.remote_repository.webservice.get_user.GetUserApiResponse
import io.reactivex.rxjava3.core.Flowable

class GetUserUsecase(private val remoteRepository: RemoteRepository) {

    fun getUserList(): Flowable<List<GetUserApiResponse.User>> = remoteRepository.getUserList()

}