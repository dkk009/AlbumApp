package com.example.deepak.albumlist.dagger

import com.example.deepak.albumlist.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class, FragmentProvider::class))
    internal abstract fun bindMainActivity(): MainActivity
}