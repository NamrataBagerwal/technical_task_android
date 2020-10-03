package com.android_dev_challenge.sliide.common

import android.app.Application
import com.android_dev_challenge.sliide.di.DependencyInjectionModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SliideApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        // get list of all modules
        val diModuleList = listOf(
            DependencyInjectionModule.apiModule,
            DependencyInjectionModule.viewModelModule
        )
        // start koin with the module list
        startKoin {
            // Android context
            androidContext(this@SliideApplication)
            // modules
            modules(diModuleList)
        }
    }
}