package com.example.deepak.albumlist.application

import com.example.deepak.albumlist.network.APIEndService
import com.example.deepak.albumlist.BuildConfig
import com.example.deepak.albumlist.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import javax.inject.Inject

class AlbumApplication : DaggerApplication() {

    @Inject
    lateinit var netWorkModule: APIEndService

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        init()
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }

    private fun init() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}