package com.example.deepak.albumlist.dagger

import android.app.Application
import com.example.deepak.albumlist.network.NetWorkModule
import com.example.deepak.albumlist.application.AlbumApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = arrayOf(
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        NetWorkModule::class
    )
)
@Singleton
interface AppComponent : AndroidInjector<AlbumApplication> {

    fun injectApp(application: AlbumApplication)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }


}



