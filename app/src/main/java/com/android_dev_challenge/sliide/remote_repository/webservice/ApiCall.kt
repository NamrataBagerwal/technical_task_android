package com.android_dev_challenge.sliide.remote_repository.webservice

import android.util.Log
import com.android_dev_challenge.sliide.remote_repository.webservice.get_user.GetUserApiResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers


class ApiCall(
    private val userApi: UserApi
) {

    fun makeApiCall(): Flowable<List<GetUserApiResponse.User>> {
        return userApi.getUsers()
            .map { userApiResponse: GetUserApiResponse ->
                return@map userApiResponse.meta.pagination.pages
            }
            .flatMap { numOfPages: Int ->
                return@flatMap userApi.getUsers(numOfPages)
            }
            .flatMap { userApiResponse: GetUserApiResponse ->
                Flowable.just(userApiResponse.userList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}