package com.android_dev_challenge.sliide.di

import com.android_dev_challenge.sliide.BuildConfig
import com.android_dev_challenge.sliide.remote_repository.RemoteRepository
import com.android_dev_challenge.sliide.remote_repository.networking_retrofit.RetrofitFactory
import com.android_dev_challenge.sliide.remote_repository.webservice.ApiCall
import com.android_dev_challenge.sliide.remote_repository.webservice.UserApi
import com.android_dev_challenge.sliide.remote_repository.webservice.UserApiFactory
import com.android_dev_challenge.sliide.ui.activity.MainActivity
import com.android_dev_challenge.sliide.usecase.GetUserUsecase
import com.android_dev_challenge.sliide.viewmodel.MainActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.inject

object DependencyInjectionModule {

    val apiModule = module {
        single {
            val userApi: UserApi = UserApiFactory.userApi
            return@single ApiCall(userApi)
        }
        single{ RemoteRepository(get()) }
        single { GetUserUsecase(get()) }
    }

    // Initializing ViewModel Modules
    val viewModelModule = module {
        viewModel {
            MainActivityViewModel(get())
        }
    }

}