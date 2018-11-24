package com.example.deepak.albumlist.dagger

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.example.deepak.albumlist.room.AppDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideRoom(application: Application): AppDataBase {
        return Room.databaseBuilder(
            application,
            AppDataBase::class.java, "album_database"
        ).build()
    }

}