package com.android_dev_challenge.sliide.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.android_dev_challenge.sliide.remote_repository.webservice.get_user.GetUserApiResponse
import com.android_dev_challenge.sliide.usecase.GetUserUsecase

class MainActivityViewModel(private val userUsecase: GetUserUsecase) : ViewModel() {

    fun getUserLiveData(): LiveData<List<GetUserApiResponse.User>> =
        LiveDataReactiveStreams.fromPublisher(userUsecase.getUserList())
}